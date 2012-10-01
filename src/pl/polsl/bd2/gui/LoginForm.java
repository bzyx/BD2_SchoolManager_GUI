package pl.polsl.bd2.gui;

import pl.polsl.bd2.gui.forms.Ui_loginForm;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;

public class LoginForm extends QWidget {

	private final Ui_loginForm ui = new Ui_loginForm();

	public static void main(String[] args) {
		QApplication.initialize(args);

		LoginForm testloginForm = new LoginForm();
		testloginForm.show();
		QApplication.exec();
	}

	public LoginForm() {
		ui.setupUi(this);
	}

	public LoginForm(QWidget parent) {
		super(parent);
		ui.setupUi(this);
		ui.buttonBox.rejected.connect(this, "reject()");
		ui.buttonBox.accepted.connect(this, "accept()");
	}
	
	public Ui_loginForm getUi() {
		return ui;
	}

	public String getLogin() {
		return ui.lineEdit.text();
	}

	public String getPassword() {
		return ui.lineEdit_2.text();
	}
}
