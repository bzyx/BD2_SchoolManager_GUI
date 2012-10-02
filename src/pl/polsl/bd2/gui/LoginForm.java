package pl.polsl.bd2.gui;

import pl.polsl.bd2.gui.forms.Ui_loginForm;

import com.trolltech.qt.gui.QDialog;

public class LoginForm extends QDialog {

	private final Ui_loginForm ui = new Ui_loginForm();

	public LoginForm() {
		ui.setupUi(this);
		ui.buttonBox.accepted.connect(this, "accept()");
		ui.buttonBox.rejected.connect(this, "reject()");
	}
	
	public Ui_loginForm getUi() {
		return ui;
	}

	public String getLogin() {
		return ui.lineEdit.text().trim();
	}

	public String getPassword() {
		return ui.lineEdit_2.text().trim();
	}
}