package pl.polsl.bd2.gui;

import pl.polsl.bd2.messageSystem.service.MessageService;
import pl.polsl.bd2.messageSystem.service.mocks.mock_MessageServiceImpl;
import pl.polsl.bd2.models.Person;
import pl.polsl.bd2.models.Student;
import pl.polsl.bd2.ui.Ui_contactForm;

import com.trolltech.qt.gui.*;

public class contactForm extends QDialog {

	Ui_contactForm ui = new Ui_contactForm();
	// FIXME: remove the mock
	MessageService msgService = new mock_MessageServiceImpl();

	public static void main(String[] args) {
		QApplication.initialize(args);

		contactForm testcontactForm = new contactForm();
		testcontactForm.show();

		QApplication.exec();
	}

	public contactForm() {
		ui.setupUi(this);
		ui.findButton.clicked.connect(this, "findPressed()");
		ui.buttonBox.rejected.connect(this, "reject()");

		Student student = (Student) msgService.findPerson(new Person());
		ui.fromPerson.setText(student.getFirstname() + " "
				+ student.getLastname() + " <" + student.getAlbumNo() + ">");

	}

	/*
	 * public contactForm(QWidget parent) { super(parent); ui.setupUi(this); }
	 */

	@SuppressWarnings("unused")
	private void findPressed() {
		QMessageBox.information(this,
				tr("School System Destroyer", "Application name"),
				tr("You have pressed my button"));
	}
}
