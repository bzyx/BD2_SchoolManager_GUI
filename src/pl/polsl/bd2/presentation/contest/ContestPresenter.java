package pl.polsl.bd2.presentation.contest;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Konkurs;
import pl.polsl.bd2.messageSystem.service.KonkursService;
import pl.polsl.bd2.presentation.BasePresenter;

public class ContestPresenter implements BasePresenter {
	private interface ContestSlots {
		final static String SHOW_ADD_CONTEST = "showAddDialog()";
		final static String ADD_NEW_CONTEST = "addNewContest()";
	}

	private Ui_MainWindow view;
	private ContestTypeDialog contestTypeDialog;
	//TODO: MJ Dodanie dodawania nowychh typów konkursów
	//TODO: MJ Edycja konkursów
	//TODO: MJ Dodawania nowych stopni wyników konkrusu
	//TODO: MJ Uwalić to "o konkursie"
	//TODO: MJ Połączenie ucznia z konkursem (b. ważne)!!
	//TODO: MJ Edycja istniejących konkursów
	
	public ContestPresenter(Ui_MainWindow view) {
		this.view = view;
	}

	@Override
	public void connectSlots() {
		view.buttonRegisterContest.clicked
				.connect(this, ContestSlots.SHOW_ADD_CONTEST);
	}

	@SuppressWarnings("unused")
	private void showAddDialog() {
		if (contestTypeDialog == null) {
			contestTypeDialog = new ContestTypeDialog();
		}
		
		if (contestTypeDialog.exec() == QDialog.DialogCode.Accepted.value()){
			String nazwaKonkursu = contestTypeDialog.getNazwaKonkursu().trim();
			
			if (nazwaKonkursu.isEmpty()){
				QMessageBox.warning(null, "Błąd", "Należy podać nazwę konkrusu");
				return;
			}
			KonkursService konkursService = (KonkursService) SpringUtil.getBean("konkursService");
			konkursService.save(new Konkurs(contestTypeDialog.getTypKonkursu(), nazwaKonkursu));
			
		}
		
		
		//konkursService.save(new Konkurs(arg0, arg1))
		//TODO: MJ Uwaga model tworzony razem z oknem (nie aktualizuje danych)
		//TODO: MJ Obsługa przycisku zapisz i cancel
	}
}
