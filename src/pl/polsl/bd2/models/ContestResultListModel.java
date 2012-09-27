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
	

	private WynikKonkursuService wynikKonkursuService;
	private List<WynikKonkursu> container;
}
