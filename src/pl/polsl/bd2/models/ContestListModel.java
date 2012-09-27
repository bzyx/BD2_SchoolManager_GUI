package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Konkurs;
import pl.polsl.bd2.messageSystem.service.KonkursService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;

public class ContestListModel extends QAbstractListModel {
	

	public ContestListModel() {
		konkursService = (KonkursService) SpringUtil.getBean("konkursService");
		container = new ArrayList<Konkurs>();
		container = konkursService.findAll();
	}
	
	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {

		
		if (role == Qt.ItemDataRole.DisplayRole)
			return String.format("%s ( %s )", container.get(index.row()).getNazwa(), container.get(index.row()).getTypKonkursu().getNazwa());
		else if (role == Qt.ItemDataRole.UserRole)
			return container.get(index.row());
		return null;
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}
	
	public void makeUpdate(){
		container.clear();
		container = konkursService.findAll();
		reset();
	}

	

	private KonkursService konkursService;
	private List<Konkurs> container;
}
