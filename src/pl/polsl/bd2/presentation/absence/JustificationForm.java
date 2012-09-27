package pl.polsl.bd2.presentation.absence;


import pl.polsl.bd2.gui.MainWindow;
import pl.polsl.bd2.gui.forms.Ui_JustificationDialog;
import pl.polsl.bd2.models.AbsenceModel;
import pl.polsl.bd2.models.JustificationModel;
import com.trolltech.qt.gui.*;

public class JustificationForm extends QDialog {
	//TODO MJ Hmmm nie wiem co to robi ani gdzie jest to użyte
    Ui_JustificationDialog ui = new Ui_JustificationDialog();
    JustificationModel model;
    AbsenceModel.Absence.AbsenceMock absence;

    public static void main(String[] args) {
        QApplication.initialize(args);

        //justificationForm testjustificationWidget = new justificationForm();
        //testjustificationWidget.show();

        QApplication.exec();
    }

    public JustificationForm(MainWindow parrent, AbsenceModel.Absence.AbsenceMock absence, JustificationModel model) {
        ui.setupUi(this);
        this.model = model;
        this.absence = absence;
        //ui.labelDateJustification.setText(absence.getDate().toString());
        ui.spinBoxHowMuchLection.setMaximum(absence.getHowMuchAbsence());
        ui.spinBoxHowMuchLection.setValue(absence.getHowMuchAbsence());
        ui.buttonBoxJustification.accepted.connect(this, "addJustification()");
        ui.buttonBoxJustification.rejected.connect(this, "close()");
    }

    public JustificationForm(QWidget parent) {
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
