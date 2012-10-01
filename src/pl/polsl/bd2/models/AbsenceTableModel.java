package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Absencja;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.AbsencjaService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class AbsenceTableModel extends QAbstractTableModel {

	private List<Absencja> container;

	public AbsenceTableModel() {
		container = new ArrayList<Absencja>();
	}

	public void setStudent(Uczen uczen) {
		AbsencjaService absencjaService = (AbsencjaService) SpringUtil
				.getBean("absencjaService");
		List<Absencja> absencja = absencjaService.findAll();
		container.clear();

		for (Absencja a : absencja) {
			if (a.getUczen().getIdUczen() == uczen.getIdUczen())
				container.add(a);
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
		if (role == ItemDataRole.DisplayRole) {
			if (col == 0)
				//return container.get(row).getObecnosc() == true ? "Obecny"
					//	: "Nieobecny";
			if (col == 1)
				return container.get(row).getData();
		}
		return null;
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Qt.Orientation.Horizontal) {
				if (section == 0)
					return "Obecność";
				if (section == 1)
					return "Data";
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
