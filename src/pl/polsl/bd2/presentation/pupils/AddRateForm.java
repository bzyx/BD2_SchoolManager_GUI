package pl.polsl.bd2.presentation.pupils;

import pl.polsl.bd2.gui.forms.Ui_AddRateDialog;

import com.trolltech.qt.gui.QDialog;

public class AddRateForm extends QDialog{
	Ui_AddRateDialog ui = new Ui_AddRateDialog();
	
	public AddRateForm(){
		this.addRateFormInit();
	}

	private void addRateFormInit(){
		ui.setupUi(this);
		ui.buttonBoxAddRate.rejected.connect(this, "reject()");
		ui.buttonBoxAddRate.accepted.connect(this, "accept()");
	}
	
	int getOcena(){
		return ui.spinBoxRate.value();
	}
	
	float getWaga(){
		return (float) ui.spinBoxWaga.value();
	}
}
