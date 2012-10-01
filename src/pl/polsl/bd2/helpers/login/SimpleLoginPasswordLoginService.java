package pl.polsl.bd2.helpers.login;

import javax.security.auth.login.LoginException;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.service.OsobaService;

/**
 * Simple login service with plain text login and password authentication
 */
public class SimpleLoginPasswordLoginService implements LoginService {

	@Override
	public Osoba login(String login, String password) throws LoginException {
		final OsobaService osobaService = (OsobaService) SpringUtil
				.getBean("osobaService");
		try {
			final Osoba resultOsoba = osobaService.findByLoginAndPassword(
					login, password);
			return resultOsoba;
		} catch (IndexOutOfBoundsException e) {
			throw new LoginException("Nie znalazlem w bazie");
		}
	}

	@Override
	public void logout() {

	}

}
