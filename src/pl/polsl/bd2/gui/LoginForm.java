package pl.polsl.bd2.gui;

import pl.polsl.bd2.gui.forms.Ui_loginForm;

import com.trolltech.qt.gui.*;

public class LoginForm extends QWidget {

    Ui_loginForm ui = new Ui_loginForm();

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
    }
}
