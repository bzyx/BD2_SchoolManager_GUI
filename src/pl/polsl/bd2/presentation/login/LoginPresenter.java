package pl.polsl.bd2.presentation.login;

import javax.security.auth.login.LoginException;

import pl.polsl.bd2.gui.LoginForm;
import pl.polsl.bd2.helpers.Toast;
import pl.polsl.bd2.helpers.login.SimpleLoginPasswordLoginService;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.gui.QMessageBox;

public class LoginPresenter implements BasePresenter {
	private final LoginForm loginForm = new LoginForm();
	private Osoba loggedPerson;

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public Osoba getLoggedPerson() {
		return loggedPerson;
	}

	public void setLoggedPerson(Osoba loggedPerson) {
		this.loggedPerson = loggedPerson;
	}

	@Override
	public void connectSlots() {
		loginForm.getUi().buttonBox.accepted.connect(this, LoginSlots.LOGIN);
		loginForm.getUi().buttonBox.rejected
				.connect(this, LoginSlots.CLOSE_APP);
	}

	@Override
	public void makeUpdateOfView() {
	}

	@SuppressWarnings("unused")
	private void login() {
		final String login = loginForm.getLogin();
		final String password = loginForm.getPassword();

		if (!login.equals("") && !password.equals("")) {
			final SimpleLoginPasswordLoginService loginService = SimpleLoginPasswordLoginService
					.getLoginService();
			try {
				// Tutaj przypisuje ale po wyjsciu z tad ma znowu nulla w 'loggedPerson'
				loggedPerson = loginService.login(login, password);
				loginForm.close();
			} catch (LoginException e) {
				Toast.show("Logowanie", "Nie Znaleziono u¿ytkownika w bazie");
			} finally {
				clearForm();
			}
		} else {
			QMessageBox.warning(null, "Logowanie", "Wype³nij wszystkie dane");
		}
	}

	@SuppressWarnings("unused")
	private void closeApp() {
		loginForm.close();
	}

	private void clearForm() {
		loginForm.getUi().lineEdit.setText("");
		loginForm.getUi().lineEdit_2.setText("");
	}
}
