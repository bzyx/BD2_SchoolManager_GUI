package pl.polsl.bd2.gui;

import pl.polsl.bd2.models.AbsenceModel;
import pl.polsl.bd2.models.JustificationModel;
import pl.polsl.bd2.ui.Ui_justificationForm;
import com.trolltech.qt.gui.*;

public class justificationForm extends QDialog {

    Ui_justificationForm ui = new Ui_justificationForm();
    JustificationModel model;
    AbsenceModel.Absence.AbsenceMock absence;

    public static void main(String[] args) {
        QApplication.initialize(args);

        //justificationForm testjustificationWidget = new justificationForm();
        //testjustificationWidget.show();

        QApplication.exec();
    }

    public justificationForm(MainWindow parrent, AbsenceModel.Absence.AbsenceMock absence, JustificationModel model) {
        ui.setupUi(this);
        this.model = model;
        this.absence = absence;
        ui.labelDateJustification.setText(absence.getDate().toString());
        ui.spinBoxHowMuchLection.setMaximum(absence.getHowMuchAbsence());
        ui.spinBoxHowMuchLection.setValue(absence.getHowMuchAbsence());
        ui.buttonBoxJustification.accepted.connect(this, "addJustification()");
        ui.buttonBoxJustification.rejected.connect(this, "close()");
    }

    public justificationForm(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
    
    @SuppressWarnings("unused")
	private void addJustification(){
    	model.addJustification(absence.getDate(), 
    			ui.spinBoxHowMuchLection.value(), 
    			ui.plainTextEditJustification.toPlainText());
    	this.close();
    	
    }
}