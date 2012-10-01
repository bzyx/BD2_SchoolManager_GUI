package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_CompetitionParticipant;
import pl.polsl.bd2.messageSystem.models.Konkurs;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.models.WynikKonkursu;
import pl.polsl.bd2.models.ContestListModel;
import pl.polsl.bd2.models.ContestResultListModel;
import pl.polsl.bd2.models.StudentListModel;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.*;

public class ContestParticipantDialog extends QDialog {

	private Ui_CompetitionParticipant ui = new Ui_CompetitionParticipant();

	public Uczen getUczen() {
		return (Uczen) ui.comboStudentList.model().data(
				ui.comboStudentList.model().index(
						ui.comboStudentList.currentIndex(), 0),
				ItemDataRole.UserRole);
	}

	public void setUczen(Uczen uczen) {
		QModelIndex tmpIndex = studentListModel.index(0, 0);

		for (int i = 0; i < studentListModel.rowCount(); i++) {
			Uczen uczenModel = (Uczen) studentListModel.data(tmpIndex,
					ItemDataRole.UserRole);

			if (uczenModel.getIdUczen() == uczen.getIdUczen()) {
				ui.comboStudentList.setCurrentIndex(tmpIndex.row());
				break;
			}

			tmpIndex = studentListModel.index(i, 0);
		}
	}

	public Konkurs getKonkurs() {
		return (Konkurs) ui.comboBoxCompetitionList.model().data(
				ui.comboBoxCompetitionList.model().index(
						ui.comboBoxCompetitionList.currentIndex(), 0),
				ItemDataRole.UserRole);
	}

	public void setKonkurs(Konkurs konkurs) {
		Konkurs konkurs_;
		for (int i = 0; i < contestListModel.rowCount(); i++) {
			konkurs_ = (Konkurs) contestListModel.data(
					contestListModel.index(i, 0), ItemDataRole.UserRole);

			if (konkurs_.getIdKonkurs() == konkurs.getIdKonkurs()) {
				ui.comboBoxCompetitionList.setCurrentIndex(i);
				break;
			}
		}
	}

	public WynikKonkursu getWynikKonkursu() {
		return (WynikKonkursu) ui.comboBoxResultList.model().data(
				ui.comboBoxResultList.model().index(
						ui.comboBoxResultList.currentIndex(), 0),
				ItemDataRole.UserRole);
	}

	public void setWynikKonkursu(WynikKonkursu wynikKonkursu) {
		WynikKonkursu wynKonkursu_;
		for (int i = 0; i < contestResultListModel.rowCount(); i++) {
			wynKonkursu_ = (WynikKonkursu) contestResultListModel.data(
					contestResultListModel.index(i, 0), ItemDataRole.UserRole);

			if (wynKonkursu_.getIdWynikKonkurs() == wynikKonkursu
					.getIdWynikKonkurs()) {
				ui.comboBoxResultList.setCurrentIndex(i);
				break;
			}
		}

	}

	public String getDodatkoweInformacje() {
		return ui.textAdditionalInfo.toPlainText();
	}

	public void setDodatkoweInformacje(String dodatkoweInformacje) {
		ui.textAdditionalInfo.setText(dodatkoweInformacje);
	}

	private StudentListModel studentListModel;
	private ContestListModel contestListModel;
	private ContestResultListModel contestResultListModel;

	public ContestParticipantDialog() {
		ui.setupUi(this);
		studentListModel = new StudentListModel();
		contestListModel = new ContestListModel();
		contestResultListModel = new ContestResultListModel();

		ui.comboStudentList.setModel(studentListModel);
		ui.comboBoxCompetitionList.setModel(contestListModel);
		ui.comboBoxResultList.setModel(contestResultListModel);

		ui.buttonBox.accepted.connect(this, "accept()");
		ui.buttonBox.rejected.connect(this, "reject()");
	}

	public void updateDialog() {
		studentListModel.makeUpdate();
		contestListModel.makeUpdate();
		contestResultListModel.makeUpdate();
	}

	public void setEditMode(Boolean editMode) {
		if (editMode == false) {
			ui.comboStudentList.setCurrentIndex(0);
			ui.comboBoxCompetitionList.setCurrentIndex(0);
			ui.comboBoxResultList.setCurrentIndex(0);
			ui.comboBoxResultList.setEnabled(false);
			ui.comboStudentList.setEnabled(true);
		} else {
			ui.comboBoxResultList.setEnabled(true);
			ui.comboStudentList.setEnabled(false);
		}
	}

}
