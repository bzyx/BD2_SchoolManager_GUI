package pl.polsl.bd2.presentation.absence;

import com.trolltech.qt.core.QModelIndex;

import pl.polsl.bd2.gui.JustificationForm;
import pl.polsl.bd2.gui.MainWindow;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.models.AbsenceModel;
import pl.polsl.bd2.models.JustificationModel;
import pl.polsl.bd2.presentation.BasePresenter;


public class AbsencePresenter implements BasePresenter {
	private Ui_MainWindow view;
	private final AbsenceModel absenceModel;
	public AbsencePresenter(MainWindow window, Ui_MainWindow view) {
		this.view = view;
		this.window = window;

		absenceModel = new AbsenceModel();
		justificationModel = new JustificationModel();
	}Model = new JustificationModel();
	}

	@Override
	@Override
	public void connectSlots() {
		view.tableAbsences.doubleClicked.connect(this, AbsenceSlots.ADD_JUSTIFICATION);
	public void initModel() {
		view.tableAbsences.setModel(absenceModel);
		view.listJustifications.setModel(justificationModel);
	}AbsenceModel getAbsenceModel() {
		return absenceModel;
	}sed")
	private void addJustification() {
		if (absenceModel.getActuallAbsenceMock(getCurrentIndex())
				.getHowMuchAbsence() != 0) {
			JustificationForm justification = new JustificationForm(window,
					absenceModel.getActuallAbsenceMock(getCurrentIndex()),
					justificationModel);
			justification.exec();
			view.listJustifications.reset();
		}
	}lIndex getCurrentIndex() {
	private QModelIndex getCurrentIndex() {
		return view.tableAbsences.currentIndex();
	}