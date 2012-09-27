package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_EditContestType;
import pl.polsl.bd2.messageSystem.models.TypKonkursu;
import pl.polsl.bd2.models.ContestTypeModel;

import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.QDialog;

public class ContestTypeDialog extends QDialog {
	
	Ui_EditContestType ui;
	ContestTypeModel contestTypeModel;
	
	public ContestTypeDialog() {
		ui = new Ui_EditContestType();
		ui.setupUi(this);
		this.setWindowTitle("Dodawanie konkursu.");
		
		contestTypeModel = new ContestTypeModel();
		this.ui.listKonkursTyp.setModel(contestTypeModel);
		this.ui.listKonkursTyp.setCurrentIndex(contestTypeModel.index(0, 0));
		
		ui.buttonBox.accepted.connect(this, "accept()");
		ui.buttonBox.rejected.connect(this, "reject()");
		
	}
	
	public String getNazwaKonkursu(){
		return ui.lineEditCompetiononType.text();
	}
	
	public TypKonkursu getTypKonkursu(){
		return (TypKonkursu) contestTypeModel.data(ui.listKonkursTyp.currentIndex(), ItemDataRole.UserRole);
	}
	
}
