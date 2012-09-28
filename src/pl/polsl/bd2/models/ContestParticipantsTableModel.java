package pl.polsl.bd2.models;

import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.UczestnikKonkursu;
import pl.polsl.bd2.messageSystem.service.UczestnikKonkursuService;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

public class ContestParticipantsTableModel extends QAbstractTableModel {
	private final String NAZWA = "Nazwa konkursu";
	private final String KTO = "Uczeń";
	private final String KTO_NAUCZYCIEL = "Nauczyciel";
	private final String WYNIK = "Wynik";
	private final String OPIS = "Szczegóły";
	
	UczestnikKonkursuService uczestnikKonkursuService = (UczestnikKonkursuService) SpringUtil.getBean("uczestnikKonkursuService");
	List<UczestnikKonkursu> container;
	
	public ContestParticipantsTableModel() {
		container = uczestnikKonkursuService.findAll();
	}
	
	@Override
	@QtBlockedSlot
	public int columnCount(QModelIndex arg0) {
		return 5;
	}

	@Override
	@QtBlockedSlot
	public Object data(QModelIndex index, int role) {
		int row = index.row();
		int col = index.column();
		
		if(role == ItemDataRole.DisplayRole){
			if (col == 0)
				return container.get(row).getKonkurs().getNazwa();
			if (col == 1)
				return String.format("%s %s ", container.get(row).getUczen().getOsoba().getImie(),
											   container.get(row).getUczen().getOsoba().getNazwisko());
			if (col == 2)
				return String.format("%s %s ", container.get(row).getNauczyciel().getOsoba().getImie(),
						   container.get(row).getNauczyciel().getOsoba().getNazwisko());
			if (col == 3)
				return container.get(row).getWynikKonkursu().getWynik();
			if (col == 4)
				return container.get(row).getDodatkoweInformacje();
		} 
		
		if (role == ItemDataRole.ToolTipRole){
			if (col == 1)
				return container.get(row).getUczen().getOddzial().getNazwa();
			if (col == 3)
				return container.get(row).getWynikKonkursu().getOpis();
		}
		
		if(role == ItemDataRole.UserRole){
				return container.get(row);
		}
		
		return null;
	}
	
	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Qt.Orientation.Horizontal) {
				if (section == 0)
					return NAZWA;
				if (section == 1)
					return KTO;
				if (section == 2)
					return KTO_NAUCZYCIEL;
				if (section == 3)
					return WYNIK;
				if (section == 4)
					return OPIS;
			}
		}
		return null;
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}

}
