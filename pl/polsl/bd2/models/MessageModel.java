package pl.polsl.bd2.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Komunikat;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.service.KomunikatService;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractTableModel;

/*
 * MessageModel Class
 * Author: Marcin Jabrzyk
 * 
 * TODO:
 * 4) Messages are shown for current user
 */

public class MessageModel extends QAbstractTableModel {
	
	public MessageModel() {
		komunikatService = (KomunikatService)SpringUtil.getBean("komunikatService");
		konfiguracjaService = (KonfiguracjaService)SpringUtil.getBean("konfiguracjaService");
		messageContainer = new ArrayList<Komunikat>(konfiguracjaService.getLoggedOsoba().getOsobaDo());
	}
	
	public enum MessageFields {
		UNREAD(0), FROM(1), TIMESTAMP(2), TITLE(3), TO(4), MESSAGETEXT(5);

		private MessageFields(Integer num) {
			this.num = num;
		}

		public Integer getNum() {
			return num;
		}

		private Integer num;
	}

	public enum MessageRoles {
		TO(Qt.ItemDataRole.UserRole + 0), MESSAGETEXT(
				Qt.ItemDataRole.UserRole + 1);

		private MessageRoles(Integer num) {
			this.num = num;
		}

		public Integer getNum() {
			return num;
		}

		private Integer num;
	}

	@Override
	public Object headerData(int section, Orientation orientation, int role) {
		if (role == Qt.ItemDataRole.DisplayRole) {
			if (orientation == Qt.Orientation.Horizontal) {
				if (section == MessageFields.FROM.getNum())
					return tr("From: ");
				if (section == MessageFields.TIMESTAMP.getNum())
					return tr("Date/Time: ");
				if (section == MessageFields.TITLE.getNum())
					return tr("Title: ");
				if (section == MessageFields.UNREAD.getNum())
					return tr("Unread: ");
			}
		}
		return null;
	}

	@Override
	public int columnCount(QModelIndex index) {
		return 4;
	}

	@Override
	public Object data(QModelIndex index, int role) {
		int row = index.row();
		int col = index.column();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (role == Qt.ItemDataRole.DisplayRole) {
			if (col == MessageFields.FROM.getNum())
				return messageContainer.get(row).getOsobaDo().getLogin();
			if (col == MessageFields.TITLE.getNum())
				return messageContainer.get(row).getTrescKomunikatu().getTytul();
			if (col == MessageFields.TIMESTAMP.getNum())
				return (messageContainer.get(row).getDataOd()!=null)?sdf.format(messageContainer.get(row).getDataOd()):"-";
			if (col == MessageFields.UNREAD.getNum())
				return messageContainer.get(row).getNieprzeczytany() ? tr("Yes")
						: tr("No");
		}

		if (role == Qt.ItemDataRole.ToolTipRole) {
			String msgText = messageContainer.get(row).getTrescKomunikatu().getTekst();
			return msgText.substring(0, (msgText.length() > 300) ? 300
					: msgText.length());
		}

		if (role == MessageRoles.TO.getNum())
			return messageContainer.get(row).getOsobaDo().getImie();
		if (role == MessageRoles.MESSAGETEXT.getNum())
			return messageContainer.get(row).getTrescKomunikatu().getTekst();

		return null;
	}

	@Override
	public int rowCount(QModelIndex index) {
		return messageContainer.size();
	}

	@Override
	public boolean setData(QModelIndex index, Object value, int role) {
		if (role == Qt.ItemDataRole.EditRole) {
			if (index.row() >= 0 && index.row() <= messageContainer.size()) {
				if (index.column() == MessageFields.UNREAD.getNum()) {
					messageContainer.get(index.row())
							.setNieprzeczytany((Boolean) value);
					komunikatService.edit(messageContainer.get(index.row()));
					this.dataChanged.emit(index, index);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeRows(int position, int rows, QModelIndex parent) {
		beginRemoveRows(parent, position, position + rows - 1);

		for (int row = 0; row < rows; row++) {
			komunikatService.delete(messageContainer.get(row));
			messageContainer.remove(position);
		}

		endRemoveRows();
		return true;
	}

	List<Komunikat> messageContainer;
	KomunikatService komunikatService;
	KonfiguracjaService konfiguracjaService;
}
