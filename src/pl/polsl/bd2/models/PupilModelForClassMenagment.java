package pl.polsl.bd2.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.messageSystem.service.RoleService;
import pl.polsl.bd2.messageSystem.service.UczenService;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class PupilModelForClassMenagment extends QAbstractTableModel {
	RoleService roleService = (RoleService) SpringUtil.getBean("roleService");
	OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
	OsobaService osobaService = (OsobaService) SpringUtil.getBean("osobaService");
	UczenService uczenService = (UczenService) SpringUtil.getBean("uczenService");
	int actualClass = 0;
	List<List<Uczen>> pupilContainer2 = new ArrayList<List<Uczen>>();
	
	public PupilModelForClassMenagment(){
		
		List<Oddzial> listClass = new ArrayList<Oddzial>(this.oddzialService.findAll());
		for (Oddzial oddzial: listClass){
			List<Uczen> listaUczniow = new ArrayList<Uczen>(oddzial.getOddzial2uczen());
		
			try{
				this.pupilContainer2.add(listaUczniow);
			}catch(Exception e){
				
			}
		}
	}
	
	public enum PupilFields {
		NAME(0), VORNAME(1), STREET(2), CITY(3), MAIL(4), LOGIN(5);

		private PupilFields(Integer num) {
			this.num = num;
		}

		public Integer getNum() {
			return num;
		}

		private Integer num;
	}

	public enum PupilRoles {
		TO(Qt.ItemDataRole.UserRole + 0), PupilTEXT(
				Qt.ItemDataRole.UserRole + 1);

		private PupilRoles(Integer num) {
			this.num = num;
		}

		public Integer getNum() {
			return num;
		}

		private Integer num;
	}
	
	public void reContainer(){
		this.pupilContainer2.clear();	
		for (Oddzial oddzial: this.oddzialService.findAll()){			
			try{
				this.pupilContainer2.add(new ArrayList<Uczen>(oddzial.getOddzial2uczen()));
			}catch(Exception e){				
			}
		}
		this.layoutChanged.emit();
	}
	
	public void reClass(int newClass){
		this.actualClass = newClass;
		this.layoutChanged.emit();
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Qt.Orientation.Horizontal) {
				if (section == PupilFields.NAME.getNum())
					return tr("Imie: ");
				if (section == PupilFields.VORNAME.getNum())
					return tr("Nazwisko: ");
				if (section == PupilFields.STREET.getNum())
					return tr("Ulica: ");
				if (section == PupilFields.CITY.getNum())
					return tr("Miasto: ");
				if (section == PupilFields.MAIL.getNum())
					return tr("E-mail: ");
				if (section == PupilFields.LOGIN.getNum())
					return tr("Login: ");
			}
		}
		return null;
	}

	@Override
	public int columnCount(QModelIndex index) {
		return 6;
	}

	@Override
	public Object data(QModelIndex index, int role) {
		int row = index.row();
		int col = index.column();

		if (role == Qt.ItemDataRole.DisplayRole) {
			if (col == PupilFields.NAME.getNum())
				return this.pupilContainer2.get(this.actualClass).get(row).getOsoba().getImie();
			if (col == PupilFields.VORNAME.getNum())
				return pupilContainer2.get(this.actualClass).get(row).getOsoba().getNazwisko();
			if (col == PupilFields.STREET.getNum())
				return pupilContainer2.get(this.actualClass).get(row).getOsoba().getUlica();
			if (col == PupilFields.CITY.getNum())
				return pupilContainer2.get(this.actualClass).get(row).getOsoba().getMiasto();
			if (col == PupilFields.MAIL.getNum())
				return pupilContainer2.get(this.actualClass).get(row).getOsoba().getMail();
			if (col == PupilFields.LOGIN.getNum())
				return pupilContainer2.get(this.actualClass).get(row).getOsoba().getLogin();
			//TODO: dodac mail itp.
		}
/*
		if (role == Qt.ItemDataRole.ToolTipRole) {
			String msgText = PupilContainer.get(row).getTrescKomunikatu().getTekst();
			return msgText.substring(0, (msgText.length() > 300) ? 300
					: msgText.length());
		}

		if (role == PupilRoles.TO.getNum())
			return PupilContainer.get(row).getOsobaDo().getImie();
		if (role == PupilRoles.PupilTEXT.getNum())
			return PupilContainer.get(row).getTrescKomunikatu().getTekst();
*/
		return null;
	}

	@Override
	public int rowCount(QModelIndex index) {
		return pupilContainer2.get(this.actualClass).size();
	}

	@Override
	public boolean setData(QModelIndex index, Object value, int role) {
		if (role == Qt.ItemDataRole.EditRole) {
			/*if (index.row() >= 0 && index.row() <= pupilContainer.size()) {
				if (index.column() == PupilFields.UNREAD.getNum()) {
					PupilContainer.get(index.row())
							.setNieprzeczytany((Boolean) value);
					komunikatService.edit(PupilContainer.get(index.row()));
					this.dataChanged.emit(index, index);
					return true;
				}
			}*/
		}
		return false;
	}

	@Override
	public boolean removeRows(int position, int rows, QModelIndex parent) {
		beginRemoveRows(parent, position, position + rows - 1);
		Uczen uczen;
		for (int row = 0; row < rows; row++) {
			uczen = pupilContainer2.get(this.actualClass).get(position);
			uczenService.delete(uczen);
			osobaService.delete(uczen.getOsoba());
			pupilContainer2.get(this.actualClass).remove(position);
		}

		endRemoveRows();
		return true;
	}
	/*
	@Override
	public boolean removeRow(){
		
	}*/

}
