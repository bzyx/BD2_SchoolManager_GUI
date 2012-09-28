package pl.polsl.bd2.presentation.pupils;

import pl.polsl.bd2.gui.forms.Ui_AddNoteDialog;

import com.trolltech.qt.gui.QDialog;

public class AddNoteForm extends QDialog{
	Ui_AddNoteDialog ui = new Ui_AddNoteDialog();
	
	public AddNoteForm(){
		this.addNoteFormInit();
	}

	private void addNoteFormInit(){
		ui.setupUi(this);
		ui.buttonBoxAddNote.rejected.connect(this, "reject()");
		ui.buttonBoxAddNote.accepted.connect(this, "accept()");
	}
	
	public String getNote(){
		return ui.plainTextEditNote.toPlainText();
	}
}
