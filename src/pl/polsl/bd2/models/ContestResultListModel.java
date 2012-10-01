package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.WynikKonkursu;
import pl.polsl.bd2.messageSystem.service.WynikKonkursuService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;

public class ContestResultListModel extends QAbstractListModel {
	

	public ContestResultListModel() {
		wynikKonkursuService = (WynikKonkursuService) SpringUtil.getBean("wynikKonkursuService");
		container = new ArrayList<WynikKonkursu>();
		container = wynikKonkursuService.findAll();
	}
	
	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {
		if (role == Qt.ItemDataRole.DisplayRole)
			return container.get(index.row()).getWynik();
		if (role == Qt.ItemDataRole.ToolTipRole)
			return container.get(index.row()).getOpis();
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
		WynikKonkursu entity = new WynikKonkursu((String) arg1, "");
		if (arg0 == null){
			container.add(entity);
			wynikKonkursuService.save(entity);
			return super.setData(arg0, arg1, arg2);
		}
	
		if (arg0.row() > container.size()){
			container.add(entity);
			wynikKonkursuService.save(entity);
		}
		else {
			WynikKonkursu typ = (WynikKonkursu)arg0.data(ItemDataRole.UserRole);
			typ.setWynik((String) arg1);
			wynikKonkursuService.edit(typ);
			container.set(arg0.row(), typ);
		}
		return super.setData(arg0, arg1, arg2);
	}
	
	@Override
	@QtBlockedSlot
	public boolean removeRows(int arg0, int arg1, QModelIndex arg2) {
		wynikKonkursuService.delete(container.get(arg0));
		container.remove(arg0);
		return super.removeRows(arg0, arg1, arg2);
	}
	
	public void makeUpdate() {
		container.clear();
		container = wynikKonkursuService.findAll();
		reset();
	}

	private WynikKonkursuService wynikKonkursuService;
	private List<WynikKonkursu> container;
}
