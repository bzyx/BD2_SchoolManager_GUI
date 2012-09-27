package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_CompetitionParticipant;

import com.trolltech.qt.gui.*;

public class CompetitionParticipantDialog extends QDialog {

    Ui_CompetitionParticipant ui = new Ui_CompetitionParticipant();

    public static void main(String[] args) {
        QApplication.initialize(args);

        CompetitionParticipantDialog testCompetitionParticipant = new CompetitionParticipantDialog();
        testCompetitionParticipant.show();

        QApplication.exec();
    }

    public CompetitionParticipantDialog() {
        ui.setupUi(this);
    }

    public CompetitionParticipantDialog(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
