package pl.polsl.bd2.presentation.data;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.UczenService;
import pl.polsl.bd2.models.DataModel;
import pl.polsl.bd2.models.DetailsDataModel;
import pl.polsl.bd2.models.SubjectListModel;
import pl.polsl.bd2.models.UserData;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.core.QSignalMapper;

public class DataPresenter implements BasePresenter {

	private Ui_MainWindow view;
	private DataModel tableDataModel;
	private DetailsDataModel tableDetailsDataModel;
	private UserData userData;
	private UczenService uczenService;
	private SubjectListModel subjectListModel;

	public DataPresenter(Ui_MainWindow view) {
		this.view = view;

		uczenService = (UczenService) SpringUtil.getBean("uczenService");

		userData = new UserData();
		tableDataModel = new DataModel();
		tableDetailsDataModel = new DetailsDataModel();
		subjectListModel = new SubjectListModel();
	}

	@Override
	public void connectSlots() {
		QSignalMapper mapperToogleTableDetailsData = new QSignalMapper();
		mapperToogleTableDetailsData
				.setMapping(view.buttonToogleDetailsData, 1);
		mapperToogleTableDetailsData.setMapping(view.tableData, 2);
		
		view.comboBoxSubject.setModel(subjectListModel);

		view.buttonToogleDetailsData.clicked.connect(
				mapperToogleTableDetailsData, "map()");
		view.tableData.clicked.connect(mapperToogleTableDetailsData, "map()");
		mapperToogleTableDetailsData.mappedInteger.connect(this,
				DataSlots.TOGLE_DETAILS);
		view.tableData.selectionModel().currentRowChanged.connect(this,
				DataSlots.CHANGE_DATA_DETAILS);
		view.comboBoxStudent.currentIndexChanged.connect(this,
				DataSlots.CHANGE_USER_WITH_DATA);
		view.comboBoxSubject.currentIndexChanged.connect(this, DataSlots.CHANGE_SUBJECT);
	}

	public void initModel() {
		this.tableDataModel.setDataContainer(this.uczenService.findById(1),
				this.userData.getUserDataConteiner().get(0).getData());
		view.tableData.setModel(this.tableDataModel);
		tableDetailsDataModel.setDetailsDataContainer(tableDataModel
				.getDataContainer().get(0).getDetailsData());
		view.tableDetailsData.setModel(this.tableDetailsDataModel);
	}

	public DataModel getTableDataModel() {
		return tableDataModel;
	}

	public DetailsDataModel getTableDetailsDataModel() {
		return tableDetailsDataModel;
	}

	public void assignStudentsToSpinner() {
		for (Uczen uczen : uczenService.findAll()) {
			if (uczen != null)
				view.comboBoxStudent.addItem(uczen.getOsoba().getImie());
		}
	}

	@SuppressWarnings("unused")
	private void toogleTableDetailsData(int i) {
		if (i == 1) {
			view.tableDetailsData
					.setVisible(!view.tableDetailsData.isVisible());
		} else if (!view.tableDetailsData.isVisible()) {
			view.tableDetailsData.setVisible(true);
		}
	}

	@SuppressWarnings("unused")
	private void changeDataDetails() {
		tableDetailsDataModel.setDetailsDataContainer(tableDataModel
				.getDataContainer().get(view.tableData.currentIndex().row())
				.getDetailsData());
		view.labelProgramInData.setText(view.tableData.model()
				.index(view.tableData.currentIndex().row(), 0).data()
				.toString());
		view.tableDetailsData.reset();

	}

	@SuppressWarnings("unused")
	private void changeUserWithData() {
		// w bazie liczymy od 1 a tutaj od 0
		tableDataModel.setDataContainer(this.uczenService
				.findById(view.comboBoxStudent.currentIndex() + 1), userData
				.getUserDataConteiner().get(0).getData());
		view.tableData.reset();
		view.labelProgramInData.setText(tableDataModel.getDataContainer()
				.get(0).getSubject());
		view.tableDetailsData.setVisible(false);
		view.tableData.selectRow(0);
	}
	
	@SuppressWarnings("unused")
	private void changedDataSubject(int i){
		//Zmiana przedmiotu
		System.out.println(i);
	}

}
