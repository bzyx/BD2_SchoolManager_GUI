package pl.polsl.bd2.models;

import java.util.*;


import com.trolltech.qt.gui.*;
import com.trolltech.qt.core.*;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;

public class TableModel extends QAbstractTableModel {
	private final String[] COLUMNS;
	private final List<Boolean> selected = new ArrayList<Boolean>();
	private final List<String> name = new ArrayList<String>();
	private Integer row = 0;
	private UserData userData;
	private final List<List<String>> nameList = new ArrayList<List<String>>();
	
	public enum DataColumnName {
		DataTable(1), DataDetailsTable(2);
		private final Map<Integer, String[]> columnMap = new HashMap<Integer, String[]>();
		{
			columnMap.put(1, new String[] {"Subject", "Avg.", "No 5",
					"No 4", "No 3", "No 2", "No 1"});//, "Absence", "Excused absence" });
			columnMap.put(2, new String[] { "Ajaja", "I ja.", "no ty",
					"itd", "No 3", "No 2", "No 1"});//, "Absence", "Excused absence" });
		}
		private int identifier;

		private DataColumnName(int i) {
			this.identifier = i;
		}

		public String[] returnColumnName() {
			return columnMap.get(this.identifier);
		}
	}

	public TableModel(String[] columns, UserData userData) {
		this.COLUMNS = columns;
		this.userData = userData;
	}
	
	public void setRow(Integer row) {
		this.row = row;
	}
	
	@Override
	public int columnCount(QModelIndex arg0) {
		return COLUMNS.length;
	}

	@Override
	public Object data(QModelIndex index, int role) {

		if (role == Qt.ItemDataRole.DisplayRole) {
			switch (index.column()) {
			case 0:
				return index.row() + " Klik";
			case 1:
				return userData.getUserDataConteiner().get(0).getName() + index.column();
			case 2:
				return userData.getUserDataConteiner().get(0).getName() + index.column();
			case 3:
				return userData.getUserDataConteiner().get(0).getName() + index.column();
			case 4:
				return userData.getUserDataConteiner().get(0).getName() + index.column();
			case 5:
				return userData.getUserDataConteiner().get(0).getName() + index.column();
			case 6:
				return userData.getUserDataConteiner().get(0).getName() + index.column();
			default:
				throw new IndexOutOfBoundsException(
						"Column must be between 0 and 6.");
			}

		}
		if (role == Qt.ItemDataRole.CheckStateRole) {
			if (index.column() == 0)
				return !selected.get(index.row());
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
	public int rowCount(QModelIndex index) {
		return selected.size();
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

		
}