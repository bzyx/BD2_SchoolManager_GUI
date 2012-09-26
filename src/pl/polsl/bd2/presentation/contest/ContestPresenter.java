package pl.polsl.bd2.presentation.contest;

import pl.polsl.bd2.gui.ContestTypeDialog;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.presentation.BasePresenter;

public class ContestPresenter implements BasePresenter {
	private interface ContestSlots {
		final static String SHOW_EDIT_DIALOG = "showEditDialog()";
	}

	private Ui_MainWindow view;
	private ContestTypeDialog contestTypeDialog;

	public ContestPresenter(Ui_MainWindow view) {
		this.view = view;
	}

	@Override
	public void connectSlots() {
		view.buttonEditContestType.clicked
				.connect(this, ContestSlots.SHOW_EDIT_DIALOG);
	}

	@SuppressWarnings("unused")
	private void showEditDialog() {
		if (contestTypeDialog == null) {
			contestTypeDialog = new ContestTypeDialog();
		}
		contestTypeDialog.show();
	}
}
