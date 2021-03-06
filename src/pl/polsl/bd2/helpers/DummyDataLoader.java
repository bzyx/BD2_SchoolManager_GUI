package pl.polsl.bd2.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import pl.polsl.bd2.messageSystem.models.Komunikat;
import pl.polsl.bd2.messageSystem.models.Konkurs;
import pl.polsl.bd2.messageSystem.models.Nauczyciel;
import pl.polsl.bd2.messageSystem.models.Ocena;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Przedmiot;
import pl.polsl.bd2.messageSystem.models.Role;
import pl.polsl.bd2.messageSystem.models.TrescKomunikatu;
import pl.polsl.bd2.messageSystem.models.TypKonkursu;
import pl.polsl.bd2.messageSystem.models.TypPrzedmiotu;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.models.WynikKonkursu;
import pl.polsl.bd2.messageSystem.service.KomunikatService;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.KonkursService;
import pl.polsl.bd2.messageSystem.service.NauczycielService;
import pl.polsl.bd2.messageSystem.service.OcenaService;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.messageSystem.service.PrzedmiotService;
import pl.polsl.bd2.messageSystem.service.RoleService;
import pl.polsl.bd2.messageSystem.service.TrescKomunikatuService;
import pl.polsl.bd2.messageSystem.service.TypKonkursuService;
import pl.polsl.bd2.messageSystem.service.TypPrzedmiotuService;
import pl.polsl.bd2.messageSystem.service.UczenService;
import pl.polsl.bd2.messageSystem.service.WynikKonkursuService;

public final class DummyDataLoader {
	private static final String modelDateSholdBe = new String(
			"23-06-2012-20:50");

	public static void tryToLoad() {
		
		ApplicationContext appContext = SpringUtil.getContext();
		
		KonfiguracjaService konfiguracjaService = (KonfiguracjaService) appContext
				.getBean("konfiguracjaService");
		if (konfiguracjaService.keyExists("modelDate")) {
			System.out.println(konfiguracjaService.getValueOrNull("modelDate"));
			if (konfiguracjaService.getValueOrNull("modelDate")
					.compareToIgnoreCase(modelDateSholdBe) != 0) {
				System.out.println("Wymagana aktualizacja bazy danych.");
				System.out
						.println("Ale kod jest zakomentowany. Ponieważ lepiej wcześniej wyczyścić bazę.");
				 // addData();
				 // konfiguracjaService.update("modelDate", modelDateSholdBe);
			} else {
				System.out.println("Aktualizacja bazy nie jest wymagana");
				
				
			}

		} else {
			System.out.println("Baza danych była pusta - wprowadzam dane");
			konfiguracjaService.add("modelDate", modelDateSholdBe);
			addData();
		}

	}

