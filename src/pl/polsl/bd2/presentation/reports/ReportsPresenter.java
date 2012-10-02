package pl.polsl.bd2.presentation.reports;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.models.OddzialListModel;
import pl.polsl.bd2.models.StudentListModel;
import pl.polsl.bd2.models.TeacherListModel;
import pl.polsl.bd2.presentation.BasePresenter;

public class ReportsPresenter implements BasePresenter {

	private Ui_MainWindow view;
	
	
	public ReportsPresenter(Ui_MainWindow view) {
		this.view = view;
	}
	
	
	@Override
	public void connectSlots() {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeUpdateOfView() {
		// TODO Auto-generated method stub

	}
	
	public void initModel() {
//		view.comboReportClass.setModel(new OddzialListModel());
	//	view.comboReportStudent.setModel(new StudentListModel());
	//	view.comboReportTeacher.setModel(new TeacherListModel());
	}

	public void generateReport(){
		 //generujRaport(int raportJRXML, , StringdataOd, String dataDo, String parametrKlasa, String parametrUczen)
	}
}
