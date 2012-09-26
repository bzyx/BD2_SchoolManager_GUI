package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_EditContestType;
import pl.polsl.bd2.models.ContestTypeModel;

import com.trolltech.qt.gui.QDialog;

public class ContestTypeDialog extends QDialog {
	
	Ui_EditContestType ui;
	public ContestTypeDialog() {
		ui = new Ui_EditContestType();
		ui.setupUi(this);
		this.setWindowTitle("Dodawanie konkursu.");
		
		ContestTypeModel contestTypeModel = new ContestTypeModel();
		this.ui.listKonkursTyp.setModel(contestTypeModel);
	}

}