	private static void addData() {
		/*
		 * Dodaj kilka ról.
		 */
		RoleService roleService = (RoleService) SpringUtil
				.getBean("roleService");

		roleService.save(new Role("Uczen"));
		roleService.save(new Role("Nauczyciel"));
		roleService.save(new Role("Rodzic"));
		roleService.save(new Role("Administrator"));

		/*
		 * Dodaj kilka osób i przypisz im role.
		 */
		OsobaService osobaService = (OsobaService) SpringUtil
				.getBean("osobaService");
		// adam/adam -> administrator
		osobaService.save(new Osoba("Adam", "Tester", "Długa", "Rybnik",
				"adam@mail.com", "adam", "adam", roleService.findByName(
						"Administrator").get(0)));
		// ala/ala -> rodzic
		osobaService.save(new Osoba("Alicja", "Jaroch", "Długi Targ", "Gdańsk",
				"ala@gmail.com", "ala", "ala", roleService.findByName("Rodzic")
						.get(0)));
		// asia/asia -> rodzic
		osobaService.save(new Osoba("Joanna", "Liszka", "Żorska", "Rybnik",
				"sia@gmail.com", "asia", "asia", roleService.findByName(
						"Rodzic").get(0)));
		// iza/iza -> nauczyciel
		osobaService.save(new Osoba("Izabela", "Piko", "Krótka", "Rybnik",
				"izis@gmail.com", "iza", "iza", roleService.findByName(
						"Nauczyciel").get(0)));
		// leszek/leszek -> nauczyciel
		osobaService.save(new Osoba("Leszek", "Gacek", "Przemysłowa 15b",
				"Wodzisław Śląski", "lechu@op.pl", "leszek", "leszek",
				roleService.findByName("Nauczyciel").get(0)));
		// marek/marek -> nauczyciel
		osobaService.save(new Osoba("Marek", "Ognik", "Główna 100", "Żory",
				"m@op.pl", "marek", "marek", roleService.findByName(
						"Nauczyciel").get(0)));
		// zosia/zosia -> nauczyciel
		osobaService.save(new Osoba("Zofia", "Konopka", "Pod lasem 1",
				"Czerwonka-Leszczyny", "zoska@konpka.pl", "zosia", "zosia",
				roleService.findByName("Nauczyciel").get(0)));
		// ania/ania -> nauczyciel
		osobaService.save(new Osoba("Anna", "Kostka", "Wiejska", "Lyski",
				"ania@op.pl", "ania", "ania", roleService.findByName(
						"Nauczyciel").get(0)));
		// patryk/patryk -> uczen
		osobaService.save(new Osoba("Patryk", "Ryżyk", "Wolna 298",
				"Jastrzębie Zdrój", "-", "patryk", "patryk", roleService
						.findByName("Uczen").get(0)));
		// bartek/bartek -> uczen
		osobaService.save(new Osoba("Barek", "Ryżyk", "Wolna 298",
				"Jastrzębie Zdrój", "-", "bartek", "bartek", roleService
						.findByName("Uczen").get(0)));
		// karolina/karolina -> uczen
		osobaService.save(new Osoba("Karolina", "Wolna", "Śieniewicza 13",
				"Ząbkowice Wielkie", "karolcia99@wp.pl", "karolina",
				"karolina", roleService.findByName("Uczen").get(0)));
		System.out.println(roleService.findAll().size());
		
		osobaService.save(new Osoba("Karolina", "Wolniak", "Śieniewicza 13",
				"Ząbkowice Wielkie", "karolcia99@wp.pl", "karolina",
				"karolina", roleService.findByName("Uczen").get(0)));
		System.out.println(roleService.findAll().size());
		
		osobaService.save(new Osoba("Karolina", "Test", "Śieniewicza 13",
				"Ząbkowice Wielkie", "karolcia99@wp.pl", "karolina",
				"karolina", roleService.findByName("Uczen").get(0)));
		System.out.println(roleService.findAll().size());
		
		osobaService.save(new Osoba("Karolina", "Maj", "Śieniewicza 13",
				"Ząbkowice Wielkie", "karolcia99@wp.pl", "karolina",
				"karolina", roleService.findByName("Uczen").get(0)));
		System.out.println(roleService.findAll().size());
		
		osobaService.save(new Osoba("Karolina", "Czerwiec", "Śieniewicza 13",
				"Ząbkowice Wielkie", "karolcia99@wp.pl", "karolina",
				"karolina", roleService.findByName("Uczen").get(0)));
		System.out.println(roleService.findAll().size());
		/*
		 * Dodamy parę treści i komunikatów.
		 */
		TrescKomunikatuService trescKomunikatuService = (TrescKomunikatuService) SpringUtil
				.getBean("trescKomunikatuService");
		trescKomunikatuService
				.save(new TrescKomunikatu(
						"Aniu, sprawdź te ostatnie sprawdziany bo nie wiem co z ocenami.", "Sprawdziany 2a"));
		trescKomunikatuService.save(new TrescKomunikatu(
				"Idziemy na wycieczkę? ", "Wycieczka"));
		trescKomunikatuService.save(new TrescKomunikatu(
				"Pilne spotkanie w pokoju nauczycielskim. Jutro o 15:00", "Spotkanie"));

		KomunikatService komunikatService = (KomunikatService) SpringUtil
				.getBean("komunikatService");
		komunikatService.save(new Komunikat(osobaService.findAll().get(3),
				osobaService.findAll().get(4), trescKomunikatuService.findAll()
						.get(0), new Date(System.currentTimeMillis()), new Date() ) );
		komunikatService.save(new Komunikat(osobaService.findAll().get(4), osobaService.findAll().get(5), trescKomunikatuService.findAll().get(1), new Date(
				System.currentTimeMillis()), new Date(System
				.currentTimeMillis() + 96 * 60 * 60 * 100)));
		komunikatService.save(new Komunikat(osobaService.findAll().get(3), osobaService.findAll().get(4), trescKomunikatuService.findAll().get(2), null, null));
		/*
		 * Dodamy parę oddziałów
		 * tutaj chyba miała być klasa ;p
		 */
		OddzialService oddzialService = (OddzialService) SpringUtil.getBean("oddzialService");
		oddzialService.save(new Oddzial("Automatyki"));
		oddzialService.save(new Oddzial("Elektroniki"));
		oddzialService.save(new Oddzial("Informatyki"));
		/*
		 * Dodamy parę uczniów
		 */
		UczenService uczenService = (UczenService)SpringUtil.getBean("uczenService");
		List<Osoba> listaOsobUczniow = new ArrayList<Osoba>(roleService.findByName("Uczen").get(0).getRole2osoba());
		List<Oddzial> listaOddzialow = new ArrayList<Oddzial>(oddzialService.findAll());
		uczenService.save(new Uczen(listaOsobUczniow.get(0), listaOddzialow.get(0)));
		uczenService.save(new Uczen(listaOsobUczniow.get(1), listaOddzialow.get(0)));
		uczenService.save(new Uczen(listaOsobUczniow.get(2), listaOddzialow.get(1)));
		
		/*
		 * Dodamy parę typow przedmiotow
		 */
		TypPrzedmiotuService typPrzedmiotuService = (TypPrzedmiotuService)SpringUtil.getBean("typPrzedmiotuService");
		typPrzedmiotuService.save(new TypPrzedmiotu("Polski"));
		typPrzedmiotuService.save(new TypPrzedmiotu("Angielski"));
		typPrzedmiotuService.save(new TypPrzedmiotu("Matematyka"));
		typPrzedmiotuService.save(new TypPrzedmiotu("Informatyka"));
		
		/*
		 * Dodamy parę nauczycieli
		 */
		NauczycielService nauczycielService = (NauczycielService)SpringUtil.getBean("nauczycielService");
		List<Osoba> listaOsobNauczycieli = new ArrayList<Osoba>(roleService.findByName("Nauczyciel").get(0).getRole2osoba());
		for(Osoba nauczyciel: listaOsobNauczycieli){
			nauczycielService.save(new Nauczyciel(nauczyciel, listaOddzialow.get(0)));
		}
		
		/*
		 * Dodajemy typy konkursów
		 */
		
		TypKonkursuService  typKonkursuService = (TypKonkursuService)SpringUtil
				.getBean("typKonkursuService");
		TypKonkursu olimpiada = new TypKonkursu("Konkurs olimpiada przedmiotowa");
		TypKonkursu szkolny = new TypKonkursu("Konkurs szkolny");
		typKonkursuService.save(szkolny);
		typKonkursuService.save(new TypKonkursu("Konkurs regionalny"));
		typKonkursuService.save(new TypKonkursu("Konkurs wojewódzki"));
		typKonkursuService.save(new TypKonkursu("Konkurs ogólnokrajowy"));
		typKonkursuService.save(olimpiada);
		typKonkursuService.save(new TypKonkursu("Konkurs międzynarodowy"));
		
		/*
		 * Dodajemy typów wyników konkursu
		 */
		
		WynikKonkursuService wynikKonkursuService = (WynikKonkursuService)SpringUtil
				.getBean("wynikKonkursuService");
		wynikKonkursuService.save(new WynikKonkursu("brak", "Konkurs w trakcie - wynik nieznany."));
		wynikKonkursuService.save(new WynikKonkursu("I miejsce", "Główna nagroda w konkursie, laureat."));
		wynikKonkursuService.save(new WynikKonkursu("II miejsce", "Druga nagroda"));
		wynikKonkursuService.save(new WynikKonkursu("III miejsce", "Trzecia nagroda"));
		wynikKonkursuService.save(new WynikKonkursu("Finalista", "Osoba będąca w finale konkrsu."));
		wynikKonkursuService.save(new WynikKonkursu("Nagroda pocieszenia", "Specjalna nagroda dla uczestnika."));
		wynikKonkursuService.save(new WynikKonkursu("Uczestnik", "Osoba biorąca udział w konkursie."));
		
		
		KonkursService konkursService = (KonkursService) SpringUtil.getBean("konkursService");
		konkursService.save(new Konkurs(szkolny, "Konkurs recytatorski"));
		konkursService.save(new Konkurs(olimpiada, "Olimpiada Informatyczna"));
		
		/*
		 * Dodamy parę przedmiotow
		 */
		PrzedmiotService przedmiotService = (PrzedmiotService)SpringUtil.getBean("przedmiotService");
		List<TypPrzedmiotu> listaTypPrzedmiotow = new ArrayList<TypPrzedmiotu>(typPrzedmiotuService.findAll());
		List<Nauczyciel> listaNauczycieli = new ArrayList<Nauczyciel>(nauczycielService.findAll());

		przedmiotService.save(new Przedmiot(listaTypPrzedmiotow.get(0), listaNauczycieli.get(0), listaOddzialow.get(0)));
		przedmiotService.save(new Przedmiot(listaTypPrzedmiotow.get(1), listaNauczycieli.get(1), listaOddzialow.get(0)));
		przedmiotService.save(new Przedmiot(listaTypPrzedmiotow.get(2), listaNauczycieli.get(2), listaOddzialow.get(1)));
		przedmiotService.save(new Przedmiot(listaTypPrzedmiotow.get(3), listaNauczycieli.get(3), listaOddzialow.get(1)));
		
		/*
		 * Dodamy kilka nieobecnosci
		 */
		//AbsencjaService absencjaService = (AbsencjaService)SpringUtil.getBean("absencjaService");
		List<Uczen> listaUczniow = new ArrayList<Uczen>(uczenService.findAll());
		List<Przedmiot> listaPrzedmiotow = new ArrayList<Przedmiot>(przedmiotService.findAll());
		/*
		 * Dodamy kilka ocen dla uczniów
		 * bez sensu przedmiot zalezy od nauczyciela, mozna dodac ocene uczniowi za przedmiot ktorego nie ma
		 * ale narazie to pomine. POWINIEN BYC PIERW ZROBIONY NAUCZYCIEL i ADMINISTRATOR WTEDY BY SIE DODAWALO Z POZIOMU PROGRAMU
		 * A NIE W TAKI SMIESZNY SPOSOB
		 */
		OcenaService ocenaService = (OcenaService)SpringUtil.getBean("ocenaService");
		Date date = new Date();
		ocenaService.save(new Ocena(listaUczniow.get(0), listaPrzedmiotow.get(0), listaNauczycieli.get(0), 5, 1.0f , date));
		/*ocenaService.save(new Ocena(listaUczniow.get(1), listaPrzedmiotow.get(1), listaNauczycieli.get(3), 4, 1.0f, new Date()));
		ocenaService.save(new Ocena(listaUczniow.get(1), listaPrzedmiotow.get(0), listaNauczycieli.get(0), 3, 1, new Date()));
		ocenaService.save(new Ocena(listaUczniow.get(1), listaPrzedmiotow.get(1), listaNauczycieli.get(1), 2, 1, new Date()));
		ocenaService.save(new Ocena(listaUczniow.get(1), listaPrzedmiotow.get(1), listaNauczycieli.get(0), 3, 1, new Date()));
		ocenaService.save(new Ocena(listaUczniow.get(2), listaPrzedmiotow.get(2), listaNauczycieli.get(1), 4, 1, new Date()));
		ocenaService.save(new Ocena(listaUczniow.get(2), listaPrzedmiotow.get(2), listaNauczycieli.get(0), 5, 1, new Date()));
		ocenaService.save(new Ocena(listaUczniow.get(2), listaPrzedmiotow.get(3), listaNauczycieli.get(1), 4, 1, new Date()));
*/
		//List<Ocena> listaOcen = new ArrayList<Ocena>(ocenaService.findBySubject(
			//	listaPrzedmiotow.get(0), listaUczniow.get(0)));
		
		
		//FIXME:  java.lang.ClassCastException: java.util.Date cannot be cast to java.sql.Date
		/*absencjaService.save(new Absencja(listaUczniow.get(0),listaPrzedmiotow.get(0) ,true ,(java.sql.Date) new Date()));
		absencjaService.save(new Absencja(listaUczniow.get(1),listaPrzedmiotow.get(0) ,false ,(java.sql.Date) new Date()));
		absencjaService.save(new Absencja(listaUczniow.get(2),listaPrzedmiotow.get(0) ,false ,(java.sql.Date) new Date()));
		absencjaService.save(new Absencja(listaUczniow.get(0),listaPrzedmiotow.get(1) ,false ,(java.sql.Date) new Date()));
		absencjaService.save(new Absencja(listaUczniow.get(1),listaPrzedmiotow.get(1) ,true ,(java.sql.Date) new Date()));
		absencjaService.save(new Absencja(listaUczniow.get(2),listaPrzedmiotow.get(1) ,true ,(java.sql.Date) new Date()));*/
		//TODO: Dlaczego nie można dodać 1 treści komuniaktu do kilku komunikatów/do kilku ludzi/od 1 osoby
		/*komunikatService.save(new Komunikat(osobaService.findAll().get(3), osobaService.findAll().get(5), trescKomunikatuService.findAll().get(2), null, null));
		komunikatService.save(new Komunikat(osobaService.findAll().get(3), osobaService.findAll().get(6), trescKomunikatuService.findAll().get(2), null, null));
		komunikatService.save(new Komunikat(osobaService.findAll().get(3), osobaService.findAll().get(7), trescKomunikatuService.findAll().get(2), null, null));*/
		//FIXME: KKK Dodać klika oddziłów i dodać uczniów do konkretnych oddzialow
		//TODO: KKK Do oddziału dodać id nauczyciela (wychowawcy kiedys)
		//FIXME: KKK Dodać kilka przedmiotów 
		//TODO: zmienić zapytanie w implementacji Dao osoby do wyszukiwania po nazwie roli na takie korzystajace z roli
		//FIXME: KKK Dodac kilku nauczycieli o ile juz nie ma przydzielic przedmioty do konkretnego nauczyciela i oddzialu
		//TODO: KKK Dodać kilka nieobecnosci konkretnym uczniom na konkretnym przedmiocie
		//TODO: KKK Dodać kilka ocen uczniom na konkretnym przedmiocie danych przez konkretnego nauczyciela i tak dalej ;p

	}

}
