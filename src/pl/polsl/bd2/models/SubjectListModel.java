package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.TypPrzedmiotu;
import pl.polsl.bd2.messageSystem.service.TypPrzedmiotuService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;

public class SubjectListModel extends QAbstractListModel {
	

	public SubjectListModel() {
		typPrzedmiotuService = (TypPrzedmiotuService) SpringUtil.getBean("typPrzedmiotuService");
		container = new ArrayList<TypPrzedmiotu>();
		container = typPrzedmiotuService.findAll();
	}
	
	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {

		
		if (role == Qt.ItemDataRole.DisplayRole)
			return container.get(index.row()).getNazwa();
		else if (role == Qt.ItemDataRole.UserRole)
			return container.get(index.row());
		return null;
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}
	

	private TypPrzedmiotuService typPrzedmiotuService;
	private List<TypPrzedmiotu> container;
}
