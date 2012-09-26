package pl.polsl.bd2.enums;

import com.trolltech.qt.core.Qt;

public enum MessageRoles {
	TO(Qt.ItemDataRole.UserRole + 0), MESSAGETEXT(Qt.ItemDataRole.UserRole + 1);

	private MessageRoles(Integer num) {
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}

	private Integer num;
}
