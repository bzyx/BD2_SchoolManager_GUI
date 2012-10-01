package pl.polsl.bd2.presentation.teacher;

import pl.polsl.bd2.gui.DictEditorWidget;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.models.SubjectListModel;
import pl.polsl.bd2.models.TeacherListModel;
import pl.polsl.bd2.presentation.BasePresenter;

public class TeacherPresenter implements BasePresenter {
	
	private Ui_MainWindow view;
	private DictEditorWidget dictEditorWidget;
	private TeacherListModel teacherListModel;
	private SubjectListModel subjectListModel;
	
	public TeacherPresenter(Ui_MainWindow view) {
		this.view = view;

	}
	
	public void initModel(){
		dictEditorWidget = new DictEditorWidget();
		teacherListModel = new TeacherListModel();
		subjectListModel = new SubjectListModel();
		
	}

	@Override
	public void connectSlots() {
		System.out.println("Łączę");
		view.buttonManageTeachers.clicked.connect(this, "manageTeachers()");
		view.buttonManageSubjects.clicked.connect(this, "manageSubjects()");

	}

	@Override
	public void makeUpdateOfView() {
		// TODO Auto-generated method stub

	}
	
	public void manageTeachers() {
		dictEditorWidget.setWindowTitle("Zarządzanie nauczycielami");
		dictEditorWidget.setModel(teacherListModel);
		dictEditorWidget.show();
	}
	
	public void manageSubjects(){
		dictEditorWidget.setWindowTitle("Zarządzanie typami przedmiotów");
		dictEditorWidget.setModel(subjectListModel);
		dictEditorWidget.show();
		
	}

}
