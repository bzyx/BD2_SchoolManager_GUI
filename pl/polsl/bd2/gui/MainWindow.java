package pl.polsl.bd2.gui;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSignalMapper;
import com.trolltech.qt.gui.*;

import pl.polsl.bd2.models.DetailsDataModel;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.models.MessageModel;
import pl.polsl.bd2.models.DataModel;
import pl.polsl.bd2.models.UserData;
import pl.polsl.bd2.ui.Ui_MainWindow;

public class MainWindow extends QMainWindow {

	Ui_MainWindow ui = new Ui_MainWindow();
	private DetailsDataModel tableDetailsDataModel = new DetailsDataModel();
	private DataModel tableDataModel = new DataModel();
	private UserData userData = new UserData();

	public static void main(String[] args) {
		QApplication.initialize(args);

		MainWindow testMainWindow = new MainWindow();
		testMainWindow.show();

		QApplication.exec();
	}

	public MainWindow() {
		ui.setupUi(this);
		// Data tab
		UserData userData = new UserData();
		ui.tableDetailsData.setVisible(false);
		for (UserData.UserDataMock a : userData.getUserDataConteiner()) {
			ui.comboBoxStudent.addItem(a.getName());
		}
		this.tableDataModel.setDataContainer(this.userData.getUserDataConteiner().get(0).getData());
		ui.tableData.setModel(this.tableDataModel);
		ui.tableData.resizeColumnsToContents();
		ui.labelProgramInData.setText(this.tableDataModel.getDataContainer().get(0).getSubject());
		this.tableDetailsDataModel.setDetailsDataContainer(this.tableDataModel.getDataContainer().get(0).getDetailsData());
		ui.tableDetailsData.setModel(this.tableDetailsDataModel);
		ui.tableDetailsData.resizeColumnsToContents();
		
		connectSignalsAndSlots();

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
		ui.tableData.activated.connect(mapperToogleTableDetailsData, "map()");
		mapperToogleTableDetailsData.mappedInteger.connect(this,
				"toogleTableDetailsData(int)");
		ui.tableData.activated.connect(this, "changeDataDetails()");
		ui.comboBoxStudent.currentIndexChanged.connect(this, "changeUserWithData()");
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
		this.tableDetailsDataModel.setDetailsDataContainer(this.tableDataModel.getDataContainer().get(ui.tableData.currentIndex().row()).getDetailsData());
		ui.labelProgramInData.setText(ui.tableData.model()
				.index(ui.tableData.currentIndex().row(), 0).data().toString());
		ui.tableDetailsData.reset();

	}
	
	@SuppressWarnings("unused")
	private void changeUserWithData() {
		this.tableDataModel.setDataContainer(this.userData.getUserDataConteiner().get(ui.comboBoxStudent.currentIndex()).getData());
		ui.tableData.reset();
		ui.labelProgramInData.setText(this.tableDataModel.getDataContainer().get(0).getSubject());
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

	// TODO: This is so dummy. Need to change it to work fine.
	@SuppressWarnings("unused")
	private void messageNewMessage() {
		contactForm cF = new contactForm("Ala", "Ela");
		cF.exec();

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
}
