package pl.polsl.bd2.presentation.login;

import javax.security.auth.login.LoginException;

import pl.polsl.bd2.messageSystem.models.Osoba;

public interface LoginService {

	/**
	 * @throws LoginException
	 *             when person cannot be found in datasource
	 */
	Osoba login(String login, String password) throws LoginException;

	void logout();
}
