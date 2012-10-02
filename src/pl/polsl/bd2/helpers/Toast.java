package pl.polsl.bd2.helpers;

import com.trolltech.qt.gui.QMessageBox;

public class Toast {
	public static void show(final String title, final String msg) {
		QMessageBox.warning(null, title, msg);
	}
}
