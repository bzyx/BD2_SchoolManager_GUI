package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.UczenService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;

public class StudentListModel extends QAbstractListModel {

	public StudentListModel() {
		uczenService = (UczenService) SpringUtil.getBean("uczenService");
		container = new ArrayList<Uczen>();
		container = uczenService.findAll();
	}

	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {

		if (role == Qt.ItemDataRole.DisplayRole)
			return String.format("%s %s", container.get(index.row()).getOsoba()
					.getImie(), container.get(index.row()).getOsoba()
					.getNazwisko());
		else if (role == Qt.ItemDataRole.UserRole)
			return container.get(index.row());
		return null;
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}

	private UczenService uczenService;
	private List<Uczen> container;
}
