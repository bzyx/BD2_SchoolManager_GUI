package pl.polsl.bd2.presentation.managment;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.QSignalMapper;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QSortFilterProxyModel;

import pl.polsl.bd2.gui.ComboBoxDialog;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Oddzial;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.Uczen;
import pl.polsl.bd2.messageSystem.service.OddzialService;
import pl.polsl.bd2.messageSystem.service.OsobaService;
import pl.polsl.bd2.messageSystem.service.RoleService;
import pl.polsl.bd2.messageSystem.service.UczenService;
import pl.polsl.bd2.models.OddzialListModel;
import pl.polsl.bd2.models.PupilModelForClassMenagment;
import pl.polsl.bd2.presentation.BasePresenter;


public class ManagmentPresenter implements BasePresenter {
	private Ui_MainWindow view;
	private PupilModelForClassMenagment pupilModelForClassMenagment;
	ComboBoxDialog comboBoxDlg;
	OddzialListModel oddzialListModel;

	public ManagmentPresenter(Ui_MainWindow view) {
		this.view = view;
		
		pupilModelForClassMenagment = new PupilModelForClassMenagment();
		QSortFilterProxyModel pupilModelForClassMenagmentSortable = new QSortFilterProxyModel();
		pupilModelForClassMenagmentSortable
				.setSourceModel(this.pupilModelForClassMenagment);

		view.tableViewPupils.setModel(pupilModelForClassMenagmentSortable);
		view.tableViewPupils.setSortingEnabled(true);
		view.tableViewPupils.resizeColumnsToContents();
		view.tableViewPupils.horizontalHeader().setStretchLastSection(true);
		view.tableViewPupils.verticalHeader().hide();
		

		this.reloadComboBoxClassAll();
		
		view.groupBoxAddClass.setVisible(false);
		view.groupBoxAddPupil.setVisible(false);
		oddzialListModel = new OddzialListModel();
	
	}

	@Override
	public void connectSlots() {
		
		view.buttonBoxAddClass.accepted.connect(this, "addClass()");
		view.buttonBoxAddClass.rejected.connect(this, "clearLineNewClass()");

		view.buttonBoxAddPupil.accepted.connect(this, "addPupil()");
		view.buttonBoxAddPupil.rejected.connect(this, "clearFieldsPupil()");

		view.comboBoxClassAll.currentIndexChanged.connect(this,
				"changeClassAllPupilTable()");
		view.pushButtonDeletePupil.clicked.connect(this, "deletePupilClass()");
		view.pushButtonMovePupil.clicked.connect(this, "movePupil()");

		QSignalMapper hideGroupBoxMapper = new QSignalMapper();
		hideGroupBoxMapper.setMapping(view.pushButtonAddClass, 1);
		hideGroupBoxMapper.setMapping(view.pushButtonAddPupil, 2);
		view.pushButtonAddClass.clicked.connect(
				hideGroupBoxMapper, "map()");
		view.pushButtonAddPupil.clicked.connect(hideGroupBoxMapper, "map()");
		hideGroupBoxMapper.mappedInteger.connect(this,
				"hideGroupBox(int)");
	}
	
	
	@SuppressWarnings("unused")
	private void deletePupilClass() {
		QModelIndex currentIndex = view.tableViewPupils.currentIndex();
		if (Helpers.indexIsValid(currentIndex)) {
			view.tableViewPupils.model().removeRows(currentIndex.row(), 1);
		} else {
			QMessageBox.warning(null, "Błąd", "Najpierw zaznacz ucznia do usunięcia.");
		}
	}
	
	
	@SuppressWarnings("unused")
	private void changeClassAllPupilTable() {
		this.pupilModelForClassMenagment.reClass(view.comboBoxClassAll
				.currentIndex());
		view.tableViewPupils.reset();
	}
	
	@SuppressWarnings("unused")
	private void hideGroupBox(int i){
		if(i == 1){
			view.groupBoxAddClass.setVisible(!view.groupBoxAddClass.isVisible());
		}
		else
		{
			view.groupBoxAddPupil.setVisible(!view.groupBoxAddPupil.isVisible());
		}
	}
	
