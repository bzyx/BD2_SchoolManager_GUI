package pl.polsl.bd2.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.polsl.bd2.ApplicationMain;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.presentation.absence.AbsencePresenter;
import pl.polsl.bd2.presentation.contest.ContestPresenter;
import pl.polsl.bd2.presentation.data.DataPresenter;
import pl.polsl.bd2.presentation.managment.ManagmentPresenter;
import pl.polsl.bd2.presentation.message.MessagePresenter;
import pl.polsl.bd2.presentation.pupils.PupilPresenter;
import pl.polsl.bd2.presentation.teacher.TeacherPresenter;

import com.trolltech.qt.gui.QMainWindow;

public class MainWindow extends QMainWindow {
	private static Map<Integer, String> currentTabs = new HashMap<Integer, String>();

	private MessagePresenter messagePresenter;
	private ContestPresenter contestPresenter;
	private AbsencePresenter absencePresenter;
	private DataPresenter dataPresenter;
	private PupilPresenter pupilPresenter;
	private ManagmentPresenter managmentPresenter;
	private TeacherPresenter teacherPresenter;
	private Ui_MainWindow ui = new Ui_MainWindow();

	public MainWindow() {
		ui.setupUi(this);
		initTabs();
	}

	
	private void initTabs() {
		dataTab();
		classMenagmentTab();
		//absenceTab();
		messagesTab();
		pupilsTab();
		contestTab();
		teacherTab();
		
		final Osoba loggedPerson = ApplicationMain.getLoggedPerson();
		System.err.println("Osoba zalogowana: " + loggedPerson);
		if (loggedPerson!= null) {
			ArrayList<Integer> tabIdsToRemove = new ArrayList<Integer>();
			// TODO: Set propper values here
			switch (loggedPerson.getRole().getIdRole()) {
			case 1:
				tabIdsToRemove.clear();
				tabIdsToRemove.add(2);
				tabIdsToRemove.add(3);
				tabIdsToRemove.add(4);
				tabIdsToRemove.add(5);
				tabIdsToRemove.add(6);
				removeTabsWithIDs(tabIdsToRemove);
				break;
			case 2:
				tabIdsToRemove.clear();
				tabIdsToRemove.add(4);
				tabIdsToRemove.add(5);
				tabIdsToRemove.add(6);
				removeTabsWithIDs(tabIdsToRemove);
			case 3:
				tabIdsToRemove.clear();
				tabIdsToRemove.add(2);
				tabIdsToRemove.add(3);
				tabIdsToRemove.add(4);
				tabIdsToRemove.add(5);
				tabIdsToRemove.add(6);
				tabIdsToRemove.add(7);
				removeTabsWithIDs(tabIdsToRemove);
				break;
			case 4:
				tabIdsToRemove.clear();
				tabIdsToRemove.add(4);
				tabIdsToRemove.add(5);
				tabIdsToRemove.add(6);
				removeTabsWithIDs(tabIdsToRemove);
				
			default:
				break;
			}
		}
	}


	private void removeTabsWithIDs(ArrayList<Integer> tabIdsToRemove) {
		for (Integer id : tabIdsToRemove) {
			ui.tabWidget.removeTab(id.intValue());
		}
	}

	private void teacherTab() {
		teacherPresenter = new TeacherPresenter(ui);
		teacherPresenter.initModel();
		teacherPresenter.connectSlots();

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

	private void contestTab() {
		contestPresenter = new ContestPresenter(ui);
		contestPresenter.connectSlots();
	}

	private void pupilsTab() {
		this.pupilPresenter = new PupilPresenter(ui);
		this.pupilPresenter.initModel();
		this.pupilPresenter.connectSlots();
	}

	private void classMenagmentTab() {
		managmentPresenter = new ManagmentPresenter(ui);
		this.managmentPresenter.connectSlots();
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
	}
}
