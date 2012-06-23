package pl.polsl.bd2.helpers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.polsl.bd2.messageSystem.models.Role;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.RoleService;

public final class DummyDataLoader {
	private static final String modelDateSholdBe = new String("23-06-2012-20:50");
	
	public static void tryToLoad(){
		//ApplicationContext appContext = new ClassPathXmlApplicationContext(
		//		"BeanLocations.xml");
		ApplicationContext appContext = SpringUtil.getContext();
		KonfiguracjaService konfiguracjaService = (KonfiguracjaService)appContext.getBean("konfiguracjaService");
		System.out.println(konfiguracjaService);
		if (konfiguracjaService.keyExists("modelDate")){
			if ( konfiguracjaService.getValueOrNull("modelDate").compareToIgnoreCase(modelDateSholdBe) != 0 ){
				System.out.println("Wymagana aktualizacja bazy danych.");
				System.out.println("Ale kod jest zakomentowany. Ponieważ lepiej wcześniej wyczyścić bazę.");
				//addData();
				//konfiguracjaService.update("modelDate", modelDateSholdBe);
			} else {
				System.out.println("Aktualizacja bazy nie jest wymagana");
			}

		} else {
			System.out.println("Baza danych była pusta - wprowadzam dane");
			addData();
			konfiguracjaService.add("modelDate", modelDateSholdBe);
		}
		
	}
	private static void addData(){
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"BeanLocations.xml");
		RoleService roleService = (RoleService) appContext.getBean("roleService");
		roleService.save(new Role("Uczen"));
		roleService.save(new Role("Nauczyciel"));
		roleService.save(new Role("Rodzic"));
		roleService.save(new Role("Administrator"));
		
		
	}

}
