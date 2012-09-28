package pl.polsl.bd2.gui;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.bd2.gui.forms.Ui_DictEditorWidget;
import pl.polsl.bd2.helpers.Helpers;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QAbstractListModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt.ItemDataRole;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.gui.QLineEdit.EchoMode;

public class DictEditorWidget extends QWidget {

	Ui_DictEditorWidget ui = new Ui_DictEditorWidget();

	public static void main(String[] args) {
		QApplication.initialize(args);

		DictEditorWidget testDictEditorWidget = new DictEditorWidget();

		TestModel tm = new TestModel();
		testDictEditorWidget.setModel(tm);
		testDictEditorWidget.show();

		QApplication.exec();
	}

	public void setModel(QAbstractListModel tm) {
		ui.listDictModel.setModel(tm);

	}

	public DictEditorWidget() {
		ui.setupUi(this);

		ui.buttonAdd.clicked.connect(this, "add()");
		ui.buttonEdit.clicked.connect(this, "edit()");
		ui.buttonRemove.clicked.connect(this, "remove()");
		ui.buttonClose.clicked.connect(this, "closeWindow()");
	}

	@SuppressWarnings("unused")
	private void add() {
		String nazwa = QInputDialog.getText(this, "Podaj nazwę typu konkurusu",
				"Nazwa:");

		if (nazwa != null) {
			ui.listDictModel.model().setData(
					ui.listDictModel.model().index(
							ui.listDictModel.model().rowCount() + 1, 0), nazwa);
			ui.listDictModel.model().dataChanged.emit(
					ui.listDictModel.currentIndex(),
					ui.listDictModel.currentIndex());
		}
	}

	@SuppressWarnings("unused")
	private void edit() {
		if (Helpers.indexIsValid(ui.listDictModel.currentIndex())) {
			String nazwa = QInputDialog.getText(this,
					"Podaj nazwę typu konkurusu", "Nazwa:", EchoMode.Normal,
					(String) ui.listDictModel.currentIndex().data());
			if (nazwa != null) {
				ui.listDictModel.model().setData(
						ui.listDictModel.currentIndex(), nazwa);
				ui.listDictModel.model().dataChanged.emit(
						ui.listDictModel.currentIndex(),
						ui.listDictModel.currentIndex());
			}
		}
	}

	@SuppressWarnings("unused")
	private void remove() {
		if (Helpers.indexIsValid(ui.listDictModel.currentIndex())) {
			ui.listDictModel.model().removeRow(
					ui.listDictModel.currentIndex().row());
			ui.listDictModel.model().dataChanged.emit(
					ui.listDictModel.currentIndex(),
					ui.listDictModel.currentIndex());
		}
	}
	
	@SuppressWarnings("unused")
	private void closeWindow(){
		hide();
	}
}

class TestModel extends QAbstractListModel {

	List<String> container = new ArrayList<String>();

	public TestModel() {
		container.add("Ala");
		container.add("Ma");
		container.add("kota");
	}

	@Override
	@QtBlockedSlot
	public Object data(QModelIndex arg0, int arg1) {
		if (arg1 == ItemDataRole.DisplayRole)
			return container.get(arg0.row());
		return null;
	}

	@Override
	@QtBlockedSlot
	public int rowCount(QModelIndex arg0) {
		return container.size();
	}

	@Override
	@QtBlockedSlot
	public boolean setData(QModelIndex arg0, Object arg1, int arg2) {
		if (arg0 == null){
			container.add((String) arg1);
			return super.setData(arg0, arg1, arg2);
		}
		
		if (arg0.row() > container.size())
			container.add((String) arg1);
		else {
			container.set(arg0.row(), (String) arg1);
		}
		return super.setData(arg0, arg1, arg2);
	}

	@Override
	@QtBlockedSlot
	public boolean removeRows(int arg0, int arg1, QModelIndex arg2) {
		container.remove(arg0);
		return super.removeRows(arg0, arg1, arg2);
	}

}
