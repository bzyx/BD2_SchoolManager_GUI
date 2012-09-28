package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.models.Uwaga;
import pl.polsl.bd2.messageSystem.service.UwagaService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class JustificationTableModel extends QAbstractTableModel {
	
	private List<Uwaga> container;
	
	public JustificationTableModel() {
		
		container = new ArrayList<Uwaga>();
	}
	
	public void setStudent(Uczen uczen){
		UwagaService uwagaService = (UwagaService) SpringUtil.getBean("uwagaService");
		List<Uwaga> uwagi = uwagaService.findAll();
		container.clear();
		
		for (Uwaga uwaga: uwagi){
			if (uwaga.getUczen().getIdUczen() == uczen.getIdUczen())
				container.add(uwaga);
		}
	}

	@Override
	@QtBlockedSlot
	public int columnCount(QModelIndex arg0) {
		return 2;
	}

	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {
		int row = index.row();
		int col = index.column();
		
		if (role == ItemDataRole.DisplayRole){
			if (col == 0)
				return container.get(row).getTekstUwagi();
			if (col == 1)
				return String.format("%s %s", container.get(row).getNauczyciel().getOsoba().getImie(), 
						container.get(row).getNauczyciel().getOsoba().getNazwisko());
		}
		
		if (role == ItemDataRole.SizeHintRole){
			if (col == 0)
				return 1;
			if (col == 1)
				return 2;
		}
		
		return null;
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Qt.Orientation.Horizontal) {
				if (section == 0)
					return "Treść uwagi";
				if (section == 1)
					return "Nauczyciel wystawiający"; 
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
