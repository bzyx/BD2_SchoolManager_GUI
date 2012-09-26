package pl.polsl.bd2.presentation.managment;

import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.presentation.BasePresenter;

public class ManagmentPresenter implements BasePresenter {
	private Ui_MainWindow view;

	public ManagmentPresenter(Ui_MainWindow view) {
		this.view = view;
	}

	@Override
	public void connectSlots() {
	}
}
