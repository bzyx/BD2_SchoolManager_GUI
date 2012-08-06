package pl.polsl.bd2.gui;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.ui.Ui_contactForm;
import com.trolltech.qt.gui.*;

public class contactForm extends QDialog {

	Ui_contactForm ui = new Ui_contactForm();
	// FIXME: remove the mock

	private OsobaService osobaService;
	private ArrayList<String> osoby;
	private List<Osoba> osobyList;
	private Osoba osobaDo;

	private void contactFormInit() {
		ui.setupUi(this);
		ui.findButton.clicked.connect(this, "findPressed()");
		ui.buttonBox.rejected.connect(this, "reject()");
		ui.buttonBox.accepted.connect(this, "accept()");
		
		osobaService = (OsobaService)SpringUtil.getBean("osobaService");
		osoby = (ArrayList<String>) new ArrayList<String>();
		osobyList = new ArrayList<Osoba>(osobaService.findAll());
	}

	public contactForm(String from) {
		contactFormInit();
	}

	public contactForm(String from, String to) {
		contactFormInit();

		ui.fromPerson.setText(from);
		ui.fromPerson.setEnabled(false);
		ui.toPerson.setText(to);
		ui.toPerson.setEnabled(false);

	}

	public contactForm(String from, String to, String topic) {
		contactFormInit();

		ui.fromPerson.setText(from);
		ui.fromPerson.setEnabled(false);
		ui.toPerson.setText(to);
		ui.toPerson.setEnabled(false);
		ui.topic.setText(topic);
		ui.topic.setEnabled(false);
		ui.findButton.hide();
	}

	/*
	 * public contactForm(QWidget parent) { super(parent); ui.setupUi(this); }
	 */

	@SuppressWarnings("unused")
	private void findPressed() {
		
		for (Osoba osoba: osobyList){
			osoby.add(osoba.getImie()+" "+osoba.getNazwisko() + " ("+osoba.getRole().getNazwa()+")");
		}
		
		String item = QInputDialog.getItem(null, "Wybór osoby", "Wybierz osobę :", osoby);
		osobaDo = osobyList.get(osoby.indexOf(item));
		ui.toPerson.setText(item);
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
