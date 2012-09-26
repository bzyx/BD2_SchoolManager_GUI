package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;

public class JustificationModel extends QAbstractListModel {
	//TODO MJ Hmmmmm mmmmmm mhmmm
	private List<String> dataContainer;
	
	public JustificationModel(){
		this.dataContainer = new ArrayList<String>();
		//this.dataContainer.add(new Date().toString() + ";\t5 hour;\tbo lolek to matolek");
		//this.dataContainer.add(new Date().toString() + ";\t5 hour;\ti jego pacho�ek te�");
	}

	@Override
	public Object data(QModelIndex index, int role){
	    if (index.row() < 0 || index.row() >= dataContainer.size())
	        return null;

	    if (role != Qt.ItemDataRole.DisplayRole)
	        return null;

	    return dataContainer.get(index.row());
	}
	
	@Override
	public int rowCount(QModelIndex parent){
		return dataContainer.size();
	}
	
	public void addJustification(Date date, int lection, String motive){
		boolean add = true;
		String justification;;
		if (this.dataContainer.size() > 0) {
			int iterator = 0;
			while (add && iterator < this.dataContainer.size()) {
				justification = this.dataContainer.get(iterator);
				if (justification.regionMatches(0, date.toString(), 0, date
						.toString().length())) {
					add = false;
				} else
					add = true;
				iterator++;	
			}
		}
		if (add) {
			this.dataContainer.add(date.toString() + ";\t"
					+ Integer.toString(lection) + " hour;\t" + motive);
		} else {
			this.dataContainer.add(" ta data ju� jest");
		}
		
	}
}
