package pl.polsl.bd2.presentation.teacher;

import pl.polsl.bd2.gui.forms.Ui_ConnectSubjectTeacherClassDialog;
import pl.polsl.bd2.messageSystem.models.Nauczyciel;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.TypPrzedmiotu;
import pl.polsl.bd2.models.OddzialListModel;
import pl.polsl.bd2.models.SubjectListModel;
import pl.polsl.bd2.models.TeacherListModel;

import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.*;

public class ConnectSubjectTeacher extends QDialog {

    Ui_ConnectSubjectTeacherClassDialog ui = new Ui_ConnectSubjectTeacherClassDialog();
    private OddzialListModel oddzialListModel;
    private SubjectListModel subjectListModel;
    private TeacherListModel teacherListModel;

    public ConnectSubjectTeacher() {
        ui.setupUi(this);
        
        ui.buttonBox.accepted.connect(this, "accept()");
		ui.buttonBox.rejected.connect(this, "reject()");
    }
    
    public void setOddzialModel(QAbstractListModel oddzialModel){
    	this.oddzialListModel = (OddzialListModel) oddzialModel;
    	ui.comboClass.setModel(oddzialListModel);
    }
    
    public void setTypPrzedmiotuModel(QAbstractListModel przedmiotModel){
    	this.subjectListModel = (SubjectListModel) przedmiotModel;
    	ui.comboSubject.setModel(subjectListModel);
    }
    
    public void setNauczycielModel(QAbstractListModel nauczycielModel){
    	this.teacherListModel = (TeacherListModel) nauczycielModel;
    	ui.comboTeacher.setModel(teacherListModel);
    }
    
    public Oddzial getOddzial(){
    	return (Oddzial) oddzialListModel.data(oddzialListModel.index(ui.comboClass.currentIndex(),0), ItemDataRole.UserRole);
    }
    
    public TypPrzedmiotu getTypPrzedmiotu() {
    	return (TypPrzedmiotu) subjectListModel.data(subjectListModel.index(ui.comboSubject.currentIndex(),0), ItemDataRole.UserRole);   
    }

    public Nauczyciel getNauczyciel() {
    	return (Nauczyciel) teacherListModel.data(teacherListModel.index(ui.comboTeacher.currentIndex(),0), ItemDataRole.UserRole);   	
    }
}
