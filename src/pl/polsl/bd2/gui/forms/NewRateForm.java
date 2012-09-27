package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.gui.*;

public class NewRateForm extends QWidget {

    Ui_NewRateForm ui = new Ui_NewRateForm();

    public static void main(String[] args) {
        QApplication.initialize(args);

        NewRateForm testNewRateForm = new NewRateForm();
        testNewRateForm.show();

        QApplication.exec();
    }

    public NewRateForm() {
        ui.setupUi(this);
    }

    public NewRateForm(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
