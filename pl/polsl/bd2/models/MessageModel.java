package pl.polsl.bd2.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
				return messageContainer.get(row).getFrom();
			if (col == MessageFields.TITLE.getNum())
				return messageContainer.get(row).getTopic();
			if (col == MessageFields.TIMESTAMP.getNum())
				return sdf.format(messageContainer.get(row).getTimeStamp());
			if (col == MessageFields.UNREAD.getNum())
				return messageContainer.get(row).getUnread() ? tr("Yes")
						: tr("No");
		}

		if (role == Qt.ItemDataRole.ToolTipRole) {
			String msgText = messageContainer.get(row).getMsgText();
			return msgText.substring(0, (msgText.length() > 300) ? 300
					: msgText.length());
		}

		if (role == MessageRoles.TO.getNum())
			return messageContainer.get(row).getTo();
		if (role == MessageRoles.MESSAGETEXT.getNum())
			return messageContainer.get(row).getMsgText();

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
							.setUnread((Boolean) value);
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
			messageContainer.remove(position);
		}

		endRemoveRows();
		return true;
	}

	public class MessageMock {

		public MessageMock(String from, String to, String topic,
				String msgText, Date timeStamp, Boolean unread) {
			super();
			this.from = from;
			this.to = to;
			this.topic = topic;
			this.msgText = msgText;
			this.timeStamp = timeStamp;
			this.unread = unread;
		}

		public String getFrom() {
			return from;
		}

		public String getTo() {
			return to;
		}

		public String getTopic() {
			return topic;
		}

		public String getMsgText() {
			return msgText;
		}

		public Date getTimeStamp() {
			return timeStamp;
		}

		public Boolean getUnread() {
			return unread;
		}

		public void setUnread(Boolean unread) {
			this.unread = unread;
		}

		String from;
		String to;
		String topic;
		String msgText;
		Date timeStamp;
		Boolean unread;

	}

	List<MessageMock> messageContainer;

	{
		messageContainer = new ArrayList<MessageMock>();
		messageContainer.add(new MessageMock("Ala", "Ela", "Pozdrowienia",
				"Z okazji urodzin ¿yczê Ci du¿o szczêscia", new Date(), true));
		messageContainer.add(new MessageMock("Ela", "Ala", "Re: Pozdrowienia",
				"Dziêkujê za wiadomosæ", new Date(), true));
		messageContainer.add(new MessageMock("Zosia", "Frania", "Oceny Jasia",
				"Jasiu chyba sci¹ga³, dam mu pa³ê!", new Date(), false));
		messageContainer.add(new MessageMock("Frania", "Jaœ",
				"Ostatni sprawdzian",
				"Jasiu idŸ do Pani Zosi i wyt³umacz jej, ¿e nie sci¹ga³eœ.",
				new Date(), true));

	}

}
