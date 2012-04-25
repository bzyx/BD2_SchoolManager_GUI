package pl.polsl.bd2.gui;

import pl.polsl.bd2.ui.Ui_loginForm;

import com.trolltech.qt.gui.*;

public class loginForm extends QWidget {

    Ui_loginForm ui = new Ui_loginForm();

    public static void main(String[] args) {
        QApplication.initialize(args);

        loginForm testloginForm = new loginForm();
        testloginForm.show();

        QApplication.exec();
    }

    public loginForm() {
        ui.setupUi(this);
    }

    public loginForm(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
