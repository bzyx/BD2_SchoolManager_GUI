package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.ApplicationMain;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Absencja;
import pl.polsl.bd2.messageSystem.models.Nauczyciel;
import pl.polsl.bd2.messageSystem.models.Ocena;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Przedmiot;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.AbsencjaService;
import pl.polsl.bd2.messageSystem.service.NauczycielService;
import pl.polsl.bd2.messageSystem.service.OcenaService;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.PrzedmiotService;
import pl.polsl.bd2.messageSystem.service.UczenService;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemFlag;
import com.trolltech.qt.core.Qt.ItemFlags;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class PupilModel extends QAbstractTableModel {
	private final String[] COLUMNS = { tr("Name"), tr("Vorname"),
			tr("Avg.       "), tr("Marks") };
	private List<List<List<List<Ocena>>>> dataContainer2 = new ArrayList<List<List<List<Ocena>>>>();
	private List<List<Przedmiot>> przedmiotWithOddzial = new ArrayList<List<Przedmiot>>();
	private List<List<Uczen>> uczenFromOddzial = new ArrayList<List<Uczen>>();
	UczenService uczenService = (UczenService) SpringUtil.getBean("uczenService");
	PrzedmiotService przedmiotService = (PrzedmiotService) SpringUtil.getBean("przedmiotService");
	OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
	OcenaService ocenaService = (OcenaService) SpringUtil.getBean("ocenaService");
	AbsencjaService absencjaService = (AbsencjaService) SpringUtil.getBean("absencjaService");
	NauczycielService nauczycielService = (NauczycielService) SpringUtil.getBean("nauczycielService");
	Nauczyciel teacher;
	int currentClass = 0;
	int changeSubject = 0;
	int row=0;

	public PupilModel(Nauczyciel teacher) {
		this.teacher = teacher;
		int lOddzial = 0;
		for (Oddzial oddzial: this.oddzialService.findAll()){
			this.dataContainer2.add(new ArrayList<List<List<Ocena>>>());
			int lPrzedmiot = 0;
			List<Przedmiot> przedmioty = new ArrayList<Przedmiot>(this.przedmiotService.findByClassTeacher(oddzial, this.teacher));
			this.przedmiotWithOddzial.add(przedmioty);
			this.uczenFromOddzial.add(new ArrayList<Uczen>(oddzial.getOddzial2uczen()));
			for(Przedmiot przedmiot: przedmioty){
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
	
	public void changeSubject(int changeSubject){
		this.changeSubject = changeSubject;
		this.layoutChanged.emit();
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
				return this.uczenFromOddzial.get(this.currentClass).get(index.row()).getOsoba().getImie();
			case 1:
				return this.uczenFromOddzial.get(this.currentClass).get(index.row()).getOsoba().getNazwisko();
			case 2:{
				if(this.dataContainer2.get(this.currentClass).get(this.changeSubject).get(index.row()).size() == 0) return "-";
				float avg = 0;
				float waga =0;
				for(Ocena ocena: this.dataContainer2.get(this.currentClass).get(this.changeSubject).get(index.row())){
					avg += ocena.getOcena()*ocena.getWaga();
					waga += ocena.getWaga();
				}
				return avg/waga;
			}
			case 3:{
				if(this.dataContainer2.get(this.currentClass).get(this.changeSubject).get(index.row()).size() == 0) return "-";
				String oceny = new String();
				for(Ocena ocena: this.dataContainer2.get(this.currentClass).get(this.changeSubject).get(index.row())){
					oceny += String.format("%d ( %.1f ), ", ocena.getOcena(), ocena.getWaga());
				}
				return oceny;
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
			return dataContainer2.get(this.currentClass).get(this.changeSubject).size();
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
	
	public void addRate(Ocena ocena, int row){
		this.dataContainer2.get(this.currentClass).get(this.changeSubject).get(row).add(ocena);
		this.refreshModel();
	}

	public List<List<List<List<Ocena>>>> getDataContainer2() {
		return dataContainer2;
	}

	public void setDataContainer2(List<List<List<List<Ocena>>>> dataContainer2) {
		this.dataContainer2 = dataContainer2;
	}
	
	public List<Uczen> getPupils(){
		return uczenFromOddzial.get(this.currentClass);
	}
	
	public List<Przedmiot> getPrzedmioty(){
		return przedmiotWithOddzial.get(this.currentClass);
	}

	public List<List<Uczen>> getUczenFromOddzial() {
		return uczenFromOddzial;
	}

	public void setUczenFromOddzial(List<List<Uczen>> uczenWithOddzial) {
		this.uczenFromOddzial = uczenWithOddzial;
	}
	
	

}
