package pl.polsl.bd2.gui;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.presentation.absence.AbsencePresenter;
import pl.polsl.bd2.presentation.contest.ContestPresenter;
import pl.polsl.bd2.presentation.data.DataPresenter;
import pl.polsl.bd2.presentation.managment.ManagmentPresenter;
import pl.polsl.bd2.presentation.message.MessagePresenter;
import pl.polsl.bd2.presentation.pupils.PupilPresenter;

import com.trolltech.qt.core.QUrl;
import com.trolltech.qt.gui.QMainWindow;

public class MainWindow extends QMainWindow {
	
	private MessagePresenter messagePresenter;
	private ContestPresenter contestPresenter;
	private AbsencePresenter absencePresenter;
	private DataPresenter dataPresenter;
	private PupilPresenter pupilPresenter;
	private ManagmentPresenter managmentPresenter;
	
	private KonfiguracjaService konfiguracjaService;
	private OsobaService osobaService;
	
	Ui_MainWindow ui;

	public MainWindow() {
		ui = new Ui_MainWindow();
		ui.setupUi(this);

		//Zalogowany użytkownik
		konfiguracjaService = (KonfiguracjaService) SpringUtil
				.getBean("konfiguracjaService");
		osobaService = (OsobaService) SpringUtil.getBean("osobaService");
		konfiguracjaService.setLoggedOsoba(osobaService.findAll().get(4));
		
		// Ustaw Pomoc
		ui.webView.setUrl(new QUrl("classpath:/pl/polsl/bd2/help.html"));
		initTabs();
	
		ui.tabWidget.currentChanged.connect(this, "makeTabUpdate(int)");
	}
	
	public void makeTabUpdate(int tabNo){
		switch (tabNo) {
		case 0:	//Podgląd ucznia
			dataPresenter.makeUpdateOfView();
			break;
		case 1: //Wiadomości
			messagePresenter.makeUpdateOfView();
			break;
		case 2: //Konkursy
			contestPresenter.makeUpdateOfView();
			break;
		case 3: //Uwagi i absencja
			absencePresenter.makeUpdateOfView();
			break;
		case 4: //Uczniowie
			pupilPresenter.makeUpdateOfView();
			break;
		case 5: //Zarządzanie klasą
			managmentPresenter.makeUpdateOfView();
			break;
		case 6: //Pomoc
			
			break;
		default:
			break;
		}
	}

	private void initTabs() {
		dataTab();
		classMenagmentTab();
		absenceTab();
		messagesTab();
		pupilsTab();
		contestTab();
	}
	
	private void dataTab() {
		dataPresenter = new DataPresenter(ui);
		dataPresenter.connectSlots();

		// Init views
		dataPresenter.assignStudentsToSpinner();

		ui.tableGrades.resizeColumnsToContents();
		ui.tableGrades.horizontalHeader().setStretchLastSection(true);
		ui.tableGrades.verticalHeader().hide();

		ui.tableAbsence.resizeColumnsToContents();
		ui.tableAbsence.horizontalHeader().setStretchLastSection(true);
		ui.tableAbsence.verticalHeader().hide();
		
		ui.tableJustification.resizeColumnsToContents();
		ui.tableJustification.horizontalHeader().setStretchLastSection(true);
		ui.tableJustification.verticalHeader().hide();
	}
	
	private void classMenagmentTab() {
		managmentPresenter = new ManagmentPresenter(ui);
		managmentPresenter.connectSlots();

	}

	private void absenceTab() { 
        absencePresenter = new AbsencePresenter(this, ui); 
        absencePresenter.initModel(); 
        absencePresenter.connectSlots(); 
        // Init Absence view 
        ui.tableAbsences.resizeColumnsToContents(); 
        ui.tableAbsences.horizontalHeader().setStretchLastSection(true); 
        ui.tableAbsences.verticalHeader().hide(); 
    }
	
	private void messagesTab() {
		messagePresenter = new MessagePresenter(ui);
		messagePresenter.initModel();
		messagePresenter.connectSlots();

		// Init Message view
		ui.tableMessages.setSortingEnabled(true);
		ui.tableMessages.resizeColumnsToContents();
		ui.tableMessages.horizontalHeader().setStretchLastSection(true);
		ui.tableMessages.verticalHeader().hide();
		ui.buttonReplayMessage.setEnabled(false);
		ui.buttonDeleteMessage.setEnabled(false);
		ui.buttonMarkAsRead.setEnabled(false);
	}

	private void pupilsTab() {
		this.pupilPresenter = new PupilPresenter(ui);
		this.pupilPresenter.initModel();
		this.pupilPresenter.connectSlots();
	}
	
	private void contestTab() {
		contestPresenter = new ContestPresenter(ui);
		contestPresenter.connectSlots();
	}

}
