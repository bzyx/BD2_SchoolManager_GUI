package pl.polsl.bd2.presentation.pupils;

import java.util.Date;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QDialog;

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
		
		this.konfiguracjaService = (KonfiguracjaService) SpringUtil.getContext().getBean(
				"konfiguracjaService");
		this.nauczycielService = (NauczycielService) SpringUtil.getContext().getBean(
				"nauczycielService");
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
	private void changeDetailsUser(){
		this.noteModel.changePupil(view.tableUsers.currentIndex().row());
		//view.tableDetailUsers.reset();
	}
	
	@SuppressWarnings("unused")
	private void addRate() {
		final Osoba osoba = this.konfiguracjaService.getLoggedOsoba();
		final AddRateForm aRF = new AddRateForm();
		Nauczyciel nauczyciel = new Nauczyciel();
		if (aRF.exec() == QDialog.DialogCode.Accepted.value()){
				final QModelIndex currentIndex = view.tableUsers.currentIndex();
				
				if (Helpers.indexIsValid(currentIndex)) {
//					for(Nauczyciel nauczycielIt: nauczycielService.findAll()){
//						if (nauczycielIt.getOsoba().getIdOsoba() == osoba.getIdOsoba()) {
//							nauczyciel = nauczycielIt;
//							break;
//						}
//					}
					NauczycielService nauczycielService = (NauczycielService) SpringUtil
							.getBean("nauczycielService");
					nauczyciel = nauczycielService.findById(konfiguracjaService
							.getLoggedOsoba().getIdOsoba());
					
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
		}
	}
	
	@SuppressWarnings("unused")
	private void addNote() {
		final Osoba osoba = this.konfiguracjaService.getLoggedOsoba();
		final AddNoteForm aNF = new AddNoteForm();
		Nauczyciel nauczyciel = new Nauczyciel();
		if (aNF.exec() == QDialog.DialogCode.Accepted.value()){
				final QModelIndex currentIndex = view.tableUsers.currentIndex();
				
				if (Helpers.indexIsValid(currentIndex)) {
					for(Nauczyciel nauczycielIt: nauczycielService.findAll()){
						if (nauczycielIt.getOsoba().getIdOsoba() == osoba.getIdOsoba()) {
							nauczyciel = nauczycielIt;
							break;
						}
					}
					
					//this.pupilModel.addRate(ocena, currentIndex.row());
					Uwaga uwaga = new Uwaga(this.pupilModel.getPupils().get(currentIndex.row()), nauczyciel, aNF.getNote());
					this.noteModel.addNote(currentIndex.row(), uwaga);
					this.uwagaService.save(uwaga);
				}
				this.noteModel.refreshModel();
		}
	}

	@Override
	public void makeUpdateOfView() {
		this.noteModel.refreshModel();
	}
}
