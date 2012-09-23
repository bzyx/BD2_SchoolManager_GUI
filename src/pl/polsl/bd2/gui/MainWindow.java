package pl.polsl.bd2.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSignalMapper;
import com.trolltech.qt.gui.*;


import pl.polsl.bd2.messageSystem.models.Komunikat;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.TrescKomunikatu;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.KomunikatService;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.messageSystem.service.RoleService;
import pl.polsl.bd2.messageSystem.service.TrescKomunikatuService;
import pl.polsl.bd2.messageSystem.service.UczenService;
import pl.polsl.bd2.models.DetailsDataModel;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.models.AbsenceModel;
import pl.polsl.bd2.models.JustificationModel;
import pl.polsl.bd2.models.MessageModel;
import pl.polsl.bd2.models.DataModel;
import pl.polsl.bd2.models.PupilModel;
import pl.polsl.bd2.models.PupilModelForClassMenagment;
import pl.polsl.bd2.models.UserData;
//import pl.polsl.bd2.models.PupilModel.Pupil;

public class MainWindow extends QMainWindow {

	Ui_MainWindow ui = new Ui_MainWindow();
	private DetailsDataModel tableDetailsDataModel = new DetailsDataModel();
	private DataModel tableDataModel = new DataModel();
	private UserData userData = new UserData();
	private AbsenceModel absenceModel = new AbsenceModel();
	private JustificationModel justificationModel = new JustificationModel();
	private PupilModel.Pupil pupilMock = new PupilModel.Pupil();
	private PupilModel pupilModel = new PupilModel(this.pupilMock.getClassPupil().get(0).getPuil());
	private KonfiguracjaService konfiguracjaService;
	private OsobaService osobaService;
	private KomunikatService komunikatService;
	private TrescKomunikatuService trescKomunikatuService;
	private UczenService uczenService;
	private ContestTypeDialog contestTypeDialog;
	private PupilModelForClassMenagment pupilModelForClassMenagment;

	public MainWindow() {
		ui.setupUi(this);
		
		konfiguracjaService = (KonfiguracjaService) SpringUtil.getBean("konfiguracjaService");
		komunikatService = (KomunikatService) SpringUtil.getBean("komunikatService");
		trescKomunikatuService = (TrescKomunikatuService) SpringUtil.getBean("trescKomunikatuService");
		osobaService = (OsobaService) SpringUtil.getBean("osobaService");
		konfiguracjaService.setLoggedOsoba(osobaService.findAll().get(4));
		uczenService = (UczenService)SpringUtil.getBean("uczenService");
		
		Osoba osoba = konfiguracjaService.getLoggedOsoba();
		System.out.println(osoba.getImie() + " " + osoba.getNazwisko());
		//konfiguracjaService.setLoggedOsoba(null);
		
		/*
		 * DataTab tab functions starts here
		 */
		this.dataTab();
		this.classMenagmentTab();
		
		
		connectSignalsAndSlots();
		/*
		 * Absence tab functions starts here
		 */
		ui.tableAbsences.setModel(absenceModel);
		ui.tableAbsences.resizeColumnsToContents();
		ui.tableAbsences.horizontalHeader().setStretchLastSection(true);
		ui.tableAbsences.verticalHeader().hide();
		ui.listJustifications.setModel(justificationModel);
		/*
		 * Messages tab functions starts here
		 */

		MessageModel messageModel = new MessageModel();
		QSortFilterProxyModel messageModelSortable = new QSortFilterProxyModel();
		messageModelSortable.setSourceModel(messageModel);

		ui.tableMessages.setModel(messageModelSortable);
		ui.tableMessages.setSortingEnabled(true);
		ui.tableMessages.resizeColumnsToContents();
		ui.tableMessages.horizontalHeader().setStretchLastSection(true);
		ui.tableMessages.verticalHeader().hide();
		ui.buttonReplayMessage.setEnabled(false);
		ui.buttonDeleteMessage.setEnabled(false);
		ui.buttonMarkAsRead.setEnabled(false);

		ui.tableMessages.selectionModel().selectionChanged.connect(this,
				"messageChanged(QItemSelection, QItemSelection)");
		ui.buttonMarkAsRead.clicked.connect(this, "messageSetAsRead()");
		ui.buttonDeleteMessage.clicked.connect(this, "messageDeleteMessage()");
		ui.buttonNewMessage.clicked.connect(this, "messageNewMessage()");
		ui.buttonReplayMessage.clicked.connect(this, "messageReplay()");

		/*
		 * Messages tab functions ends here
		 */
		this.teacherGui();
		this.contestGui();

		
	}
	
