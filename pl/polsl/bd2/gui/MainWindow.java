package pl.polsl.bd2.gui;

import com.trolltech.qt.core.QSignalMapper;
import com.trolltech.qt.gui.*;

import pl.polsl.bd2.models.DataColumnName;
import pl.polsl.bd2.models.TableModel;
import pl.polsl.bd2.ui.Ui_MainWindow;

public class MainWindow extends QMainWindow {

	Ui_MainWindow ui = new Ui_MainWindow();
	private TableModel tableDetailsDataModel = new TableModel(DataColumnName.valueOf("DataDetailsTable").returnColumnName());
	public static void main(String[] args) {
		QApplication.initialize(args);

		MainWindow testMainWindow = new MainWindow();
		testMainWindow.show();

		QApplication.exec();
	}

	public MainWindow() {
		ui.setupUi(this);
		connectSignalsAndSlots();
		ui.tableData.setSelectionMode(QAbstractItemView.SelectionMode.SingleSelection);
		ui.tableData.setSelectionBehavior(QAbstractItemView.SelectionBehavior.SelectRows);
		ui.tableData.setModel(new TableModel(DataColumnName.valueOf("DataTable").returnColumnName()));
		ui.tableDetailsData.setModel(this.tableDetailsDataModel);
		//ui.tableDetailsData.setVisible(false);
	}

	public MainWindow(QWidget parent) {
		super(parent);
		ui.setupUi(this);
		connectSignalsAndSlots();
	}

	private void connectSignalsAndSlots() {
		//ui.pushButton.clicked.connect(this, "showContactForm()");
		QSignalMapper mapperToogleTableDetailsData = new QSignalMapper();
		mapperToogleTableDetailsData.setMapping(ui.buttoToogleDetailsData, 1);
		mapperToogleTableDetailsData.setMapping(ui.tableData, 2);
		ui.buttoToogleDetailsData.clicked.connect(mapperToogleTableDetailsData, "map()");
		ui.tableData.activated.connect(mapperToogleTableDetailsData, "map()");
		mapperToogleTableDetailsData.mappedInteger.connect(this, "toogleTableDetailsData(int)");
		ui.tableData.activated.connect(this,"changeDataDetails()");
	}

	@SuppressWarnings("unused")
	private void showContactForm() {
		contactForm cF = new contactForm();
		cF.exec();

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
		//ui.tableDetailsData.setR
		this.tableDetailsDataModel.setRow(ui.tableData.currentIndex().row());
		//this.tableDetailsDataModel;
		ui.tableDetailsData.reset();

	}
}
