package pl.polsl.bd2.gui;

import com.trolltech.qt.gui.*;

import pl.polsl.bd2.ui.Ui_MainWindow;

public class MainWindow extends QMainWindow {

	Ui_MainWindow ui = new Ui_MainWindow();

	public static void main(String[] args) {
		QApplication.initialize(args);

		MainWindow testMainWindow = new MainWindow();
		testMainWindow.show();

		QApplication.exec();
	}

	public MainWindow() {
		ui.setupUi(this);
		connectSignalsAndSlots();
		ui.tableView.setModel(new TableModel());
		ui.label.setText(tr("Hi! Push this -> "));

	}

	public MainWindow(QWidget parent) {
		super(parent);
		ui.setupUi(this);
		connectSignalsAndSlots();
	}

	private void connectSignalsAndSlots() {
		ui.pushButton.clicked.connect(this, "showContactForm()");

	}

	@SuppressWarnings("unused")
	private void showContactForm() {
		contactForm cF = new contactForm();
		cF.exec();

	}
}
