package pl.polsl.bd2.gui;

import java.util.Date;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSignalMapper;
import com.trolltech.qt.gui.*;


import pl.polsl.bd2.messageSystem.models.Komunikat;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.TrescKomunikatu;
import pl.polsl.bd2.messageSystem.service.KomunikatService;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.messageSystem.service.TrescKomunikatuService;
import pl.polsl.bd2.models.DetailsDataModel;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.models.AbsenceModel;
import pl.polsl.bd2.models.JustificationModel;
import pl.polsl.bd2.models.MessageModel;
import pl.polsl.bd2.models.DataModel;
import pl.polsl.bd2.models.UserData;
import pl.polsl.bd2.ui.Ui_MainWindow;

public class MainWindow extends QMainWindow {

	Ui_MainWindow ui = new Ui_MainWindow();
	private DetailsDataModel tableDetailsDataModel = new DetailsDataModel();
	private DataModel tableDataModel = new DataModel();
	private UserData userData = new UserData();
	private AbsenceModel absenceModel = new AbsenceModel();
	private JustificationModel justificationModel = new JustificationModel();
	private KonfiguracjaService konfiguracjaService;
	private OsobaService osobaService;
	private KomunikatService komunikatService;
	private TrescKomunikatuService trescKomunikatuService;

	public static void main(String[] args) {
		QApplication.initialize(args);

		MainWindow testMainWindow = new MainWindow();
		testMainWindow.show();

		QApplication.exec();
	}

	public MainWindow() {
		ui.setupUi(this);
		
		konfiguracjaService = (KonfiguracjaService) SpringUtil.getBean("konfiguracjaService");
		komunikatService = (KomunikatService) SpringUtil.getBean("komunikatService");
		trescKomunikatuService = (TrescKomunikatuService) SpringUtil.getBean("trescKomunikatuService");
		osobaService = (OsobaService) SpringUtil.getBean("osobaService");
		konfiguracjaService.setLoggedOsoba(osobaService.findAll().get(4));
		
		Osoba osoba = konfiguracjaService.getLoggedOsoba();
		System.out.println(osoba.getImie() + " " + osoba.getNazwisko());
		//konfiguracjaService.setLoggedOsoba(null);
		
		/*
		 * DataTab tab functions starts here
		 */
		UserData userData = new UserData();
		ui.tableDetailsData.setVisible(false);
		for (UserData.UserDataMock a : userData.getUserDataConteiner()) {
			ui.comboBoxStudent.addItem(a.getName());
		}
		ui.tableData.selectRow(0);
		this.tableDataModel.setDataContainer(this.userData
				.getUserDataConteiner().get(0).getData());
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
	private void changeDataDetails() {
		this.tableDetailsDataModel.setDetailsDataContainer(this.tableDataModel
				.getDataContainer().get(ui.tableData.currentIndex().row())
				.getDetailsData());
		ui.labelProgramInData.setText(ui.tableData.model()
				.index(ui.tableData.currentIndex().row(), 0).data().toString());
		ui.tableDetailsData.reset();

	}

	@SuppressWarnings("unused")
	private void changeUserWithData() {
		this.tableDataModel.setDataContainer(this.userData
				.getUserDataConteiner().get(ui.comboBoxStudent.currentIndex())
				.getData());
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
}
