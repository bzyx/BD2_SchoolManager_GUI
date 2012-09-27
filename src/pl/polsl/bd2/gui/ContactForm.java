package pl.polsl.bd2.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.polsl.bd2.gui.forms.Ui_contactForm;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.service.OsobaService;

import com.trolltech.qt.gui.*;

public class ContactForm extends QDialog {

	Ui_contactForm ui = new Ui_contactForm();

	private OsobaService osobaService;
	private ArrayList<String> osoby;
	private List<Osoba> osobyList;
	private Osoba osobaDo;

	private void contactFormInit() {
		ui.setupUi(this);
		ui.findButton.clicked.connect(this, "findPressed()");
		ui.buttonBox.rejected.connect(this, "reject()");
		ui.buttonBox.accepted.connect(this, "accept()");

		if (osoby == null)
			osoby = new ArrayList<String>();
		
		if (!osoby.isEmpty())
			osoby.clear();
		
		//if (osobyList == null)
		//	osobyList = new ArrayList<Osoba>();
		
		//if (!osobyList.isEmpty())
		//	osobyList.clear();
		
		osobaService = (OsobaService) SpringUtil.getBean("osobaService");
		osobyList =  new ArrayList<Osoba>(new HashSet<Osoba>(osobaService.findAll()));
		
		Collections.sort(osobyList);
		for (Osoba osoba : osobyList) {
			System.out.println(osoba.getIdOsoba() +" "+ osoba.getImie() + " " + osoba.getNazwisko() + " ("
					+ osoba.getRole().getNazwa() + ")");
			osoby.add(osoba.getImie() + " " + osoba.getNazwisko() + " ("
					+ osoba.getRole().getNazwa() + ")");
		}
	}
		
	public ContactForm(String from, String to) {
		contactFormInit();

		ui.fromPerson.setText(from);
		ui.fromPerson.setEnabled(false);
		ui.toPerson.setText(to);
		ui.toPerson.setEnabled(false);

	}

	public ContactForm(String from, String to, String topic) {
		contactFormInit();

		ui.fromPerson.setText(from);
		ui.fromPerson.setEnabled(false);
		ui.toPerson.setText(to);
		ui.toPerson.setEnabled(false);
		ui.topic.setText(topic);
		ui.topic.setEnabled(false);
		ui.findButton.hide();
	}

	@SuppressWarnings("unused")
	private void findPressed() {

		String item = QInputDialog.getItem(null, "Wybór osoby",
				"Wybierz osobę :", osoby);
		try {
			osobaDo = osobyList.get(osoby.indexOf(item));
			ui.toPerson.setText(item);
		} catch (ArrayIndexOutOfBoundsException e) {
			QMessageBox.warning(this, "Błąd", "Nie wybrano osoby");
			osobaDo = null;
		}

	}

	public Osoba getOsobaDo() {
		return osobaDo;
	}

	public String getTemat() {
		return ui.topic.text();
	}

	public String getTekst() {
		return ui.messageText.toPlainText();
	}
}
