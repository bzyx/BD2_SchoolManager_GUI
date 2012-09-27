package pl.polsl.bd2.presentation.data;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.UczenService;
import pl.polsl.bd2.models.AbsenceTableModel;
import pl.polsl.bd2.models.DetailsDataModel;
import pl.polsl.bd2.models.GradesTableModel;
import pl.polsl.bd2.models.StudentListModel;
import pl.polsl.bd2.models.SubjectListModel;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.core.Qt.ItemDataRole;

public class DataPresenter implements BasePresenter {

	private Ui_MainWindow view;
	private GradesTableModel gradesTableModel;
	private DetailsDataModel tableDetailsDataModel;
	private SubjectListModel subjectListModel;
	private UczenService uczenService;
	private AbsenceTableModel absenceModel;
	
	public DataPresenter(Ui_MainWindow view) {
		this.view = view;

		gradesTableModel = new GradesTableModel();
		tableDetailsDataModel = new DetailsDataModel();
		subjectListModel = new SubjectListModel();
		uczenService = (UczenService) SpringUtil.getBean("uczenService");
		
		gradesTableModel.setStudent(uczenService.findById(1));
		view.tableGrades.setModel(gradesTableModel);
		
		absenceModel = new AbsenceTableModel();
		absenceModel.setStudent(uczenService.findById(1));
		view.tableAbsence.setModel(absenceModel);
	}

	@Override
	public void connectSlots() {
		view.comboBoxStudent.currentIndexChanged.connect(this,
				DataSlots.CHANGE_USER_WITH_DATA);
	}

	/*public DataModel getTableDataModel() {
		return gradesTableModel;
	}*/

	public DetailsDataModel getTableDetailsDataModel() {
		return tableDetailsDataModel;
	}

	public void assignStudentsToSpinner() {
		view.comboBoxStudent.setModel(new StudentListModel());
	}

	@SuppressWarnings("unused")
	private void changeUserWithData() {
		view.tableGrades.setModel(null);
		gradesTableModel.setStudent((Uczen) view.comboBoxStudent.model().data(view.comboBoxStudent.model().index(view.comboBoxStudent.currentIndex(), 0), ItemDataRole.UserRole));
		view.tableGrades.reset();
		view.tableGrades.setModel(gradesTableModel);
		
		
		view.tableAbsence.setModel(null);
		absenceModel.setStudent((Uczen) view.comboBoxStudent.model().data(view.comboBoxStudent.model().index(view.comboBoxStudent.currentIndex(), 0), ItemDataRole.UserRole));
		view.tableAbsence.reset();
		view.tableAbsence.setModel(absenceModel);
	}
	
	@SuppressWarnings("unused")
	private void changedDataSubject(int i){
		//Zmiana przedmiotu
		System.out.println(i);
		
		System.out.println(subjectListModel.data(subjectListModel.index(i, 0), ItemDataRole.UserRole));
	}

}
