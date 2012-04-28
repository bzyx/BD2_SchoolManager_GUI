package pl.polsl.bd2.gui;

import java.util.*;

import com.trolltech.qt.QVariant;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.core.*;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;

public class TableModel extends QAbstractTableModel {
	private final List<Boolean> selected = new ArrayList<Boolean>();
	private final List<String> name = new ArrayList<String>();

	public TableModel(QObject parent) {
		super(parent);
	}
	public TableModel(){}

	@Override
	public int columnCount(QModelIndex arg0){
		return 2;
	}

	@Override
	public Object data(QModelIndex index, int role)
	{
		Object returnObject = new Object();

		if (role == Qt.ItemDataRole.DisplayRole || role == Qt.ItemDataRole.EditRole)
		{
			switch (index.column())
			{
			case 0:
				return new QVariant();
			case 1:
				returnObject = name.get(index.row());
				break;
			/*case 2:
				returnObject = description.get(index.row());
				break;
			*/default:
				throw new IndexOutOfBoundsException("Column must be between 0 and 2.");
			}

			return returnObject;
		} else if (role == Qt.ItemDataRole.CheckStateRole)
		{
			if (index.column() == 0) 
			{ 
				return selected.get(index.row()); 
			}

		}
		return super.data(index);
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role)
	{
		if (role == Qt.ItemDataRole.DisplayRole || role == Qt.ItemDataRole.EditRole)
		{
			if (orientation == Orientation.Horizontal)
			{
				String headerName = "";
				switch (section)
				{
				case 0:
					headerName = "Object";
					break;
				case 1:
					headerName = "Ratings";
					break;
				default:
					headerName = "";
					break;
				}
				return headerName;
			} else
			{
				return null;
			}
		} else
		{
			return super.headerData(section, orientation, role);
		}
	}

	public void setData(QModelIndex index, QVariant value, int role)
	{
		if (role == Qt.ItemDataRole.DisplayRole || role == Qt.ItemDataRole.EditRole)
		{
			if (index.column() == 0)
			{
				boolean valueToSet = value != null;
				selected.set(index.row(), valueToSet);
			}
		}
	}

	@Override
	public int rowCount(QModelIndex index)
	{
		return selected.size();
	}

	@Override
	public ItemFlags flags(QModelIndex index)
	{
		if (index.column() == 0)
		{
			ItemFlags itemFlags = new ItemFlags(new ItemFlag[]
					{ ItemFlag.ItemIsSelectable, ItemFlag.ItemIsEditable, ItemFlag.ItemIsEnabled, ItemFlag.ItemIsUserCheckable });

			return itemFlags;
		}
		return new ItemFlags(0);
	}

}