	private void teacherGui(){
		ui.tableUsers.setModel(this.pupilModel);
		ui.tableUsers.resizeColumnsToContents();
		ui.tableUsers.horizontalHeader().setStretchLastSection(true);
		ui.tableUsers.verticalHeader().hide();
		ui.tableDetailUsers.setModel(this.tableDetailsDataModel);
		ui.tableDetailUsers.resizeColumnsToContents();
		ui.tableDetailUsers.horizontalHeader().setStretchLastSection(true);
		ui.tableDetailUsers.verticalHeader().hide();
		for (PupilModel.Pupil.ClassPupilMock classPupil : this.pupilMock.getClassPupil()){
			ui.comboBoxClass.addItem(Integer.toString(classPupil.getClassPupil()));
		}
	}
	
	private void contestGui(){
		ui.buttonEditContestType.clicked.connect(this, "editContestTypeDialog()");
		contestTypeDialog = new ContestTypeDialog();
	}
	
	private void classMenagmentTab(){
		this.pupilModelForClassMenagment = new PupilModelForClassMenagment();
		QSortFilterProxyModel pupilModelForClassMenagmentSortable = new QSortFilterProxyModel();
		pupilModelForClassMenagmentSortable.setSourceModel(this.pupilModelForClassMenagment);

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
		
		ui.comboBoxClassAll.currentIndexChanged.connect(this, "changeClassPupilTable()");
		ui.pushButtonDeletePupil.clicked.connect(this, "deletePupilClass()");

		// ui.comboBoxClassAll.currentIndexChanged.connect(this, "ui.tableViewPupils.reset()");
	}
	
	private void dataTab(){
		ui.tableDetailsData.setVisible(false);
		for (Uczen uczen : this.uczenService.findAll()) {
			ui.comboBoxStudent.addItem(uczen.getOsoba().getImie());
		}
		ui.tableData.selectRow(0);
		this.tableDataModel.setDataContainer(this.uczenService.findById(1), this.userData
				.getUserDataConteiner().get(0)
				.getData());
		ui.tableData.setModel(this.tableDataModel);
		ui.tableData.resizeColumnsToContents();
		ui.tableData.horizontalHeader().setStretchLastSection(true);
		ui.tableData.verticalHeader().hide();
		ui.labelProgramInData.setText(this.tableDataModel.getDataContainer()
				.get(0).getSubject());
		this.tableDetailsDataModel.setDetailsDataContainer(this.tableDataModel
				.getDataContainer().get(0).getDetailsData());
		ui.tableDetailsData.setModel(this.tableDetailsDataModel);
		ui.tableDetailsData.resizeColumnsToContents();
		ui.tableDetailsData.horizontalHeader().setStretchLastSection(true);
		ui.tableDetailsData.verticalHeader().hide();
	}
	// public MainWindow(QWidget parent) {
	// super(parent);
	// ui.setupUi(this);
	// connectSignalsAndSlots();
	// }

	private void connectSignalsAndSlots() {
		QSignalMapper mapperToogleTableDetailsData = new QSignalMapper();
		mapperToogleTableDetailsData.setMapping(ui.buttonToogleDetailsData, 1);
		mapperToogleTableDetailsData.setMapping(ui.tableData, 2);
		ui.buttonToogleDetailsData.clicked.connect(
				mapperToogleTableDetailsData, "map()");
		ui.tableData.clicked.connect(mapperToogleTableDetailsData, "map()");
		mapperToogleTableDetailsData.mappedInteger.connect(this,
				"toogleTableDetailsData(int)");
		ui.tableData.selectionModel().currentRowChanged.connect(this,
				"changeDataDetails()");
		ui.comboBoxStudent.currentIndexChanged.connect(this,
				"changeUserWithData()");
		ui.tableAbsences.doubleClicked.connect(this, "addJustification()");
		ui.comboBoxClass.currentIndexChanged.connect(this, "changePupilTable()");

	}
	
