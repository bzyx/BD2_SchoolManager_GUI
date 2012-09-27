package pl.polsl.bd2.presentation.contest;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Konkurs;
import pl.polsl.bd2.messageSystem.models.Nauczyciel;
import pl.polsl.bd2.messageSystem.models.UczestnikKonkursu;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.KonkursService;
import pl.polsl.bd2.messageSystem.service.NauczycielService;
import pl.polsl.bd2.messageSystem.service.UczestnikKonkursuService;
import pl.polsl.bd2.models.ContestParticipantsTableModel;
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
	private ContestParticipantDialog competitionParticipantDialog;
	private UczestnikKonkursuService uczestnikKonkursuService;
	private ContestParticipantsTableModel contestParticipantsTableModel;

	public ContestPresenter(Ui_MainWindow view) {
		this.view = view;
		contestListModel = new ContestListModel();
		competitionParticipantDialog = new ContestParticipantDialog();
		contestParticipantsTableModel = new ContestParticipantsTableModel();

		view.listContest.setModel(contestListModel);
		view.contestTable.setModel(contestParticipantsTableModel);

		view.contestTable.resizeColumnsToContents();
		view.contestTable.horizontalHeader().setStretchLastSection(true);
	}

	@Override
	public void connectSlots() {
		view.buttonRegisterContest.clicked.connect(this,
				ContestSlots.SHOW_ADD_CONTEST);
		view.buttonEditContestType.clicked.connect(this,
				ContestSlots.SHOW_EDIT_CONTEST);
		view.buttonAddContestParticipant.clicked.connect(this,
				"addContestParticipant()");
		view.buttonEditConestParticipant.clicked.connect(this,
				"editContestParticipant()");
	}

	@SuppressWarnings("unused")
	private void showAddDialog() {
		if (contestTypeDialog == null) {
			contestTypeDialog = new ContestTypeDialog();
		}
		contestTypeDialog.setAddMode(true);

		if (contestTypeDialog.exec() == QDialog.DialogCode.Accepted.value()) {
			String nazwaKonkursu = contestTypeDialog.getNazwaKonkursu().trim();

			if (nazwaKonkursu.isEmpty()) {
				QMessageBox
						.warning(null, "Błąd", "Należy podać nazwę konkrusu");
				return;
			}
			KonkursService konkursService = (KonkursService) SpringUtil
					.getBean("konkursService");
			konkursService.save(new Konkurs(contestTypeDialog.getTypKonkursu(),
					nazwaKonkursu));

			view.contestTable.setModel(null);
			contestListModel.makeUpdate();
			view.contestTable.reset();
			view.contestTable.setModel(new ContestParticipantsTableModel());
		}

	}

	@SuppressWarnings("unused")
	private void editContest() {
		if (contestTypeDialog == null) {
			contestTypeDialog = new ContestTypeDialog();
		}

		if (!Helpers.indexIsValid(view.listContest.currentIndex())) {
			QMessageBox.warning(null, "Błąd",
					"Należy wybrać konkurs do edycji.");
			return;
		}
		contestTypeDialog.setAddMode(false);
		Konkurs konkurs = (Konkurs) contestListModel.data(
				view.listContest.currentIndex(), ItemDataRole.UserRole);

		contestTypeDialog.setNazwaKonkursu(konkurs.getNazwa());
		contestTypeDialog.setTypKonkursu(konkurs.getTypKonkursu());

		if (contestTypeDialog.exec() == QDialog.DialogCode.Accepted.value()) {
			String nazwaKonkursu = contestTypeDialog.getNazwaKonkursu().trim();

			if (nazwaKonkursu.isEmpty()) {
				QMessageBox
						.warning(null, "Błąd", "Należy podać nazwę konkrusu");
				return;
			}
			KonkursService konkursService = (KonkursService) SpringUtil
					.getBean("konkursService");

			konkurs.setNazwa(nazwaKonkursu);
			konkurs.setTypKonkursu(contestTypeDialog.getTypKonkursu());

			view.contestTable.setModel(null);
			konkursService.edit(konkurs);
			contestListModel.makeUpdate();
			view.contestTable.setModel(contestParticipantsTableModel);
		}
	}

	public void addContestParticipant() {
		KonfiguracjaService konfiguracjaService = (KonfiguracjaService) SpringUtil
				.getBean("konfiguracjaService");// .getLoggedOsoba();

		NauczycielService nauczycielService = (NauczycielService) SpringUtil
				.getBean("nauczycielService");
		Nauczyciel nauczyciel = nauczycielService.findById(konfiguracjaService
				.getLoggedOsoba().getIdOsoba());
		if (competitionParticipantDialog.exec() == QDialog.DialogCode.Accepted
				.value()) {
			uczestnikKonkursuService = (UczestnikKonkursuService) SpringUtil
					.getBean("uczestnikKonkursuService");

			uczestnikKonkursuService.save(new UczestnikKonkursu(
					competitionParticipantDialog.getUczen(), nauczyciel,
					competitionParticipantDialog.getKonkurs(),
					competitionParticipantDialog.getWynikKonkursu(),
					competitionParticipantDialog.getDodatkoweInformacje()));
		}
		view.contestTable.setModel(null);
		contestListModel.makeUpdate();
		view.contestTable.reset();
		view.contestTable.setModel(new ContestParticipantsTableModel());

	}

	public void editContestParticipant() {
		QModelIndex index = view.contestTable.currentIndex();

		if (Helpers.indexIsValid(index)) {
			UczestnikKonkursu uczestnikKonkursu = (UczestnikKonkursu) contestParticipantsTableModel
					.data(index, ItemDataRole.UserRole);

			competitionParticipantDialog.setUczen(uczestnikKonkursu.getUczen());
			competitionParticipantDialog.setKonkurs(uczestnikKonkursu
					.getKonkurs());
			competitionParticipantDialog.setWynikKonkursu(uczestnikKonkursu
					.getWynikKonkursu());
			competitionParticipantDialog
					.setDodatkoweInformacje(uczestnikKonkursu
							.getDodatkoweInformacje());

			if (competitionParticipantDialog.exec() == QDialog.DialogCode.Accepted
					.value()) {

				uczestnikKonkursu.setUczen(competitionParticipantDialog
						.getUczen());
				uczestnikKonkursu.setKonkurs(competitionParticipantDialog
						.getKonkurs());
				uczestnikKonkursu.setWynikKonkursu(competitionParticipantDialog
						.getWynikKonkursu());
				uczestnikKonkursu
						.setDodatkoweInformacje(competitionParticipantDialog
								.getDodatkoweInformacje());

				uczestnikKonkursuService.edit(uczestnikKonkursu);
			}

			view.contestTable.setModel(null);
			contestListModel.makeUpdate();
			view.contestTable.reset();
			view.contestTable.setModel(new ContestParticipantsTableModel());
		}

	}
}
