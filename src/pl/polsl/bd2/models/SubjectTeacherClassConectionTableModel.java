package pl.polsl.bd2.models;

import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Przedmiot;
import pl.polsl.bd2.messageSystem.service.PrzedmiotService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class SubjectTeacherClassConectionTableModel extends QAbstractTableModel {
	private final String PRZEDMIOT = "Przedmiot";
	private final String ODDZIAL = "Oddział‚";
	private final String NAUCZYCIEL = "Nauczyciel";
	
	PrzedmiotService przedmiotService = (PrzedmiotService) SpringUtil.getBean("przedmiotService");
	List<Przedmiot> container;
	
	public SubjectTeacherClassConectionTableModel() {
		container = przedmiotService.findAll();
	}
	
	@Override
	@QtBlockedSlot
	public int columnCount(QModelIndex arg0) {
		return 3;
	}

	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {
		int row = index.row();
		int col = index.column();
		
		if(role == ItemDataRole.DisplayRole){
			if (col == 0)
				return container.get(row).getTypPrzedmiotu().getNazwa();
			if (col == 1)
				return container.get(row).getOddzial().getNazwa();
			if (col == 2)
				return String.format("%s %s ", container.get(row).getNauczyciel().getOsoba().getImie(),
						   container.get(row).getNauczyciel().getOsoba().getNazwisko());
		} 
		
		if(role == ItemDataRole.UserRole){
				return container.get(row);
		}
		
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Qt.Orientation.Horizontal) {
				if (section == 0)
					return PRZEDMIOT;
				if (section == 1)
					return ODDZIAL;
				if (section == 2)
					return NAUCZYCIEL;
			}
		}
		return null;
	}
	
	public void updateModel(){
		container.clear();
		container = przedmiotService.findAll();
		reset();
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}

}
