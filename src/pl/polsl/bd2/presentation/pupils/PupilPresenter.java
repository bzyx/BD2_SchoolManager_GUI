package pl.polsl.bd2.presentation.pupils;

import java.util.Date;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;

import pl.polsl.bd2.enums.MessageFields;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Nauczyciel;
import pl.polsl.bd2.messageSystem.models.Ocena;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Uwaga;
import pl.polsl.bd2.messageSystem.service.KomunikatService;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.NauczycielService;
import pl.polsl.bd2.messageSystem.service.OcenaService;
import pl.polsl.bd2.messageSystem.service.PrzedmiotService;
import pl.polsl.bd2.messageSystem.service.UwagaService;
import pl.polsl.bd2.models.NoteModel;
import pl.polsl.bd2.models.PupilModel;
import pl.polsl.bd2.presentation.BasePresenter;
import pl.polsl.bd2.presentation.data.DataSlots;

public class PupilPresenter implements BasePresenter {

	private Ui_MainWindow view;
	private PupilModel pupilModel;
	private NoteModel noteModel;
	private KonfiguracjaService konfiguracjaService;
	private NauczycielService nauczycielService;
	private OcenaService ocenaService;
	private UwagaService uwagaService;

	public PupilPresenter(Ui_MainWindow view) {
		this.view = view;

		this.konfiguracjaService = (KonfiguracjaService) SpringUtil
				.getContext().getBean("konfiguracjaService");
		this.nauczycielService = (NauczycielService) SpringUtil.getContext()
				.getBean("nauczycielService");
		this.ocenaService = (OcenaService) SpringUtil.getContext().getBean(
				"ocenaService");
		this.uwagaService = (UwagaService) SpringUtil.getContext().getBean(
				"uwagaService");

	}

	@Override
	public void connectSlots() {
		view.comboBoxClass.currentIndexChanged.connect(this,
				PupilSlots.CHANGE_CLASS);
		view.pushButtonAddRate.clicked.connect(this, PupilSlots.ADD_RATE);
		view.pushButtonAddNote.clicked.connect(this, PupilSlots.ADD_NOTE);
		view.tableUsers.selectionModel().currentRowChanged.connect(this,
				PupilSlots.CHANGE_DETAILS_USER);
	}

	public void initModel() {
		this.pupilModel = new PupilModel();
		view.tableUsers.setModel(this.pupilModel);
		view.tableUsers.resizeColumnsToContents();
		view.tableUsers.horizontalHeader().setStretchLastSection(true);
		view.tableUsers.verticalHeader().hide();
		this.noteModel = new NoteModel();
		view.tableDetailUsers.setModel(this.noteModel);
		view.tableDetailUsers.resizeColumnsToContents();
		view.tableDetailUsers.horizontalHeader().setStretchLastSection(true);
		view.tableDetailUsers.verticalHeader().hide();
		this.pupilModel.initDetail(this.noteModel);
	}

	@SuppressWarnings("unused")
	private void changeClass() {
		this.pupilModel.changeClass(view.comboBoxClass.currentIndex());
		view.tableUsers.reset();
	}

	@SuppressWarnings("unused")
	private void changeDetailsUser() {
		this.noteModel.changePupil(view.tableUsers.currentIndex().row());
		// view.tableDetailUsers.reset();
	}

	@SuppressWarnings("unused")
	private void addRate() {
		final AddRateForm aRF = new AddRateForm();
		final QModelIndex currentIndex = getCurrentIndex();
		if (!Helpers.indexIsValid(getCurrentIndex())) {
			QMessageBox.warning(null, "Dodaj ocenê", "Nie wybra³eœ ucznia.");
			return;
		}

		if (aRF.exec() == QDialog.DialogCode.Accepted.value()) {
			System.err.println("index: " + currentIndex.row());

			if (Helpers.indexIsValid(currentIndex)) {
				final Nauczyciel nauczyciel = getLoggedTeacher();
				final Ocena ocena = new Ocena(pupilModel.getPupils().get(
						currentIndex.row()), pupilModel.getPrzedmioty().get(
						currentIndex.row()), nauczyciel, aRF.getOcena(),
						aRF.getWaga(), new Date());
				ocenaService.save(ocena);
				pupilModel.addRate(ocena, currentIndex.row());
			}
			pupilModel.refreshModel();
		}
	}

	private QModelIndex getCurrentIndex() {
		return view.tableUsers.currentIndex();
	}

	@SuppressWarnings("unused")
	private void addNote() {
		final AddNoteForm aNF = new AddNoteForm();
		final QModelIndex currentIndex = getCurrentIndex();
		if (!Helpers.indexIsValid(getCurrentIndex())) {
			QMessageBox
			.warning(null, "Dodaj notkê", "Nie wybra³eœ ucznia.");
			return;
		}
		if (aNF.exec() == QDialog.DialogCode.Accepted.value()) {

			if (Helpers.indexIsValid(currentIndex)) {
				final Nauczyciel nauczyciel = getLoggedTeacher();

				if (aNF.getNote() != null && !aNF.getNote().equals("")) {
					Uwaga uwaga = new Uwaga(this.pupilModel.getPupils().get(
							currentIndex.row()), nauczyciel, aNF.getNote());
					uwagaService.save(uwaga);
					noteModel.addNote(currentIndex.row(), uwaga);
				} else {
					QMessageBox.warning(null, "Dodaj notkê",
							"Nie wpisa³eœ treœci.");
				}
			}
			this.noteModel.refreshModel();
		}
	}

	/**
	 * Access here has only Teacher so we can assume logged person is Teacher
	 */
	private Nauczyciel getLoggedTeacher() {
		final int logedPersonId = konfiguracjaService.getLoggedOsoba()
				.getIdOsoba();
		return nauczycielService.findByOsobaId(logedPersonId);
	}

	@Override
	public void makeUpdateOfView() {
		this.noteModel.refreshModel();
	}
}
