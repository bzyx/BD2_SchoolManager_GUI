package pl.polsl.bd2.presentation.login;

import javax.security.auth.login.LoginException;

import pl.polsl.bd2.gui.LoginForm;
import pl.polsl.bd2.helpers.Toast;
import pl.polsl.bd2.helpers.login.SimpleLoginPasswordLoginService;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;

public class LoginPresenter implements BasePresenter {
	private final LoginForm loginForm = new LoginForm();
	private Osoba loggedPerson;
	private final SimpleLoginPasswordLoginService loginService;

	public LoginPresenter() {
		loginService = SimpleLoginPasswordLoginService.getLoginService();
	}

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
		
	}

	@Override
	public void makeUpdateOfView() {
	}

	public void login() {

		if (loginForm.exec() == QDialog.DialogCode.Accepted.value()) {

			final String login = loginForm.getLogin();
			final String password = loginForm.getPassword();

			if (!login.equals("") && !password.equals("")) {
				try {
					loggedPerson = loginService.login(login, password);
				} catch (LoginException e) {
					Toast.show("Logowanie",
							"Nie znaleziono użytkownika w bazie");
				} finally {
					clearForm();
				}
			} else {
				QMessageBox.warning(null, "Logowanie",
						"Wypełnij wszystkie dane");
			}

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

	public Osoba getLoggedOsoba() {
		return loggedPerson;
	}
}
