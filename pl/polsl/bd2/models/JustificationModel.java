package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;

public class JustificationModel extends QAbstractListModel {
	private List<String> dataContainer;
	
	public JustificationModel(){
		this.dataContainer = new ArrayList<String>();
		this.dataContainer.add("lolek matolek");
		this.dataContainer.add("i jego pacho³ek");
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
}
