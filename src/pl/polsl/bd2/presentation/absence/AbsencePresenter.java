package pl.polsl.bd2.presentation.absence;

import pl.polsl.bd2.gui.MainWindow;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.models.AbsenceModel;
import pl.polsl.bd2.models._JustificationModel;
import pl.polsl.bd2.presentation.BasePresenter;

public class AbsencePresenter implements BasePresenter { 
    private Ui_MainWindow view; 
    private final AbsenceModel absenceModel; 
    private final _JustificationModel justificationModel; 
    private MainWindow window; 
    public AbsencePresenter(MainWindow window, Ui_MainWindow view) { 
        this.view = view; 
        this.window = window; 
        absenceModel = new AbsenceModel(); 
        justificationModel = new _JustificationModel(); 
    } 
    @Override 
    public void connectSlots() { 
        view.tableAbsences.doubleClicked.connect(this, AbsenceSlots.ADD_JUSTIFICATION); 
    } 
    
    public void initModel() { 
        view.tableAbsences.setModel(absenceModel); 
        view.listJustifications_3.setModel(justificationModel); 
    } 
    
    public AbsenceModel getAbsenceModel() { 
        return absenceModel; 
    } 

	@Override
	public void makeUpdateOfView() {
		view.tableAbsences.setModel(null);
		view.tableAbsences.reset();
		view.tableAbsences.setModel(absenceModel);
		
		view.listJustifications_3.setModel(null); 
		view.listJustifications_3.reset();
        view.listJustifications_3.setModel(justificationModel);
		
	} 
}