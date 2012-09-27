package pl.polsl.bd2.presentation.contest;

import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Konkurs;
import pl.polsl.bd2.messageSystem.service.KonkursService;
import pl.polsl.bd2.models.ContestListModel;
import pl.polsl.bd2.presentation.BasePresenter;


public class ContestPresenter implements BasePresenter {
	private interface ContestSlots {
		final static String SHOW_ADD_CONTEST = "showAddDialog()";
		final static String SHOW_EDIT_CONTEST = "editContest()";
	}

	private Ui_MainWindow view;
	private ContestTypeDialog contestTypeDialog;
	private ContestListModel contestListModel;
	private CompetitionParticipantDialog competitionParticipant;
	//TODO: MJ Dodawania nowych stopni wyników konkrusu
	//TODO: MJ Połączenie ucznia z konkursem (b. ważne)!!
	
	public ContestPresenter(Ui_MainWindow view) {
		this.view = view;
		contestListModel = new ContestListModel();
		
		view.listContest.setModel(contestListModel);
	}

	@Override
	public void connectSlots() {
		view.buttonRegisterContest.clicked
				.connect(this, ContestSlots.SHOW_ADD_CONTEST);
		view.buttonEditContestType.clicked.connect(this, ContestSlots.SHOW_EDIT_CONTEST);
		view.buttonAddContestParticipant.clicked.connect(this, "addContestParticipant()");
	}

	@SuppressWarnings("unused")
	private void showAddDialog() {
		if (contestTypeDialog == null) {
			contestTypeDialog = new ContestTypeDialog();
		}
		contestTypeDialog.setAddMode(true);
		
		if (contestTypeDialog.exec() == QDialog.DialogCode.Accepted.value()){
			String nazwaKonkursu = contestTypeDialog.getNazwaKonkursu().trim();
			
			if (nazwaKonkursu.isEmpty()){
				QMessageBox.warning(null, "Błąd", "Należy podać nazwę konkrusu");
				return;
			}
			KonkursService konkursService = (KonkursService) SpringUtil.getBean("konkursService");
			konkursService.save(new Konkurs(contestTypeDialog.getTypKonkursu(), nazwaKonkursu));
			contestListModel.makeUpdate();
		}
		
	}
	
	@SuppressWarnings("unused")		
	private void editContest(){
		if (contestTypeDialog == null) {
			contestTypeDialog = new ContestTypeDialog();
		}
		
		if (!Helpers.indexIsValid(view.listContest.currentIndex())){
			QMessageBox.warning(null, "Błąd", "Należy wybrać konkurs do edycji.");
			return;
		}
		contestTypeDialog.setAddMode(false);
		Konkurs konkurs = (Konkurs) contestListModel.data(view.listContest.currentIndex(), ItemDataRole.UserRole);
		
		contestTypeDialog.setNazwaKonkursu(konkurs.getNazwa());
		contestTypeDialog.setTypKonkursu(konkurs.getTypKonkursu());
		
		if (contestTypeDialog.exec() == QDialog.DialogCode.Accepted.value()){
			String nazwaKonkursu = contestTypeDialog.getNazwaKonkursu().trim();
			
			if (nazwaKonkursu.isEmpty()){
				QMessageBox.warning(null, "Błąd", "Należy podać nazwę konkrusu");
				return;
			}
			KonkursService konkursService = (KonkursService) SpringUtil.getBean("konkursService");
			
			konkurs.setNazwa(nazwaKonkursu);
			konkurs.setTypKonkursu(contestTypeDialog.getTypKonkursu());
			
			konkursService.edit(konkurs);
			contestListModel.makeUpdate();
		}
	}
	
	public void addContestParticipant(){
		
	}
}
