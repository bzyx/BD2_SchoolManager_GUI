package pl.polsl.bd2.presentation.data;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.UczenService;
import pl.polsl.bd2.models.AbsenceTableModel;
import pl.polsl.bd2.models.DetailsDataModel;
import pl.polsl.bd2.models.GradesTableModel;
import pl.polsl.bd2.models.JustificationTableModel;
import pl.polsl.bd2.models.StudentListModel;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.QHeaderView;

public class DataPresenter implements BasePresenter {

	private Ui_MainWindow view;
	private GradesTableModel gradesTableModel;
	private DetailsDataModel tableDetailsDataModel;
	private UczenService uczenService;
	private AbsenceTableModel absenceModel;
	private JustificationTableModel justificationModel;
	
	public DataPresenter(Ui_MainWindow view) {
		this.view = view;

		//FIXME: uczenService.findById(1) - tak nie może być
		gradesTableModel = new GradesTableModel();
		tableDetailsDataModel = new DetailsDataModel();
		uczenService = (UczenService) SpringUtil.getBean("uczenService");
		
		gradesTableModel.setStudent(uczenService.findById(1));
		view.tableGrades.setModel(gradesTableModel);
		
		absenceModel = new AbsenceTableModel();
		absenceModel.setStudent(uczenService.findById(1));
		view.tableAbsence.setModel(absenceModel);
		
		
		justificationModel = new JustificationTableModel();
		justificationModel.setStudent(uczenService.findById(1));
		view.tableJustification.setModel(justificationModel);
		view.tableJustification.verticalHeader().setResizeMode(QHeaderView.ResizeMode.ResizeToContents);
	}

	@Override
	public void connectSlots() {
		view.comboBoxStudent.currentIndexChanged.connect(this,
				DataSlots.CHANGE_USER_WITH_DATA);
	}

	public DetailsDataModel getTableDetailsDataModel() {
		return tableDetailsDataModel;
	}

	public void assignStudentsToSpinner() {
		view.comboBoxStudent.setModel(new StudentListModel());
	}

	@Override
	public void makeUpdateOfView() {
		view.tableGrades.setModel(null);
		gradesTableModel.setStudent((Uczen) view.comboBoxStudent.model().data(view.comboBoxStudent.model().index(view.comboBoxStudent.currentIndex(), 0), ItemDataRole.UserRole));
		view.tableGrades.reset();
		view.tableGrades.setModel(gradesTableModel);
		
		
		view.tableAbsence.setModel(null);
		absenceModel.setStudent((Uczen) view.comboBoxStudent.model().data(view.comboBoxStudent.model().index(view.comboBoxStudent.currentIndex(), 0), ItemDataRole.UserRole));
		view.tableAbsence.reset();
		view.tableAbsence.setModel(absenceModel);
		
		view.tableJustification.setModel(null);
		justificationModel.setStudent((Uczen) view.comboBoxStudent.model().data(view.comboBoxStudent.model().index(view.comboBoxStudent.currentIndex(), 0), ItemDataRole.UserRole));
		view.tableJustification.reset();
		view.tableJustification.setModel(justificationModel);
		view.tableJustification.verticalHeader().setResizeMode(QHeaderView.ResizeMode.ResizeToContents);
		
	}
	
}
