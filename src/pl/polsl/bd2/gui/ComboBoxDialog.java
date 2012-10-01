package pl.polsl.bd2.gui;

import pl.polsl.bd2.gui.forms.Ui_ComboBoxDialog;

import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.*;

public class ComboBoxDialog extends QDialog {

    Ui_ComboBoxDialog ui = new Ui_ComboBoxDialog();
    QAbstractListModel model;

    public void setModel(QAbstractListModel model) {
		this.model = model;
		ui.comboBox.setModel(model);
	}

    public ComboBoxDialog() {
        ui.setupUi(this);
		ui.buttonBox.accepted.connect(this, "accept()");
		ui.buttonBox.rejected.connect(this, "reject()");
    }

    public Object getCurrentItem(){
		return model.data(model.index(ui.comboBox.currentIndex(), 0), ItemDataRole.UserRole);
    }
    
    @Override
    public int exec() {
    	return super.exec();
    }
}
