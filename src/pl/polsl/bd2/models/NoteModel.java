package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.models.Uwaga;
import pl.polsl.bd2.messageSystem.service.OcenaService;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.UczenService;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class NoteModel extends QAbstractTableModel {
	private final String[] COLUMNS = { tr("Uwagi") };
	List<List<Uwaga>> dataContainer = new ArrayList<List<Uwaga>>();
	UczenService uczenService = (UczenService) SpringUtil.getBean("uczenService");
	OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
	OcenaService ocenaService = (OcenaService) SpringUtil.getBean("ocenaService");
	int currentPupil = 0;
	int row=0;

	public void initData(List<Uczen> listaUczniow){
		for(Uczen uczen: listaUczniow){
			this.dataContainer.add(new ArrayList<Uwaga>(uczen.getUczen2Uwaga()));
		}
		this.refreshModel();
	}
	
	public void addNote(int uczen, Uwaga uwaga){
		this.dataContainer.get(uczen).add(uwaga);
		this.refreshModel();
	}
	
	public void changePupil(int indeksPupil){
		this.currentPupil = indeksPupil;
		this.refreshModel();
	}

	public void refreshModel(){
		this.reset();
		this.layoutChanged.emit();
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return COLUMNS.length;
	}

	@Override
	public Object data(QModelIndex index, int role) {
		this.row = index.row();
		if (role == Qt.ItemDataRole.DisplayRole) {
			try{
			switch (index.column()) {
			case 0:
				return this.dataContainer.get(this.currentPupil).get(index.row()).getTekstUwagi();
			}
			}catch(IndexOutOfBoundsException e){}

		}
		return null;
		
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		// TODO Auto-generated method stub
		try{
			return dataContainer.get(this.currentPupil).size();
		}catch(IndexOutOfBoundsException e){
			return 0;
		}
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Orientation.Horizontal) {
				return COLUMNS[section];
			} else {
				return null;
			}
		} else {
			return super.headerData(section, orientation, role);
		}
	}

	@Override
	public ItemFlags flags(QModelIndex index) {
		ItemFlags itemFlags = new ItemFlags();
		itemFlags.clearAll();
		itemFlags.set(ItemFlag.ItemIsEnabled, ItemFlag.ItemIsSelectable);
		return itemFlags;
	}

	public List<List<Uwaga>> getDataContainer() {
		return dataContainer;
	}

	public void setDataContainer(List<List<Uwaga>> dataContainer) {
		this.dataContainer = dataContainer;
	}
	
	
	
}
