package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class DetailsDataModel extends QAbstractTableModel {
	
	private final String[] COLUMNS;
	private final List<Boolean> selected = new ArrayList<Boolean>();
	private List<UserData.DetailsDataMock> dataContainer;

	public DetailsDataModel() {
		this.COLUMNS = DataColumnName.valueOf("DataDetailsTable").returnColumnName();
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
				return dataContainer.get(index.row()).getDate();
			case 1:
				return dataContainer.get(index.row()).getRate();
			case 2:
				return dataContainer.get(index.row()).getInformation();
			case 3:
				return dataContainer.get(index.row()).getNote();
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
			columnMap.put(2, new String[] { "Date", "Rate", "Information", "Note"});
		}
		private int identifier;

		private DataColumnName(int i) {
			this.identifier = i;
		}

		public String[] returnColumnName() {
			return columnMap.get(this.identifier);
		}
	}

	public void setDetailsDataContainer(List<UserData.DetailsDataMock> dataContainer) {
		this.dataContainer = dataContainer;
		for(int i = 0; i < dataContainer.size(); i++){
			this.selected.add(true);
		}
	}
}
