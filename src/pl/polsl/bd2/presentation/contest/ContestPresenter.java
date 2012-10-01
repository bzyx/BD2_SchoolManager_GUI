package pl.polsl.bd2.presentation.contest;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QSortFilterProxyModel;

import pl.polsl.bd2.gui.DictEditorWidget;
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
import pl.polsl.bd2.models.ContestResultListModel;
import pl.polsl.bd2.models.ContestTypeListModel;
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
	private DictEditorWidget dictEditorWidget;
	private ContestTypeListModel contestTypeListModel;
	private ContestResultListModel contestResultListModel;
	private QSortFilterProxyModel contestParticipantsTableModelSorted;

	public ContestPresenter(Ui_MainWindow view) {
		this.view = view;
		contestListModel = new ContestListModel();
		competitionParticipantDialog = new ContestParticipantDialog();
		contestParticipantsTableModel = new ContestParticipantsTableModel();
		dictEditorWidget = new DictEditorWidget();
		contestTypeListModel = new ContestTypeListModel();
		contestResultListModel = new ContestResultListModel();
		
		contestParticipantsTableModelSorted = new QSortFilterProxyModel();
		contestParticipantsTableModelSorted.setSourceModel(contestParticipantsTableModel);

		view.listContest.setModel(contestListModel);
		view.contestTable.setModel(contestParticipantsTableModelSorted);

		view.contestTable.setSortingEnabled(true);
		view.contestTable.resizeColumnsToContents();
		view.contestTable.horizontalHeader().setStretchLastSection(true);

		view.contestTable.verticalHeader().hide();
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
		view.buttonRemoveContestParticipant.clicked.connect(this,
				"removeContestParticipant()");
		view.buttonContestType.clicked.connect(this, "contestTypeDialog()");
		view.buttonContestResult.clicked.connect(this, "contestResultDialog()");
		view.buttonRemoveContest.clicked.connect(this, "removeContest()");
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

			makeUpdateOfView();
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
			konkursService.edit(konkurs);

			makeUpdateOfView();
		}
	}

	public void addContestParticipant() {
		KonfiguracjaService konfiguracjaService = (KonfiguracjaService) SpringUtil
				.getBean("konfiguracjaService");// .getLoggedOsoba();

		NauczycielService nauczycielService = (NauczycielService) SpringUtil
				.getBean("nauczycielService");
		Nauczyciel nauczyciel = nauczycielService.findById(konfiguracjaService
				.getLoggedOsoba().getIdOsoba());
		competitionParticipantDialog.updateDialog();
		competitionParticipantDialog.setEditMode(false);
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
		makeUpdateOfView();

	}

	public void editContestParticipant() {
		QModelIndex index = view.contestTable.currentIndex();
		uczestnikKonkursuService = (UczestnikKonkursuService) SpringUtil
				.getBean("uczestnikKonkursuService");

		if (Helpers.indexIsValid(index)) {
			competitionParticipantDialog.updateDialog();
			competitionParticipantDialog.setEditMode(true);

			contestParticipantsTableModel.updateModel();
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
			makeUpdateOfView();
		} else {
			QMessageBox.warning(null, "Błąd",
					"Należy wybrać uczestnika do edycji");
		}

	}

	public void contestTypeDialog() {
		dictEditorWidget.setWindowTitle("Typy konkursów");
		dictEditorWidget.setModel(contestTypeListModel);

		dictEditorWidget.show();
	}

	public void contestResultDialog() {
		dictEditorWidget.setWindowTitle("Wyniki konkursów");
		dictEditorWidget.setModel(contestResultListModel);

		dictEditorWidget.show();
	}

	@Override
	public void makeUpdateOfView() {
		view.listContest.setModel(null);
		contestListModel.makeUpdate();
		view.listContest.reset();
		view.listContest.setModel(contestListModel);

		view.contestTable.setModel(null);
		view.contestTable.reset();
		view.contestTable.setModel(new ContestParticipantsTableModel());
		view.contestTable.resizeColumnsToContents();
		view.contestTable.horizontalHeader().setStretchLastSection(true);

	}

	public void removeContest() {
		QModelIndex index = view.listContest.currentIndex();

		if (Helpers.indexIsValid(index)) {
			KonkursService konkursService = (KonkursService) SpringUtil
					.getBean("konkursService");

			konkursService.delete((Konkurs) index.data(ItemDataRole.UserRole));
			makeUpdateOfView();
		}
	}

	public void removeContestParticipant() {
		QModelIndex index = view.contestTable.currentIndex();

		if (Helpers.indexIsValid(index)) {
			UczestnikKonkursuService uczestnikKonkursuService = (UczestnikKonkursuService) SpringUtil
					.getBean("uczestnikKonkursuService");

			uczestnikKonkursuService.delete((UczestnikKonkursu) index
					.data(ItemDataRole.UserRole));
			makeUpdateOfView();
		}
	}
}
