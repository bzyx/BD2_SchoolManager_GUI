package pl.polsl.bd2.helpers.login;

import javax.security.auth.login.LoginException;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.service.OsobaService;

/**
 * Simple login service with plain text login and password authentication
 */
public class SimpleLoginPasswordLoginService implements LoginService {
	private Osoba loggedPerson;

	private SimpleLoginPasswordLoginService() {
	}

	public Osoba getLoggedPerson() {
		return loggedPerson;
	}

	public void setLoggedPerson(Osoba loggedPerson) {
		this.loggedPerson = loggedPerson;
	}

	@Override
	public Osoba login(String login, String password) throws LoginException {
		final OsobaService osobaService = (OsobaService) SpringUtil
				.getBean("osobaService");
		try {
			return osobaService.findByLoginAndPassword(login, password);
		} catch (IndexOutOfBoundsException e) {
			throw new LoginException("Nie znalazlem w bazie");
		}
	}

	@Override
	public void logout() {
	}

	private static final SimpleLoginPasswordLoginService instance = new SimpleLoginPasswordLoginService();

	public static SimpleLoginPasswordLoginService getLoginService() {
		return instance;
	}

}
