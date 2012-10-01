package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.service.OddzialService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;

public class OddzialListModel extends QAbstractListModel {
	

	public OddzialListModel() {
		oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
		container = new ArrayList<Oddzial>();
		container = oddzialService.findAll();
	}
	
	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {

		
		if (role == Qt.ItemDataRole.DisplayRole)
			return String.format("%s", container.get(index.row()).getNazwa());
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
		container = oddzialService.findAll();
		reset();
	}

	private OddzialService oddzialService;
	private List<Oddzial> container;
}
