package pl.polsl.bd2;

import pl.polsl.bd2.gui.MainWindow;
import pl.polsl.bd2.helpers.DummyDataLoader;
import pl.polsl.bd2.helpers.SpringUtil;

import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;

public final class applicationMain {

	public static void main(String[] args) {
		SpringUtil.setContext("BeanLocations.xml");
		DummyDataLoader.tryToLoad();
		QApplication.initialize(args);
		QTranslator translator = new QTranslator();
		translator.load("classpath:/pl/polsl/bd2/qt_pl.qm" , ".");

		QApplication.installTranslator(translator);
		QTranslator appTranslator = new QTranslator();
		appTranslator.load("classpath:/pl/polsl/bd2/out.qm",".");
		QApplication.installTranslator(appTranslator);
		

		
		MainWindow testMainWindow = new MainWindow();
		testMainWindow.show();

		QApplication.exec();

	}

}
