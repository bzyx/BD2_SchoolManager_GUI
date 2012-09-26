package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.presentation.BasePresenter;

public class ContestPresenter implements BasePresenter {
	private interface ContestSlots {
		final static String SHOW_ADD_CONTEST = "showAddDialog()";
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
		contestTypeDialog.show();
		//TODO: MJ Uwaga model tworzony razem z oknem (nie aktualizuje danych)
		//TODO: MJ Obsługa przycisku zapisz i cancel
	}
}