	@SuppressWarnings("unused")
	private void deletePupilClass(){
		QModelIndex currentIndex = ui.tableViewPupils.currentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			ui.tableViewPupils.model().removeRows(currentIndex.row(), 1);
		}
	}
	
	@SuppressWarnings("unused")
	private void changeClassPupilTable(){
		this.pupilModelForClassMenagment.reClass(ui.comboBoxClassAll.currentIndex());
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
	private void addClass() {
		if(!ui.lineEditNewClassName.text().isEmpty()){
			OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
			oddzialService.save(new Oddzial(ui.lineEditNewClassName.text()));
			//this.reloadComboBoxClassAll();
			ui.comboBoxClassAll.addItem(ui.lineEditNewClassName.text());
			ui.lineEditNewClassName.clear();
			this.pupilModelForClassMenagment.reContainer();
			ui.tableViewPupils.reset();
		}
	}
	
	private void reloadComboBoxClassAll(){	
		ui.comboBoxClassAll.clear();
		OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
		for( Oddzial oddzial: oddzialService.findAll()){
			ui.comboBoxClassAll.addItem(oddzial.getNazwa());
		}
		
	}
	
	@SuppressWarnings("unused")
	private void clearLineNewClass() {
		//TODO: Cos w stylu chowania mozna zrobic
		ui.lineEditNewClassName.clear();
	}
	
	@SuppressWarnings("unused")
	private void addPupil() {
		if(!ui.lineEditNewPupilName.text().isEmpty()){
			RoleService roleService = (RoleService) SpringUtil.getBean("roleService");
			OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
			UczenService uczenService = (UczenService)SpringUtil.getBean("uczenService");
			OsobaService osobaService = (OsobaService) SpringUtil.getBean("osobaService");
			System.err.println(osobaService.findAll().size());
			osobaService.save(new Osoba(ui.lineEditNewPupilName.text(),
					ui.lineEditNewPupilVorname.text(),
					ui.lineEditNewPupilStreet.text(),
					ui.lineEditNewPupilCity.text(),
					ui.lineEditNewPupilMail.text(),
					ui.lineEditNewPupilLogin.text(),
					ui.lineEditNewPupilPassword.text(),
					roleService.findByName("Uczen").get(0)));
			System.err.println(oddzialService.findAll().get(ui.comboBoxClassAll.currentIndex()).getNazwa());

			//List<Osoba> users = new ArrayList<Osoba>(roleService.findByName("Uczen").get(0).getRole2osoba());
			uczenService.save(new Uczen(osobaService.findLast(),
					oddzialService.findAll().get(ui.comboBoxClassAll.currentIndex())));
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
	private void changeDataDetails() {
		this.tableDetailsDataModel.setDetailsDataContainer(this.tableDataModel
				.getDataContainer().get(ui.tableData.currentIndex().row())
				.getDetailsData());
		ui.labelProgramInData.setText(ui.tableData.model()
				.index(ui.tableData.currentIndex().row(), 0).data().toString());
		ui.tableDetailsData.reset();

	}
	@SuppressWarnings("unused")
	private void changePupilTable(){
		this.pupilModel.setDataContainer(this.pupilMock.getClassPupil().get(
				ui.comboBoxClass.currentIndex()).getPuil());
		ui.tableUsers.reset();
	}

	@SuppressWarnings("unused")
	private void changeUserWithData() {
		//w bazie liczymy od 1 a tutaj od 0
		this.tableDataModel.setDataContainer(this.uczenService.findById(ui.comboBoxStudent.currentIndex()+1), this.userData
				.getUserDataConteiner().get(0).getData());
		ui.tableData.reset();
		ui.labelProgramInData.setText(this.tableDataModel.getDataContainer()
				.get(0).getSubject());
		ui.tableDetailsData.setVisible(false);
		ui.tableData.selectRow(0);
	}

	/*
	 * Messages tab functions starts here
	 */
	@SuppressWarnings("unused")
	private void messageChanged(QItemSelection is, QItemSelection was) {

		QModelIndex currentIndex = ui.tableMessages.currentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			ui.buttonReplayMessage.setEnabled(true);
			ui.buttonDeleteMessage.setEnabled(true);
			ui.buttonMarkAsRead.setEnabled(true);

			QModelIndex tmpIndex;

			tmpIndex = currentIndex.child(currentIndex.row(),
					MessageModel.MessageFields.FROM.getNum());
			ui.lineEditFromMessage.setText((String) ui.tableMessages.model()
					.data(tmpIndex));

			tmpIndex = currentIndex.child(currentIndex.row(),
					MessageModel.MessageFields.TITLE.getNum());
			ui.lineEditTopicMessage.setText((String) ui.tableMessages.model()
					.data(tmpIndex));

			tmpIndex = currentIndex.child(currentIndex.row(), 0);
			ui.plainTextEditMessage.setPlainText((String) ui.tableMessages
					.model().data(tmpIndex,
							MessageModel.MessageRoles.MESSAGETEXT.getNum()));
		} else {
			ui.lineEditFromMessage.setText("");
			ui.lineEditTopicMessage.setText("");
			ui.plainTextEditMessage.setPlainText("");

			ui.buttonReplayMessage.setEnabled(false);
			ui.buttonDeleteMessage.setEnabled(false);
			ui.buttonMarkAsRead.setEnabled(false);
		}

	}

	@SuppressWarnings("unused")
	private void messageSetAsRead() {
		QModelIndex currentIndex = ui.tableMessages.currentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			QModelIndex tmpIndex = currentIndex.child(ui.tableMessages
					.currentIndex().row(), MessageModel.MessageFields.UNREAD
					.getNum());
			ui.tableMessages.model().setData(tmpIndex, false);
		}

	}

	@SuppressWarnings("unused")
	private void messageDeleteMessage() {
		QModelIndex currentIndex = ui.tableMessages.currentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			ui.tableMessages.model().removeRows(currentIndex.row(), 1);
		}

	}

	@SuppressWarnings("unused")
	private void messageNewMessage() {
		Osoba osoba = konfiguracjaService.getLoggedOsoba();
		contactForm cF = new contactForm(osoba.getImie()+" "+osoba.getNazwisko(), "wybierz osobę");
		cF.exec();
		
		TrescKomunikatu trescKomunikatu = new TrescKomunikatu(cF.getTekst(), cF.getTemat());
		trescKomunikatuService.save(trescKomunikatu);
		komunikatService.save(new Komunikat(osoba,cF.getOsobaDo(),trescKomunikatu, new Date(System.currentTimeMillis()), null));
		
		System.out.println(cF.getTemat());

	}

	@SuppressWarnings("unused")
	private void messageReplay() {
		QModelIndex currentIndex = ui.tableMessages.currentIndex();

		if (Helpers.indexIsValid(currentIndex)) {

			String from, to, title;
			QModelIndex tmpIndex;

			tmpIndex = currentIndex.child(
					ui.tableMessages.currentIndex().row(),
					MessageModel.MessageFields.FROM.getNum());
			from = (String) ui.tableMessages.model().data(tmpIndex);

			tmpIndex = currentIndex.child(
					ui.tableMessages.currentIndex().row(), 0);
			to = (String) ui.tableMessages.model().data(tmpIndex,
					MessageModel.MessageRoles.TO.getNum());

			tmpIndex = currentIndex.child(
					ui.tableMessages.currentIndex().row(),
					MessageModel.MessageFields.TITLE.getNum());
			title = (String) ui.tableMessages.model().data(tmpIndex);

			title = tr("Re: ") + title;

			// Need to cross the values because this is a response to sender
			contactForm cF = new contactForm(to, from, title);
			cF.exec();
		}

	}

	/*
	 * Messages tab functions ends here
	 */
	@SuppressWarnings("unused")
	private void addJustification() {
		if (absenceModel.getActuallAbsenceMock(ui.tableAbsences.currentIndex())
				.getHowMuchAbsence() != 0) {
			justificationForm justification = new justificationForm(this,
					absenceModel.getActuallAbsenceMock(ui.tableAbsences
							.currentIndex()), justificationModel);
			justification.exec();
			ui.listJustifications.reset();
		}
	}
	
	/*
	 * Contest tab
	 */
	
	void editContestTypeDialog(){
		contestTypeDialog.show();
	}
	
	/*
	 * Contest tab ends here
	 */
}

