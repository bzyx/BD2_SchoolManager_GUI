package pl.polsl.bd2.gui.forms;

import com.trolltech.qt.gui.*;

public class EditContestType extends QDialog {

    Ui_EditContestType ui = new Ui_EditContestType();

    public static void main(String[] args) {
        QApplication.initialize(args);

        EditContestType testEditContestType = new EditContestType();
        testEditContestType.show();

        QApplication.exec();
    }

    public EditContestType() {
        ui.setupUi(this);
    }

    public EditContestType(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
