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
				ui.comboStudentList.currentIndex(), 0), ItemDataRole.UserRole);
	}

	public void setUczen(Uczen uczen) {
	QModelIndex tmpIndex = studentListModel.index(0, 0);
		
		for (int i = 0; i < studentListModel.rowCount(); i++ ){
			if ( studentListModel.data(tmpIndex).equals(String.format("%s %s", uczen.getOsoba().getImie(),uczen.getOsoba().getNazwisko()))){
				ui.comboStudentList.setCurrentIndex(tmpIndex.row());
				break;
			}
			
			tmpIndex = studentListModel.index(i, 0);
		}
	}

	public Konkurs getKonkurs() {
		return  (Konkurs) ui.comboBoxCompetitionList.model().data(
				ui.comboBoxCompetitionList.model().index(
				ui.comboBoxCompetitionList.currentIndex(), 0), ItemDataRole.UserRole);
	}

	public void setKonkurs(Konkurs konkurs) {
	QModelIndex tmpIndex = contestListModel.index(0, 0);
		
		for (int i = 0; i < contestListModel.rowCount(); i++ ){
			if ( contestListModel.data(tmpIndex).equals(String.format("%s ( %s )", konkurs.getNazwa(), konkurs.getTypKonkursu().getNazwa()))){
				ui.comboBoxCompetitionList.setCurrentIndex(tmpIndex.row());
				break;
			}
			
			tmpIndex = contestListModel.index(i, 0);
		}
	}

	public WynikKonkursu getWynikKonkursu() {
		return (WynikKonkursu) ui.comboBoxResultList.model().data(
				ui.comboBoxResultList.model().index(
				ui.comboBoxResultList.currentIndex(), 0), ItemDataRole.UserRole);
	}

	public void setWynikKonkursu(WynikKonkursu wynikKonkursu) {
		QModelIndex tmpIndex = contestResultListModel.index(0, 0);
		
		for (int i = 0; i < contestResultListModel.rowCount(); i++ ){
			if ( contestResultListModel.data(tmpIndex).equals(wynikKonkursu.getWynik())){
				ui.comboBoxCompetitionList.setCurrentIndex(tmpIndex.row());
				break;
			}
			
			tmpIndex = contestResultListModel.index(i, 0);
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
	
	@Override
	public int exec() {
		studentListModel.makeUpdate();
		contestListModel.makeUpdate();
		contestResultListModel.makeUpdate();
		
		return super.exec();
	}

}
