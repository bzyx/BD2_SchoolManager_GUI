package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Nauczyciel;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Role;
import pl.polsl.bd2.messageSystem.service.NauczycielService;
import pl.polsl.bd2.messageSystem.service.RoleService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;

public class TeacherListModel extends QAbstractListModel {

	public TeacherListModel() {
		nauczycielService = (NauczycielService) SpringUtil.getBean("nauczycielService");
		container = new ArrayList<Nauczyciel>();
		container = nauczycielService.findAll();
	}
	
	public void makeUpdate() {
		container.clear();
		container = nauczycielService.findAll();
		reset();
	}

	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {

		if (role == Qt.ItemDataRole.DisplayRole)
			return String.format("%s %s", container.get(index.row()).getOsoba()
					.getImie(), container.get(index.row()).getOsoba()
					.getNazwisko());
		else if (role == Qt.ItemDataRole.UserRole)
			return container.get(index.row());
		return null;
	}
	
	@Override
	@QtBlockedSlot
	public boolean setData(QModelIndex arg0, Object arg1, int arg2) {
		String[] tuple = ((String) arg1).split(" ");
		RoleService roleService = (RoleService) SpringUtil.getBean("roleService");
		Role rola = roleService.findByName("Nauczyciel").get(0);
		NauczycielService nauczycielService = (NauczycielService) SpringUtil.getBean("nauczycielService");
		
		Nauczyciel entity = new Nauczyciel(new Osoba(tuple[0], tuple[1], "", "", "", "", "", rola), null);
		if (arg0 == null){
			container.add(entity);
			nauczycielService.save(entity);
			return super.setData(arg0, arg1, arg2);
		}
	
		if (arg0.row() > container.size()){
			container.add(entity);
			nauczycielService.save(entity);
		}
		else {
			Nauczyciel nauczyciel = (Nauczyciel)arg0.data(ItemDataRole.UserRole);
			tuple = ((String) arg1).split(" ");
			Osoba osoba = nauczyciel.getOsoba();
			osoba.setImie(tuple[0]);
			osoba.setNazwisko(tuple[1]);
			nauczyciel.setOsoba(osoba);
			
			nauczycielService.edit(nauczyciel);
			container.set(arg0.row(), nauczyciel);
		}
		return super.setData(arg0, arg1, arg2);
	}
	
	@Override
	@QtBlockedSlot
	public boolean removeRows(int arg0, int arg1, QModelIndex arg2) {
		nauczycielService.delete(container.get(arg0));
		container.remove(arg0);
		return super.removeRows(arg0, arg1, arg2);
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}

	private NauczycielService nauczycielService;
	private List<Nauczyciel> container;
	
}
