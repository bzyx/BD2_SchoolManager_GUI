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

public class PupilModel extends QAbstractTableModel {
	private final String[] COLUMNS = { tr("Ab."), tr("Name"), tr("Vorname"),
			tr("Marks"), tr("Avg.") };
	private List<Pupil.PupilMock> dataContainer;

	public PupilModel(List<Pupil.PupilMock> pupilMock) {
		this.dataContainer = pupilMock;
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		// TODO Auto-generated method stub
		return COLUMNS.length;
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			switch (index.column()) {
			case 0:
				return dataContainer.get(index.row()).getName();
			case 1:
				return dataContainer.get(index.row()).getVorname();
			case 2:
				return dataContainer.get(index.row()).getRates();
			case 3:
				return dataContainer.get(index.row()).getAvg();
			case 4:
				return dataContainer.get(index.row()).getAvg();
			default:
				throw new IndexOutOfBoundsException(
						"Column must be between 0 and 6.");
			}

		}

		return null;
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		// TODO Auto-generated method stub
		return dataContainer.size();
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

	public static class Pupil {
		List<ClassPupilMock> classPupil;
		{
			this.classPupil = new ArrayList<ClassPupilMock>();
			List<DetailPupilMock> pupil1 = new ArrayList<DetailPupilMock>();
			List<DetailPupilMock> pupil2 = new ArrayList<DetailPupilMock>();
			List<PupilMock> class1 = new ArrayList<PupilMock>();
			List<PupilMock> class2 = new ArrayList<PupilMock>();
			pupil1.add(new DetailPupilMock(new Date(), 5, "kotem"));
			pupil1.add(new DetailPupilMock(new Date(), 3, "nudy"));
			pupil2.add(new DetailPupilMock(new Date(), 3, "nudy2"));
			pupil2.add(new DetailPupilMock(new Date(), 5, "kotem2"));
			class1.add(new PupilMock(pupil1, false, "£ukasz", "to æpun :P",
					"2, 2, 2, 2", "2"));
			class1.add(new PupilMock(pupil2, false, "Karol", "to mistrz :P",
					"5, 5, 5, 5", "5"));
			class2.add(new PupilMock(pupil2, false, "Karol", "to mistrz :P",
					"5, 5, 5, 5", "5"));
			class2.add(new PupilMock(pupil1, false, "£ukasz", "to æpun :P",
					"2, 2, 2, 2", "2"));
			this.classPupil.add(new ClassPupilMock(1, class1));
			this.classPupil.add(new ClassPupilMock(2, class2));
		}

		public Pupil() {
		}

		public class DetailPupilMock {
			Date date;
			int rate;
			String note;

			public DetailPupilMock(Date date, int rate, String note) {
				super();
				this.date = date;
				this.rate = rate;
				this.note = note;
			}

			public Date getDate() {
				return date;
			}

			public void setDate(Date date) {
				this.date = date;
			}

			public int getRate() {
				return rate;
			}

			public void setRate(int rate) {
				this.rate = rate;
			}

			public String getNote() {
				return note;
			}

			public void setNote(String note) {
				this.note = note;
			}

		}

		public class PupilMock {
			List<DetailPupilMock> detailsPupilMock;
			boolean absence;
			String name;
			String vorname;
			String rates;
			String avg;

			public PupilMock(List<DetailPupilMock> detailsPupilMock,
					boolean absence, String name, String vorname, String rates,
					String avg) {
				super();
				this.detailsPupilMock = detailsPupilMock;
				this.absence = absence;
				this.name = name;
				this.vorname = vorname;
				this.rates = rates;
				this.avg = avg;
			}

			public List<DetailPupilMock> getDetailsPupilMock() {
				return detailsPupilMock;
			}

			public void setDetailsPupilMock(
					List<DetailPupilMock> detailsPupilMock) {
				this.detailsPupilMock = detailsPupilMock;
			}

			public boolean isAbsence() {
				return absence;
			}

			public void setAbsence(boolean absence) {
				this.absence = absence;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getVorname() {
				return vorname;
			}

			public void setVorname(String vorname) {
				this.vorname = vorname;
			}

			public String getRates() {
				return rates;
			}

			public void setRates(String rates) {
				this.rates = rates;
			}

			public String getAvg() {
				return avg;
			}

			public void setAvg(String avg) {
				this.avg = avg;
			}
		}

		public class ClassPupilMock {
			int classPupil;
			List<PupilMock> pupil;

			public ClassPupilMock(int classPupil, List<PupilMock> puil) {
				super();
				this.classPupil = classPupil;
				this.pupil = puil;
			}

			public int getClassPupil() {
				return classPupil;
			}

			public void setClassPupil(int classPupil) {
				this.classPupil = classPupil;
			}

			public List<PupilMock> getPuil() {
				return pupil;
			}

			public void setPupil(List<PupilMock> puil) {
				this.pupil = puil;
			}
		}

		public List<ClassPupilMock> getClassPupil() {
			return classPupil;
		}

		public void setClassPupil(List<ClassPupilMock> classPupil) {
			this.classPupil = classPupil;
		}

	}

	public List<Pupil.PupilMock> getDataContainer() {
		return dataContainer;
	}

	public void setDataContainer(List<Pupil.PupilMock> dataContainer) {
		this.dataContainer = dataContainer;
	}
	
	

}
