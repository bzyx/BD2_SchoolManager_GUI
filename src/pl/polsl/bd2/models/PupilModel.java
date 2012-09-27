package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Ocena;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.Przedmiot;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.OcenaService;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.messageSystem.service.UczenService;

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
	private List<List<List<List<Ocena>>>> dataContainer2 = new ArrayList<List<List<List<Ocena>>>>();
	private List<List<Przedmiot>> przedmiotWithOddzial = new ArrayList<List<Przedmiot>>();
	private List<List<Uczen>> uczenWithOddzial = new ArrayList<List<Uczen>>();
	UczenService uczenService = (UczenService) SpringUtil.getBean("uczenService");
	OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
	OcenaService ocenaService = (OcenaService) SpringUtil.getBean("ocenaService");
	int currentClass = 0;

	public PupilModel() {
		int lOddzial = 0;
		for (Oddzial oddzial: this.oddzialService.findAll()){
			this.dataContainer2.add(new ArrayList<List<List<Ocena>>>());
			int lPrzedmiot = 0;
			this.przedmiotWithOddzial.add(new ArrayList<Przedmiot>(oddzial.getOddzial2przedmiot()));
			this.uczenWithOddzial.add(new ArrayList<Uczen>(oddzial.getOddzial2uczen()));
			for(Przedmiot przedmiot: oddzial.getOddzial2przedmiot()){
				this.dataContainer2.get(lOddzial).add(new ArrayList<List<Ocena>>());
				for(Uczen uczen: oddzial.getOddzial2uczen()){
					List<Ocena> listaOcen = new ArrayList<Ocena>(ocenaService.findBySubject(przedmiot, uczen));
					try{
						this.dataContainer2.get(lOddzial).get(lPrzedmiot).add(listaOcen);
					}catch(Exception e){
						
					}
					System.out.println(Integer.toString(this.dataContainer2.size()));
				}
				lPrzedmiot++;
			}
			lOddzial++;
		}
		
	}
	
	public void changeClass(int newClass){
		this.currentClass = newClass;
		this.layoutChanged.emit();
	}

	@Override
	public int columnCount(QModelIndex arg0) {
		// TODO Auto-generated method stub
		return COLUMNS.length;
	}

	@Override
	public Object data(QModelIndex index, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			try{
			switch (index.column()) {
			case 0:
				return index.row();
			case 1:
				return this.uczenWithOddzial.get(this.currentClass).get(index.row()).getOsoba().getImie();
			case 2:
				return this.uczenWithOddzial.get(this.currentClass).get(index.row()).getOsoba().getNazwisko();
			case 3:{
				String oceny = new String();
				for(Ocena ocena: this.dataContainer2.get(this.currentClass).get(0).get(index.row())){
					oceny += String.format("%d, ", ocena.getOcena());
				}
				return oceny;
			}
			case 4:{
				float avg = 0;
				float waga =0;
				for(Ocena ocena: this.dataContainer2.get(this.currentClass).get(0).get(index.row())){
					avg += ocena.getOcena()*ocena.getWaga();
					waga += ocena.getWaga();
				}
				return avg/waga;
			}
			default:
				throw new IndexOutOfBoundsException(
						"Column must be between 0 and 6.");
			}
			}catch(IndexOutOfBoundsException e){}

		}
		return null;
		
	}

	@Override
	public int rowCount(QModelIndex arg0) {
		// TODO Auto-generated method stub
		try{
			return dataContainer2.get(this.currentClass).get(0).size();
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
			class1.add(new PupilMock(pupil1, false, "�ukasz", "to �pun :P",
					"2, 2, 2, 2", "2"));
			class1.add(new PupilMock(pupil2, false, "Karol", "to mistrz :P",
					"5, 5, 5, 5", "5"));
			class2.add(new PupilMock(pupil2, false, "Karol", "to mistrz :P",
					"5, 5, 5, 5", "5"));
			class2.add(new PupilMock(pupil1, false, "�ukasz", "to �pun :P",
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
