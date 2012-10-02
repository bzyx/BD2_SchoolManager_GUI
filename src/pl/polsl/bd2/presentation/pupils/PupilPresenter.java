package pl.polsl.bd2.presentation.pupils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.polsl.bd2.ApplicationMain;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Absencja;
import pl.polsl.bd2.messageSystem.models.Nauczyciel;
import pl.polsl.bd2.messageSystem.models.Ocena;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Uwaga;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.NauczycielService;
import pl.polsl.bd2.messageSystem.service.OcenaService;
import pl.polsl.bd2.messageSystem.service.UwagaService;
import pl.polsl.bd2.models.AbsenceModel;
import pl.polsl.bd2.models.NoteModel;
import pl.polsl.bd2.models.PupilModel;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;

public class PupilPresenter implements BasePresenter {

	private Ui_MainWindow view;
	private PupilModel pupilModel;
	private NoteModel noteModel;
	private AbsenceModel absenceModel;
	private KonfiguracjaService konfiguracjaService;
	private NauczycielService nauczycielService;
	private OcenaService ocenaService;
	private UwagaService uwagaService;

	public PupilPresenter(Ui_MainWindow view) {
		this.view = view;

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
		this.pupilModel.initNote(this.noteModel);
		this.absenceModel = new AbsenceModel();
		view.tableAbsence_2.setModel(this.absenceModel);
		view.tableAbsence_2.resizeColumnsToContents();
		view.tableAbsence_2.horizontalHeader().setStretchLastSection(true);
		view.tableAbsence_2.verticalHeader().hide();
		this.initAbsence(this.absenceModel);
		
	}

	@SuppressWarnings("unused")
	private void changeClass() {
		this.pupilModel.changeClass(view.comboBoxClass.currentIndex());
		view.tableUsers.reset();
	}

	@SuppressWarnings("unused")
	private void changeDetailsUser() {
		this.noteModel.changePupil(view.tableUsers.currentIndex().row());
		this.initAbsence(this.absenceModel);
		//view.tableDetailUsers.reset();
	}

	@SuppressWarnings("unused")
	private void addRate() {
		final AddRateForm aRF = new AddRateForm();
		Nauczyciel nauczyciel = new Nauczyciel();
		if (aRF.exec() == QDialog.DialogCode.Accepted.value()){
				final QModelIndex currentIndex = view.tableUsers.currentIndex();
				
				if (Helpers.indexIsValid(currentIndex)) {
					NauczycielService nauczycielService = (NauczycielService) SpringUtil
							.getBean("nauczycielService");
					nauczyciel = nauczycielService.findById(ApplicationMain.getLoggedPerson().getIdOsoba());
					
					Ocena ocena = new Ocena(this.pupilModel.getPupils().get(currentIndex.row()),
							this.pupilModel.getPrzedmioty().get(currentIndex.row()),
							nauczyciel, aRF.getOcena(), aRF.getWaga(), new Date() );
					this.pupilModel.addRate(ocena, currentIndex.row());
					System.out.println(Integer.toString(this.pupilModel.getPupils().get(currentIndex.row()).getIdUczen())
							+ Integer.toString(this.pupilModel.getPrzedmioty().get(currentIndex.row()).getIdPrzedmiot())
							+ Integer.toString(nauczyciel.getIdNauczyciel()));
					this.ocenaService.save(ocena);
				}
				this.pupilModel.refreshModel();
				view.tableUsers.selectRow(currentIndex.row());
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
			QMessageBox.warning(null, "Dodaj notk�", "Nie wybra�e� ucznia.");
			return;
		}

		if (aNF.exec() == QDialog.DialogCode.Accepted.value()) {
			if (Helpers.indexIsValid(currentIndex)) {
				if (aNF.getNote() != null && !aNF.getNote().equals("")) {
					final Nauczyciel nauczyciel = getLoggedTeacher();
					final Uwaga uwaga = new Uwaga(this.pupilModel.getPupils().get(
							currentIndex.row()), nauczyciel, aNF.getNote());
					uwagaService.save(uwaga);
					noteModel.addNote(currentIndex.row(), uwaga);
				} else {
					QMessageBox.warning(null, "Dodaj notk�",
							"Nie wpisa�e� tre�ci.");
				}
			}
			this.noteModel.refreshModel();
		}
	}
	
	public void initAbsence(AbsenceModel absence){
		try{
		this.absenceModel.initData(new ArrayList<Absencja> (this.pupilModel.getUczenFromOddzial().get(
				view.comboBoxClass.currentIndex()).get(
						view.tableUsers.currentIndex().row()).getUczen2Absencja()));
		}catch(NullPointerException e){}
	}

	/**
	 * Access here has only Teacher so we can assume logged person is Teacher
	*/ 
	private Nauczyciel getLoggedTeacher() {
		Osoba loggedPerson = ApplicationMain.getLoggedPerson();
		System.err.println("loggedPerson: " + loggedPerson);
		Nauczyciel teacher = nauczycielService.findByOsobaId(loggedPerson
				.getIdOsoba());
		return teacher;
	}

	@Override
	public void makeUpdateOfView() {
		this.noteModel.refreshModel();
	}
}
