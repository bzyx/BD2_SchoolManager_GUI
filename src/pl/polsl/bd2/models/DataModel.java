package pl.polsl.bd2.models;

import java.util.*;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Komunikat;
import pl.polsl.bd2.messageSystem.models.Przedmiot;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.KomunikatService;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.PrzedmiotService;
import pl.polsl.bd2.messageSystem.service.UczenService;

import com.trolltech.qt.gui.*;
import com.trolltech.qt.core.*;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;

public class DataModel extends QAbstractTableModel {
	private final String[] COLUMNS;
	private final List<Boolean> selected = new ArrayList<Boolean>();
	private List<UserData.DataMock> dataContainer;
	List<Przedmiot> dataContainer2;
	PrzedmiotService przedmiotService;
	List<Uczen> listUczen;
	UczenService uczenService;

	public DataModel() {
		przedmiotService = (PrzedmiotService)SpringUtil.getBean("przedmiotService");
		dataContainer2 = new ArrayList<Przedmiot>(przedmiotService.findAll());
		this.COLUMNS = DataColumnName.valueOf("DataTable").returnColumnName();
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		return this.COLUMNS.length;
	}

	@Override
	public int rowCount(QModelIndex index) {
		return dataContainer2.size();
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			switch (index.column()) {
			case 0:
				System.out.println(dataContainer2.get(index.row()).getTypPrzedmiotu().getNazwa());
				return dataContainer2.get(index.row()).getTypPrzedmiotu().getNazwa();	
			case 1:
				return dataContainer2.get(index.row()).getTypPrzedmiotu().getNazwa();
			case 2:
				return dataContainer2.get(index.row()).getTypPrzedmiotu().getNazwa();
			case 3:
				return dataContainer2.get(index.row()).getTypPrzedmiotu().getNazwa();
			case 4:
				return dataContainer2.get(index.row()).getTypPrzedmiotu().getNazwa();
			default:
				throw new IndexOutOfBoundsException(
						"Column must be between 0 and 6.");
			}

		}
		//combo box
		/*if (role == Qt.ItemDataRole.CheckStateRole) {
			if (index.column() == 0)
				return !selected.get(index.row());
		}*/
		//center Text in row
	    /*if ( index.column() >=1 && role == Qt.ItemDataRole.TextAlignmentRole ) {
	    	Alignment aligmentFlags = new Alignment();
	        aligmentFlags.set(AlignmentFlag.AlignCenter, Qt.AlignmentFlag.AlignHCenter);// | ;
	        return aligmentFlags;
	    }*/

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
	public boolean setData(QModelIndex index, Object value, int role) {
		if (role == Qt.ItemDataRole.CheckStateRole) {
			if (index.column() == 0) {
				Boolean val = selected.get(0);
				val = !val;
				selected.set(0, val);
				selected.set(index.row(), val);
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemFlags flags(QModelIndex index) {
		ItemFlags itemFlags = new ItemFlags();
		itemFlags.clearAll();
		if (index.column() == 0) {
			itemFlags.set(ItemFlag.ItemIsSelectable, ItemFlag.ItemIsEnabled,
					ItemFlag.ItemIsUserCheckable);
		} else {
			itemFlags.set(ItemFlag.ItemIsEnabled, ItemFlag.ItemIsSelectable);
		}

		return itemFlags;
	}

	public enum DataColumnName {
		DataTable(1), DataDetailsTable(2);
		private final Map<Integer, String[]> columnMap = new HashMap<Integer, String[]>();
		{
			columnMap.put(1, new String[] { "Subject", "Avg.", "Rates",
					"Absence", "Excused absence" });
			columnMap.put(2, new String[] { "Ajaja", "I ja.", "no ty", "itd",
					"No 3", "No 2", "No 1" });
		}
		private int identifier;

		private DataColumnName(int i) {
			this.identifier = i;
		}

		public String[] returnColumnName() {
			return columnMap.get(this.identifier);
		}
	}

	public void setDataContainer(Uczen uczen, List<UserData.DataMock> dataContainer) {
		this.dataContainer2 = przedmiotService.findByClass(uczen.getOddzial());
		this.dataContainer = dataContainer;
		System.out.println(this.dataContainer2.size());
		for(int i = 0; i < dataContainer2.size(); i++){
			this.selected.add(true);
		}
	}

	public List<UserData.DataMock> getDataContainer() {
		return dataContainer;
	}

}