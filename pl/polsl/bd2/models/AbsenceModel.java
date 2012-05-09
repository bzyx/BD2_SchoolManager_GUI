package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class AbsenceModel extends QAbstractTableModel{
	private final String[] COLUMNS = {tr("Date"), tr("How much hour"), tr("Hom much absence")};
	private List<Absence.AbsenceMock> dataContainer;

	public AbsenceModel(){
		this.dataContainer = new Absence().getAbsenceConteiner();
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
				return dataContainer.get(index.row()).getHowMuchLection();
			case 2:
				return dataContainer.get(index.row()).getHowMuchAbsence();
			case 3:
				return dataContainer.get(index.row()).getHowMuchUsprawiedliowioneAbsence();
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
	
	public class Absence{
		final public List<AbsenceMock> absenceConteiner;
		{
			absenceConteiner = new ArrayList<AbsenceMock>();
			absenceConteiner.add(new AbsenceMock(new Date(), 5, 4, 1));
			absenceConteiner.add(new AbsenceMock(new Date(), 6, 6, 0));
		}
		public class AbsenceMock{
			Date date;
			int howMuchLection;
			int howMuchAbsence;
			int howMuchUsprawiedliowioneAbsence;
			public AbsenceMock(Date date, int howMuchLection,
					int howMuchAbsence, int howMuchUsprawiedliowioneAbsence) {
				super();
				this.date = date;
				this.howMuchLection = howMuchLection;
				this.howMuchAbsence = howMuchAbsence;
				this.howMuchUsprawiedliowioneAbsence = howMuchUsprawiedliowioneAbsence;
			}
			public Date getDate() {
				return date;
			}
			public int getHowMuchLection() {
				return howMuchLection;
			}
			public int getHowMuchAbsence() {
				return howMuchAbsence;
			}
			public int getHowMuchUsprawiedliowioneAbsence() {
				return howMuchUsprawiedliowioneAbsence;
			}
			
		}
		public List<AbsenceMock> getAbsenceConteiner() {
			return absenceConteiner;
		}
		
	}
}
