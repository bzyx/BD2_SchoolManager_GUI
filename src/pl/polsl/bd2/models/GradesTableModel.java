package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Ocena;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.OcenaService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class GradesTableModel extends QAbstractTableModel {

	List<Ocena> container;
	public GradesTableModel() {
		container = new ArrayList<Ocena>();
	}
	
	public void setStudent(Uczen uczen){
		OcenaService ocenaService = (OcenaService) SpringUtil.getBean("ocenaService");
		List<Ocena> oceny = ocenaService.findAll();
		container.clear();
		
		for (Ocena ocena: oceny){
			if (ocena.getUczen().getOsoba().getIdOsoba() == uczen.getOsoba().getIdOsoba() ){
				container.add(ocena);
			}
		}
		
	}
	
	@Override
	@QtBlockedSlot
	public int columnCount(QModelIndex arg0) {
		return 4;
	}

	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {
		int row = index.row();
		int col = index.column();
		if (role == ItemDataRole.DisplayRole){
			if (col == 0)
				return container.get(row).getOcena();
			if (col == 1)
				return container.get(row).getData();
			if (col == 2)
				return container.get(row).getPrzedmiot().getTypPrzedmiotu().getNazwa();
			if (col == 3 )
				return container.get(row).getNauczyciel().getOsoba().getImie()+" "+container.get(row).getNauczyciel().getOsoba().getNazwisko();
		}
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Qt.Orientation.Horizontal) {
				if (section == 0)
					return "Ocena";
				if (section == 1)
					return "Data";
				if (section == 2)
					return "Przedmiot";
				if (section == 3)
					return "Nauczyciel";
			}
		}
		return null;
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}

}