	@SuppressWarnings("unused")
	private void addClass() {
		if (!view.lineEditNewClassName.text().isEmpty()) {
			OddzialService oddzialService = (OddzialService) SpringUtil
					.getBean("oddzialService");
			oddzialService.save(new Oddzial(view.lineEditNewClassName.text()));
			// this.reloadComboBoxClassAll();
			view.comboBoxClassAll.addItem(view.lineEditNewClassName.text());
			view.comboBoxClass.addItem(view.lineEditNewClassName.text());
			makeUpdateOfView();
		}
	}

	private void reloadComboBoxClassAll() {
		view.comboBoxClassAll.clear();
		OddzialService oddzialService = (OddzialService) SpringUtil
				.getBean("oddzialService");
		for (Oddzial oddzial : oddzialService.findAll()) {
			view.comboBoxClassAll.addItem(oddzial.getNazwa());
			view.comboBoxClass.addItem(oddzial.getNazwa());
		}

	}

	@SuppressWarnings("unused")
	private void clearLineNewClass() {
		// TODO: Cos w stylu chowania mozna zrobic
		view.lineEditNewClassName.clear();
	}

	@SuppressWarnings("unused")
	private void addPupil() {
		if (!view.lineEditNewPupilName.text().isEmpty()) {
			RoleService roleService = (RoleService) SpringUtil
					.getBean("roleService");
			OddzialService oddzialService = (OddzialService) SpringUtil
					.getBean("oddzialService");
			UczenService uczenService = (UczenService) SpringUtil
					.getBean("uczenService");
			OsobaService osobaService = (OsobaService) SpringUtil
					.getBean("osobaService");
			System.err.println(osobaService.findAll().size());
			osobaService.save(new Osoba(view.lineEditNewPupilName.text(),
					view.lineEditNewPupilVorname.text(),
					view.lineEditNewPupilStreet.text(), view.lineEditNewPupilCity
							.text(), view.lineEditNewPupilMail.text(),
					view.lineEditNewPupilLogin.text(),
					view.lineEditNewPupilPassword.text(), roleService.findByName(
							"Uczen").get(0)));
			System.err.println(oddzialService.findAll()
					.get(view.comboBoxClassAll.currentIndex()).getNazwa());


			uczenService.save(new Uczen(osobaService.findLast(), oddzialService
					.findAll().get(view.comboBoxClassAll.currentIndex())));
			makeUpdateOfView();
		}
	}

	private void clearFieldsPupil() {
		view.lineEditNewPupilName.clear();
		view.lineEditNewPupilVorname.clear();
		view.lineEditNewPupilStreet.clear();
		view.lineEditNewPupilCity.clear();
		view.lineEditNewPupilMail.clear();
		view.lineEditNewPupilLogin.clear();
		view.lineEditNewPupilPassword.clear();
	}
	
	public void movePupil(){
		
		QModelIndex currentIndex = view.tableViewPupils.currentIndex();
		if (Helpers.indexIsValid(currentIndex)) {
			if (comboBoxDlg == null)
				comboBoxDlg = new ComboBoxDialog();
			
			comboBoxDlg.setModel(oddzialListModel);
			comboBoxDlg.setWindowTitle("Przenosiny do klasy.");
			
				if (comboBoxDlg.exec() == QDialog.DialogCode.Accepted.value()){
					Uczen u = (Uczen) view.tableViewPupils.model().data(currentIndex, ItemDataRole.UserRole);
					u.setOddzial((Oddzial) comboBoxDlg.getCurrentItem());
					
					view.tableViewPupils.model().setData(currentIndex, u);
					makeUpdateOfView();
				}
		} else {
			QMessageBox.warning(null, "Błąd", "Najpierw zaznacz ucznia do usunięcia.");
		}
		
		
	}

	@Override
	public void makeUpdateOfView() {
		view.lineEditNewClassName.clear();
		this.clearFieldsPupil();
		this.pupilModelForClassMenagment.reContainer();
		view.tableViewPupils.reset();
		
	}
}
