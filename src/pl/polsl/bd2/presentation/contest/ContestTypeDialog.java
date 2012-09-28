package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.forms.Ui_ContestTypeDialog;
import pl.polsl.bd2.messageSystem.models.TypKonkursu;
import pl.polsl.bd2.models.ContestTypeListModel;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.QDialog;

public class ContestTypeDialog extends QDialog {
	
	Ui_ContestTypeDialog ui;
	ContestTypeListModel contestTypeModel;
	
	public ContestTypeDialog() {
		ui = new Ui_ContestTypeDialog();
		ui.setupUi(this);
		this.setWindowTitle("Dodawanie konkursu.");
		
		contestTypeModel = new ContestTypeListModel();
		this.ui.listKonkursTyp.setModel(contestTypeModel);
		this.ui.listKonkursTyp.setCurrentIndex(contestTypeModel.index(0, 0));
		
		ui.buttonBox.accepted.connect(this, "accept()");
		ui.buttonBox.rejected.connect(this, "reject()");
		
	}
	
	public String getNazwaKonkursu(){
		return ui.lineEditCompetiononType.text();
	}
	
	public TypKonkursu getTypKonkursu(){
		return (TypKonkursu) contestTypeModel.data(ui.listKonkursTyp.currentIndex(), ItemDataRole.UserRole);
	}
	
	public void setNazwaKonkursu(String nazwaKonkursu){
		ui.lineEditCompetiononType.setText(nazwaKonkursu);
	}
	
	public void setTypKonkursu(TypKonkursu typKonkursu){
		
		QModelIndex tmpIndex = contestTypeModel.index(0, 0);
		
		for (int i = 0; i < contestTypeModel.rowCount(); i++ ){
			if ( contestTypeModel.data(tmpIndex).equals(typKonkursu.getNazwa())){
				ui.listKonkursTyp.setCurrentIndex(tmpIndex);
				break;
			}
			
			tmpIndex = contestTypeModel.index(i, 0);
		}
		
	}

	public void setAddMode(boolean b) {
		this.ui.listKonkursTyp.setCurrentIndex(contestTypeModel.index(0, 0));
		if (b)
			this.setWindowTitle("Dodawanie konkursu");
		else
			this.setWindowTitle("Edycja konkursu");
	}
	
	@Override
	public int exec() {
		contestTypeModel.makeUpdate();
		return super.exec();
	}
	
}