package pl.polsl.bd2.models;

import java.util.*;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.core.*;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;

public class TableModel extends QAbstractTableModel {
	private final String[] COLUMNS;// = { tr("Subject"), tr("Avg."), tr("No 5"),
	// tr("No 4"), tr("No 3"), tr("No 2"), tr("No 1"), tr("Absence"),
	// tr("Excused absence") };
	private final List<Boolean> selected = new ArrayList<Boolean>();
	private final List<String> name = new ArrayList<String>();
	private Integer row = 0;
	private final List<List<String>> nameList = new ArrayList<List<String>>();

	{
		selected.add(true);
		name.add("Nick");
		selected.add(true);
		name.add("Bzyku uu");
		nameList.add(name);
		List<String> name2 = new ArrayList<String>();
		name2.add("karol");
		name2.add("Misiek");
		nameList.add(name2);
	}

	public TableModel(String[] columns) {
		this.COLUMNS = columns;
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
				return nameList.get(row).get(index.row()) + index.column();
			case 2:
				return nameList.get(row).get(index.row()) + index.column();
			case 3:
				return nameList.get(row).get(index.row()) + index.column();
			case 4:
				return nameList.get(row).get(index.row()) + index.column();
			case 5:
				return nameList.get(row).get(index.row()) + index.column();
			case 6:
				return nameList.get(row).get(index.row()) + index.column();
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