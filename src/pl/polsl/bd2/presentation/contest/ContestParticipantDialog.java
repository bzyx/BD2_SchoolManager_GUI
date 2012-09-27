package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_CompetitionParticipant;
import pl.polsl.bd2.messageSystem.models.Konkurs;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.models.WynikKonkursu;
import pl.polsl.bd2.models.ContestListModel;
import pl.polsl.bd2.models.ContestResultListModel;
import pl.polsl.bd2.models.ContestTypeListModel;
import pl.polsl.bd2.models.StudentListModel;

import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.*;

public class ContestParticipantDialog extends QDialog {

	private Ui_CompetitionParticipant ui = new Ui_CompetitionParticipant();

	public Uczen getUczen() {
		return (Uczen) ui.comboStudentList.model().data(
				ui.comboStudentList.model().index(
				ui.comboStudentList.currentIndex(), 0), ItemDataRole.UserRole);
	}

	public void setUczen(Uczen uczen) {
		this.uczen = uczen;
	}

	public Konkurs getKonkurs() {
		return  (Konkurs) ui.comboBoxCompetitionList.model().data(
				ui.comboBoxCompetitionList.model().index(
				ui.comboBoxCompetitionList.currentIndex(), 0), ItemDataRole.UserRole);
	}

	public void setKonkurs(Konkurs konkurs) {
		this.konkurs = konkurs;
	}

	public WynikKonkursu getWynikKonkursu() {
		return (WynikKonkursu) ui.comboBoxResultList.model().data(
				ui.comboBoxResultList.model().index(
				ui.comboBoxResultList.currentIndex(), 0), ItemDataRole.UserRole);
	}

	public void setWynikKonkursu(WynikKonkursu wynikKonkursu) {
		this.wynikKonkursu = wynikKonkursu;
	}

	public String getDodatkoweInformacje() {
		return ui.textAdditionalInfo.toPlainText();
	}

	public void setDodatkoweInformacje(String dodatkoweInformacje) {
		this.dodatkoweInformacje = dodatkoweInformacje;
	}

	private Uczen uczen;
	private Konkurs konkurs;
	private WynikKonkursu wynikKonkursu;
	private String dodatkoweInformacje;

	public ContestParticipantDialog() {
		ui.setupUi(this);

		ui.comboStudentList.setModel(new StudentListModel());
		ui.comboBoxCompetitionList.setModel(new ContestListModel());
		ui.comboBoxResultList.setModel(new ContestResultListModel());
		
		ui.buttonBox.accepted.connect(this, "accept()");
		ui.buttonBox.rejected.connect(this, "reject()");
	}

}
