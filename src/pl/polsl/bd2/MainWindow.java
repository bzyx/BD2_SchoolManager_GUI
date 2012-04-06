package pl.polsl.bd2;

import com.trolltech.qt.gui.*;

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
    }

    public MainWindow(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
