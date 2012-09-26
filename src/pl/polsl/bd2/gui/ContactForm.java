package pl.polsl.bd2.gui;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.gui.forms.Ui_contactForm;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.service.OsobaService;

import com.trolltech.qt.QThread;
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

		osobaService = (OsobaService) SpringUtil.getBean("osobaService");
		osoby = (ArrayList<String>) new ArrayList<String>();
		
		// Assign `osobyList`
		getPersons();
	}

	public ContactForm(String from) {
		contactFormInit();
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
		for (Osoba osoba : osobyList) {
			osoby.add(osoba.getImie() + " " + osoba.getNazwisko() + " ("
					+ osoba.getRole().getNazwa() + ")");
		}

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
	
	private void getPersons() {
		QThread finderThread = new QThread(new PersonFinder());
		finderThread.start();
	}
	
	class PersonFinder implements Runnable {
		@Override
		public void run() {
			osobyList = new ArrayList<Osoba>(osobaService.findAll());
		}
	}
}
