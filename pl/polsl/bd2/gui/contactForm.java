package pl.polsl.bd2.gui;

/*
import pl.polsl.bd2.models.Person;
import pl.polsl.bd2.models.Student;
*/
import pl.polsl.bd2.ui.Ui_contactForm;
import com.trolltech.qt.gui.*;

public class contactForm extends QDialog {

	Ui_contactForm ui = new Ui_contactForm();
	// FIXME: remove the mock

	public static void main(String[] args) {
		QApplication.initialize(args);

		contactForm testcontactForm = new contactForm("");
		testcontactForm.show();

		QApplication.exec();
	}

	private void contactFormInit() {
		ui.setupUi(this);
		ui.findButton.clicked.connect(this, "findPressed()");
		ui.buttonBox.rejected.connect(this, "reject()");
	}

	public contactForm(String from) {
		contactFormInit();
		/*
		if (from.equals("")) {
			Student student = (Student) msgService.findPerson(new Person());
			ui.fromPerson
					.setText(student.getFirstname() + " "
							+ student.getLastname() + " <"
							+ student.getAlbumNo() + ">");
		}*/

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
		QMessageBox.information(this,
				tr("School System Destroyer", "Application name"),
				tr("You have pressed my button"));
	}
}
