package pl.polsl.bd2;

import pl.polsl.bd2.gui.MainWindow;
import pl.polsl.bd2.helpers.DummyDataLoader;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.presentation.login.LoginPresenter;

import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;

public final class ApplicationMain {
	
	public static Osoba loggedOsoba;

	public static void main(String[] args) {
		SpringUtil.setContext("BeanLocations.xml");
		DummyDataLoader.tryToLoad();
		QApplication.initialize(args);
		QTranslator translator = new QTranslator();
		translator.load("classpath:/pl/polsl/bd2/qt_pl.qm", ".");

		QApplication.installTranslator(translator);
		QTranslator appTranslator = new QTranslator();
		appTranslator.load("classpath:/pl/polsl/bd2/out.qm", ".");
		QApplication.installTranslator(appTranslator);
		Osoba loggedPerson;
		MainWindow testMainWindow;

		LoginPresenter loginPresenter = new LoginPresenter();
		loginPresenter.connectSlots();

		loginPresenter.login();
		loggedPerson = loginPresenter.getLoggedOsoba();

		if (loggedPerson != null) {
			loggedOsoba = loggedPerson;
			System.out.println("Zalogowany");
			testMainWindow = new MainWindow();
			testMainWindow.show();
		} else {
			System.out.println("Bląd logowania.");
		}

		QApplication.exec();

	}
	
	public static Osoba getLoggedPerson() {
		return loggedOsoba;
	}
}