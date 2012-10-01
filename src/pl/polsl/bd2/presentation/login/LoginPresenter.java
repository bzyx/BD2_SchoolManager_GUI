package pl.polsl.bd2.presentation.login;

import javax.security.auth.login.LoginException;

import pl.polsl.bd2.gui.LoginForm;
import pl.polsl.bd2.helpers.Toast;
import pl.polsl.bd2.helpers.login.LoginService;
import pl.polsl.bd2.helpers.login.SimpleLoginPasswordLoginService;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.gui.QMessageBox;

public class LoginPresenter implements BasePresenter {
	final LoginForm loginForm = new LoginForm();

	public LoginForm getLoginForm() {
		return loginForm;
	}

	@Override
	public void connectSlots() {
		loginForm.getUi().buttonBox.accepted.connect(this, LoginSlots.LOGIN);
		loginForm.getUi().buttonBox.rejected.connect(this, LoginSlots.CLOSE_APP);
	}

	@Override
	public void makeUpdateOfView() {
	}

	@SuppressWarnings("unused")
	private void login() {
		final String login = loginForm.getLogin();
		final String password = loginForm.getPassword();

		if (!login.equals("") && !password.equals("")) {
			final LoginService loginService = new SimpleLoginPasswordLoginService();
			try {
				final Osoba loggedPerson = loginService.login(login, password);
				loginForm.close();

				System.err.println("Logged person: " + loggedPerson.toString());
			} catch (LoginException e) {
				Toast.show("Logowanie",
						"Nie Znaleziono u¿ytkownika w bazie");
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
