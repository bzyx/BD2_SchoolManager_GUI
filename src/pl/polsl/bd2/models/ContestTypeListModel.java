package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.TypKonkursu;
import pl.polsl.bd2.messageSystem.service.TypKonkursuService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;

public class ContestTypeListModel extends QAbstractListModel {
	

	public ContestTypeListModel() {
		typKonkursuService = (TypKonkursuService) SpringUtil.getBean("typKonkursuService");
		container = new ArrayList<TypKonkursu>();
		container = typKonkursuService.findAll();
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
	
	@Override
	@QtBlockedSlot
	public boolean setData(QModelIndex arg0, Object arg1, int arg2) {
		TypKonkursu entity = new TypKonkursu((String) arg1);
		if (arg0 == null){
			container.add(entity);
			typKonkursuService.save(entity);
			return super.setData(arg0, arg1, arg2);
		}
	
		if (arg0.row() > container.size()){
			container.add(entity);
			typKonkursuService.save(entity);
		}
		else {
			TypKonkursu typ = (TypKonkursu)arg0.data(ItemDataRole.UserRole);
			typ.setNazwa((String) arg1);
			typKonkursuService.edit(typ);
			container.set(arg0.row(), typ);
		}
		return super.setData(arg0, arg1, arg2);
	}
	
	@Override
	@QtBlockedSlot
	public boolean removeRows(int arg0, int arg1, QModelIndex arg2) {
		typKonkursuService.delete(container.get(arg0));
		container.remove(arg0);
		return super.removeRows(arg0, arg1, arg2);
	}

	public void makeUpdate() {
		container.clear();
		container = typKonkursuService.findAll();
		reset();	
	}

	private TypKonkursuService typKonkursuService;
	private List<TypKonkursu> container;

}
