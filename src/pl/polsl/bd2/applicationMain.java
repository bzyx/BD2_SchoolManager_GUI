package pl.polsl.bd2;

import pl.polsl.bd2.gui.MainWindow;

import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;

public final class applicationMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QApplication.initialize(args);

		QTranslator translator = new QTranslator();
		translator.load("qt_pl.qm");
		QApplication.installTranslator(translator);
		QTranslator appTranslator = new QTranslator();
		appTranslator.load("out.qm");
		QApplication.installTranslator(appTranslator);

		MainWindow testMainWindow = new MainWindow();
		testMainWindow.show();

		QApplication.exec();

	}

}
