package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_CompetitionParticipant;
import pl.polsl.bd2.models.ContestResultListModel;
import pl.polsl.bd2.models.ContestTypeListModel;
import pl.polsl.bd2.models.StudentListModel;

import com.trolltech.qt.gui.*;

public class ContestParticipantDialog extends QDialog {

    Ui_CompetitionParticipant ui = new Ui_CompetitionParticipant();

    public ContestParticipantDialog() {
        ui.setupUi(this);
        
        ui.comboStudentList.setModel(new StudentListModel());
        ui.comboBoxCompetitionList.setModel(new ContestResultListModel());
        ui.comboBoxResultList.setModel(new ContestTypeListModel());
    } 
    
    
}
