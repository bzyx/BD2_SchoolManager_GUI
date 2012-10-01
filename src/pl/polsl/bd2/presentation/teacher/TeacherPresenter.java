package pl.polsl.bd2.presentation.teacher;

import com.trolltech.qt.gui.QDialog;

import pl.polsl.bd2.gui.DictEditorWidget;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Przedmiot;
import pl.polsl.bd2.messageSystem.service.PrzedmiotService;
import pl.polsl.bd2.models.OddzialListModel;
import pl.polsl.bd2.models.SubjectListModel;
import pl.polsl.bd2.models.SubjectTeacherClassConectionTableModel;
import pl.polsl.bd2.models.TeacherListModel;
import pl.polsl.bd2.presentation.BasePresenter;

public class TeacherPresenter implements BasePresenter {
	
	private Ui_MainWindow view;
	private DictEditorWidget dictEditorWidget;
	private TeacherListModel teacherListModel;
	private SubjectListModel subjectListModel;
	private OddzialListModel oddzialListModel;
	private ConnectSubjectTeacher connectSubjectTeacher;
	private PrzedmiotService przedmiotService;
	private SubjectTeacherClassConectionTableModel subjectTeacherClassConectionTableModel;
	
	public TeacherPresenter(Ui_MainWindow view) {
		this.view = view;

	}
	
	public void initModel(){
		dictEditorWidget = new DictEditorWidget();
		teacherListModel = new TeacherListModel();
		subjectListModel = new SubjectListModel();
		oddzialListModel = new OddzialListModel();
		connectSubjectTeacher = new ConnectSubjectTeacher();
		przedmiotService = (PrzedmiotService) SpringUtil.getBean("przedmiotService");
		subjectTeacherClassConectionTableModel = new SubjectTeacherClassConectionTableModel();
		
		view.tableExistingConnectons.setModel(subjectTeacherClassConectionTableModel);
		view.tableExistingConnectons.resizeColumnsToContents();
		view.tableExistingConnectons.horizontalHeader().setStretchLastSection(true);
		view.tableExistingConnectons.verticalHeader().hide();
	}

	@Override
	public void connectSlots() {
		view.buttonManageTeachers.clicked.connect(this, "manageTeachers()");
		view.buttonManageSubjects.clicked.connect(this, "manageSubjects()");
		view.buttonMakeConnection.clicked.connect(this, "makeConnection()");
		view.buttonRemoveConnection.clicked.connect(this, "removeConnection()");
	}

	@Override
	public void makeUpdateOfView() {
		subjectTeacherClassConectionTableModel.updateModel();

	}
	
	public void manageTeachers() {
		dictEditorWidget.setWindowTitle("Zarządzanie nauczycielami");
		dictEditorWidget.setModel(teacherListModel);
		dictEditorWidget.show();
		makeUpdateOfView();
	}
	
	public void manageSubjects(){
		dictEditorWidget.setWindowTitle("Zarządzanie typami przedmiotów");
		dictEditorWidget.setModel(subjectListModel);
		dictEditorWidget.show();
		makeUpdateOfView();
	}
	
	public void makeConnection(){
		connectSubjectTeacher.setNauczycielModel(teacherListModel);
		connectSubjectTeacher.setOddzialModel(oddzialListModel);
		connectSubjectTeacher.setTypPrzedmiotuModel(subjectListModel);
		
		if (connectSubjectTeacher.exec() == QDialog.DialogCode.Accepted.value()){
			przedmiotService.save(new Przedmiot(connectSubjectTeacher.getTypPrzedmiotu(), 
												connectSubjectTeacher.getNauczyciel(),
												connectSubjectTeacher.getOddzial()));
		makeUpdateOfView();
		}
		
	}
	
	public void removeConnection(){
		subjectTeacherClassConectionTableModel.removeRow(view.tableExistingConnectons.currentIndex().row());
		makeUpdateOfView();
	}
	

}
