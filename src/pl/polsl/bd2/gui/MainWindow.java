package pl.polsl.bd2.gui;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.messageSystem.service.RoleService;
import pl.polsl.bd2.messageSystem.service.UczenService;
import pl.polsl.bd2.models.PupilModelForClassMenagment;
import pl.polsl.bd2.presentation.absence.AbsencePresenter;
import pl.polsl.bd2.presentation.contest.ContestPresenter;
import pl.polsl.bd2.presentation.data.DataPresenter;
import pl.polsl.bd2.presentation.message.MessagePresenter;
import pl.polsl.bd2.presentation.pupils.PupilPresenter;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSignalMapper;
import com.trolltech.qt.core.QUrl;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QSortFilterProxyModel;

public class MainWindow extends QMainWindow {

	//TODO MJ Zakładka z pomocą (QTextEdit chyba ma full HTML ew. przeglądakrkowe okno)
	//TODO MJ JUstification i absensce


	private MessagePresenter messagePresenter;
	private ContestPresenter contestPresenter;
	private AbsencePresenter absencePresenter;
	private DataPresenter dataPresenter;
	private PupilPresenter pupilPresenter;
	
	private KonfiguracjaService konfiguracjaService;
	private OsobaService osobaService;
	private PupilModelForClassMenagment pupilModelForClassMenagment;
	
	Ui_MainWindow ui = new Ui_MainWindow();

	public MainWindow() {
		ui.setupUi(this);

		konfiguracjaService = (KonfiguracjaService) SpringUtil
				.getBean("konfiguracjaService");
		osobaService = (OsobaService) SpringUtil.getBean("osobaService");
		konfiguracjaService.setLoggedOsoba(osobaService.findAll().get(4));
		
		ui.webView.setUrl(new QUrl("classpath:/pl/polsl/bd2/help.html"));
		
		initTabs();
	}

	private void initTabs() {
		dataTab();
		classMenagmentTab();
		connectSignalsAndSlots();
		absenceTab();
		messagesTab();

		pupilsTab();
		contestTab();
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
		this.pupilModelForClassMenagment = new PupilModelForClassMenagment();
		QSortFilterProxyModel pupilModelForClassMenagmentSortable = new QSortFilterProxyModel();
		pupilModelForClassMenagmentSortable
				.setSourceModel(this.pupilModelForClassMenagment);

		ui.tableViewPupils.setModel(pupilModelForClassMenagmentSortable);
		ui.tableViewPupils.setSortingEnabled(true);
		ui.tableViewPupils.resizeColumnsToContents();
		ui.tableViewPupils.horizontalHeader().setStretchLastSection(true);
		ui.tableViewPupils.verticalHeader().hide();

		this.reloadComboBoxClassAll();

		ui.buttonBoxAddClass.accepted.connect(this, "addClass()");
		ui.buttonBoxAddClass.rejected.connect(this, "clearLineNewClass()");

		ui.buttonBoxAddPupil.accepted.connect(this, "addPupil()");
		ui.buttonBoxAddPupil.rejected.connect(this, "clearFieldsPupil()");

		ui.comboBoxClassAll.currentIndexChanged.connect(this,
				"changeClassAllPupilTable()");
		ui.pushButtonDeletePupil.clicked.connect(this, "deletePupilClass()");

		QSignalMapper hideGroupBoxMapper = new QSignalMapper();
		hideGroupBoxMapper.setMapping(ui.pushButtonAddClass, 1);
		hideGroupBoxMapper.setMapping(ui.pushButtonAddPupil, 2);
		ui.pushButtonAddClass.clicked.connect(
				hideGroupBoxMapper, "map()");
		ui.pushButtonAddPupil.clicked.connect(hideGroupBoxMapper, "map()");
		hideGroupBoxMapper.mappedInteger.connect(this,
				"hideGroupBox(int)");

	}

	private void dataTab() {
		dataPresenter = new DataPresenter(ui);
		dataPresenter.initModel();
		dataPresenter.connectSlots();

		// Init views
		ui.tableDetailsData.setVisible(false);
		dataPresenter.assignStudentsToSpinner();

		ui.tableData.selectRow(0);

		ui.tableData.resizeColumnsToContents();
		ui.tableData.horizontalHeader().setStretchLastSection(true);
		ui.tableData.verticalHeader().hide();

		ui.labelProgramInData.setText(dataPresenter.getTableDataModel()
		.getDataContainer().get(0).getSubject());

		ui.tableDetailsData.resizeColumnsToContents();
		ui.tableDetailsData.horizontalHeader().setStretchLastSection(true);
		ui.tableDetailsData.verticalHeader().hide();
	}

