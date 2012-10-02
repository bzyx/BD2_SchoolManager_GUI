package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.messageSystem.models.Absencja;
import pl.polsl.bd2.messageSystem.models.Uczen;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class AbsenceModel extends QAbstractTableModel{
	private final String[] COLUMNS = {tr("Date                   "), tr("absence")};//, tr("unexcused hours")};
	//private List<Absence.AbsenceMock> dataContainer;
	private List<Absencja> dataContainer = new ArrayList<Absencja>();
	
	public AbsenceModel(){
		super();
	}
	
	public AbsenceModel(Uczen uczen){
		super();
		this.dataContainer = new ArrayList<Absencja>(uczen.getUczen2Absencja());
	}
	
	public void initData(List<Absencja> absencje){
		dataContainer.clear();
		this.dataContainer = absencje;
		this.refreshModel();
	}
	
	public void refreshModel(){
		this.reset();
		this.layoutChanged.emit();
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return this.COLUMNS.length;
	}

	@Override
	public int rowCount(QModelIndex index) {
		return dataContainer.size();
	}

	@Override
	public Object data(QModelIndex index, int role) {

		if (role == Qt.ItemDataRole.DisplayRole) {
			switch (index.column()) {
			case 0:
				return dataContainer.get(index.row()).getData();
			case 1:{
				if(!dataContainer.get(index.row()).getObecnosc()){
					if(dataContainer.get(index.row()).getUsprawiedliwiona())
						return "nieobecny (uspr)" ;
					else return "nieobecny";
				}else return "obecny";
			}
			default:
				throw new IndexOutOfBoundsException(
						"Column must be between 0 and 6.");
			}

		}

		return null;
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
	
	public void addAbsence(Absencja absencja){
		this.dataContainer.add(absencja);
	}

	public List<Absencja> getDataContainer() {
		return dataContainer;
	}

	public void setDataContainer(List<Absencja> dataContainer) {
		this.dataContainer = dataContainer;
	}
	
}
