package pl.polsl.bd2.gui;

import pl.polsl.bd2.gui.forms.Ui_LoginDialog;

import com.trolltech.qt.gui.*;

public class loginForm extends QWidget {

    Ui_LoginDialog ui = new Ui_LoginDialog();

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
