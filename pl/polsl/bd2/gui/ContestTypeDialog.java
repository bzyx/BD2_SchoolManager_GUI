package pl.polsl.bd2.gui;

import pl.polsl.bd2.ui.Ui_EditContestType;

import com.trolltech.qt.gui.QDialog;

public class ContestTypeDialog extends QDialog {
	
	Ui_EditContestType ui;
	public ContestTypeDialog() {
		ui = new Ui_EditContestType();
		ui.setupUi(this);
		this.setWindowTitle("Edycja typów konkursów");
	}

}