	private void connectSignalsAndSlots() {
		ui.comboBoxClass.currentIndexChanged
				.connect(this, "changePupilTable()");
	}

	@SuppressWarnings("unused")
	private void deletePupilClass() {
		QModelIndex currentIndex = ui.tableViewPupils.currentIndex();
		if (Helpers.indexIsValid(currentIndex)) {
			ui.tableViewPupils.model().removeRows(currentIndex.row(), 1);
		}
	}
	
	
	@SuppressWarnings("unused")
	private void changeClassAllPupilTable() {
		this.pupilModelForClassMenagment.reClass(ui.comboBoxClassAll
				.currentIndex());
		ui.tableViewPupils.reset();
	}
	
	@SuppressWarnings("unused")
	private void toogleTableDetailsData(int i) {
		if (i == 1) {
			ui.tableDetailsData.setVisible(!ui.tableDetailsData.isVisible());
		} else if (!ui.tableDetailsData.isVisible()) {
			ui.tableDetailsData.setVisible(true);
		}
	}
	@SuppressWarnings("unused")
	private void hideGroupBox(int i){
		if(i == 1){
			ui.groupBoxAddClass.setVisible(!ui.groupBoxAddClass.isVisible());
		}
		else
		{
			ui.groupBoxAddPupil.setVisible(!ui.groupBoxAddPupil.isVisible());
		}
	}
	
	@SuppressWarnings("unused")
	private void addClass() {
		if (!ui.lineEditNewClassName.text().isEmpty()) {
			OddzialService oddzialService = (OddzialService) SpringUtil
					.getBean("oddzialService");
			oddzialService.save(new Oddzial(ui.lineEditNewClassName.text()));
			// this.reloadComboBoxClassAll();
			ui.comboBoxClassAll.addItem(ui.lineEditNewClassName.text());
			ui.comboBoxClass.addItem(ui.lineEditNewClassName.text());
			ui.lineEditNewClassName.clear();
			this.pupilModelForClassMenagment.reContainer();
			ui.tableViewPupils.reset();
		}
	}

	private void reloadComboBoxClassAll() {
		ui.comboBoxClassAll.clear();
		OddzialService oddzialService = (OddzialService) SpringUtil
				.getBean("oddzialService");
		for (Oddzial oddzial : oddzialService.findAll()) {
			ui.comboBoxClassAll.addItem(oddzial.getNazwa());
			ui.comboBoxClass.addItem(oddzial.getNazwa());
		}

	}

	@SuppressWarnings("unused")
	private void clearLineNewClass() {
		// TODO: Cos w stylu chowania mozna zrobic
		ui.lineEditNewClassName.clear();
	}

	@SuppressWarnings("unused")
	private void addPupil() {
		if (!ui.lineEditNewPupilName.text().isEmpty()) {
			RoleService roleService = (RoleService) SpringUtil
					.getBean("roleService");
			OddzialService oddzialService = (OddzialService) SpringUtil
					.getBean("oddzialService");
			UczenService uczenService = (UczenService) SpringUtil
					.getBean("uczenService");
			OsobaService osobaService = (OsobaService) SpringUtil
					.getBean("osobaService");
			System.err.println(osobaService.findAll().size());
			osobaService.save(new Osoba(ui.lineEditNewPupilName.text(),
					ui.lineEditNewPupilVorname.text(),
					ui.lineEditNewPupilStreet.text(), ui.lineEditNewPupilCity
							.text(), ui.lineEditNewPupilMail.text(),
					ui.lineEditNewPupilLogin.text(),
					ui.lineEditNewPupilPassword.text(), roleService.findByName(
							"Uczen").get(0)));
			System.err.println(oddzialService.findAll()
					.get(ui.comboBoxClassAll.currentIndex()).getNazwa());


			uczenService.save(new Uczen(osobaService.findLast(), oddzialService
					.findAll().get(ui.comboBoxClassAll.currentIndex())));
			this.clearFieldsPupil();
			this.pupilModelForClassMenagment.reContainer();
			ui.tableViewPupils.reset();
		}
	}

	private void clearFieldsPupil() {
		ui.lineEditNewPupilName.clear();
		ui.lineEditNewPupilVorname.clear();
		ui.lineEditNewPupilStreet.clear();
		ui.lineEditNewPupilCity.clear();
		ui.lineEditNewPupilMail.clear();
		ui.lineEditNewPupilLogin.clear();
		ui.lineEditNewPupilPassword.clear();
	}

	@SuppressWarnings("unused")
	private void changePupilTable() {
		ui.tableUsers.reset();
	}

}
