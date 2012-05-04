package pl.polsl.bd2.gui;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSignalMapper;
import com.trolltech.qt.gui.*;

import pl.polsl.bd2.models.DataColumnName;
import pl.polsl.bd2.models.MessageModel;
import pl.polsl.bd2.models.TableModel;
import pl.polsl.bd2.ui.Ui_MainWindow;

public class MainWindow extends QMainWindow {

	Ui_MainWindow ui = new Ui_MainWindow();
	private TableModel tableDetailsDataModel = new TableModel(DataColumnName
			.valueOf("DataDetailsTable").returnColumnName());

	public static void main(String[] args) {
		QApplication.initialize(args);

		MainWindow testMainWindow = new MainWindow();
		testMainWindow.show();

		QApplication.exec();
	}

	public MainWindow() {
		ui.setupUi(this);
		ui.tableDetailsData.setVisible(false);
		ui.tableData.setModel(new TableModel(DataColumnName
				.valueOf("DataTable").returnColumnName()));
		ui.tableDetailsData.setModel(this.tableDetailsDataModel);
		ui.labelProgramInData.setText(ui.tableData.model().index(0, 0).data()
				.toString());
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
		this.tableDetailsDataModel.setRow(ui.tableData.currentIndex().row());
		ui.labelProgramInData.setText(ui.tableData.model()
				.index(ui.tableData.currentIndex().row(), 0).data().toString());
		ui.tableDetailsData.reset();

	}

	/*
	 * Messages tab functions starts here
	 */
	@SuppressWarnings("unused")
	private void messageChanged(QItemSelection is, QItemSelection was) {
		ui.buttonReplayMessage.setEnabled(true);
		ui.buttonDeleteMessage.setEnabled(true);
		ui.buttonMarkAsRead.setEnabled(true);

		QModelIndex currentIndex = ui.tableMessages.currentIndex();
		QModelIndex tmpIndex;

		tmpIndex = currentIndex.child(currentIndex.row(),
				MessageModel.MessageFields.FROM.getNum());
		ui.lineEditFromMessage.setText((String) ui.tableMessages.model().data(
				tmpIndex));

		tmpIndex = currentIndex.child(currentIndex.row(),
				MessageModel.MessageFields.TITLE.getNum());
		ui.lineEditTopicMessage.setText((String) ui.tableMessages.model().data(
				tmpIndex));

		tmpIndex = currentIndex.child(currentIndex.row(), 0);
		ui.plainTextEditMessage
				.setPlainText((String) ui.tableMessages.model().data(tmpIndex,
						MessageModel.MessageRoles.MESSAGETEXT.getNum()));

	}

	@SuppressWarnings("unused")
	private void messageSetAsRead() {
		QModelIndex currentIndex = ui.tableMessages.currentIndex().child(
				ui.tableMessages.currentIndex().row(),
				MessageModel.MessageFields.UNREAD.getNum());

		ui.tableMessages.model().setData(currentIndex, false);

	}

	@SuppressWarnings("unused")
	private void messageDeleteMessage() {
		QModelIndex currentIndex = ui.tableMessages.currentIndex();
		ui.tableMessages.model().removeRows(currentIndex.row(), 1);

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

		if (currentIndex != null && currentIndex.row() > 0
				&& currentIndex.row() < ui.tableMessages.model().rowCount()) {

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
