package pl.polsl.bd2.presentation.message;

import java.util.Date;

import pl.polsl.bd2.enums.MessageFields;
import pl.polsl.bd2.enums.MessageRoles;
import pl.polsl.bd2.gui.contactForm;
import pl.polsl.bd2.gui.forms.Ui_MainWindow;
import pl.polsl.bd2.helpers.Helpers;
import pl.polsl.bd2.helpers.SpringUtil;
import pl.polsl.bd2.messageSystem.models.Komunikat;
import pl.polsl.bd2.messageSystem.models.Osoba;
import pl.polsl.bd2.messageSystem.models.TrescKomunikatu;
import pl.polsl.bd2.messageSystem.service.KomunikatService;
import pl.polsl.bd2.messageSystem.service.KonfiguracjaService;
import pl.polsl.bd2.messageSystem.service.TrescKomunikatuService;
import pl.polsl.bd2.models.MessageModel;
import pl.polsl.bd2.presentation.BasePresenter;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QItemSelection;
import com.trolltech.qt.gui.QSortFilterProxyModel;

/**
 * Handles actions connected to Message Form
 * 
 * @author lizmajlowicz
 */
public class MessagePresenter implements BasePresenter {
	private static final String CHOOSE_PERSON = "wybierz osobę";

	private Ui_MainWindow view;

	private KomunikatService komunikatService;
	private TrescKomunikatuService trescKomunikatuService;
	private KonfiguracjaService konfiguracjaService;

	public MessagePresenter(Ui_MainWindow view) {
		this.view = view;

		komunikatService = (KomunikatService) SpringUtil.getContext().getBean(
				"komunikatService");
		trescKomunikatuService = (TrescKomunikatuService) SpringUtil
				.getContext().getBean("trescKomunikatuService");
		konfiguracjaService = (KonfiguracjaService) SpringUtil.getContext()
				.getBean("konfiguracjaService");
	}

	@Override
	public void connectSlots() {
		view.tableMessages.selectionModel().selectionChanged.connect(this,
				MessageSlots.MSG_CHANGED);
		view.buttonMarkAsRead.clicked.connect(this, MessageSlots.SET_READED);
		view.buttonDeleteMessage.clicked.connect(this, MessageSlots.DELETE_MSG);
		view.buttonReplayMessage.clicked.connect(this, MessageSlots.REPLAY_MSG);
		view.buttonNewMessage.clicked.connect(this, MessageSlots.CREATE_MSG);
	}

	public void initModel() {
		final MessageModel messageModel = new MessageModel();
		QSortFilterProxyModel messageModelSortable = new QSortFilterProxyModel();
		messageModelSortable.setSourceModel(messageModel);

		view.tableMessages.setModel(messageModelSortable);
	}

	@SuppressWarnings("unused")
	private void setAsRead() {
		final QModelIndex currentIndex = getCurrentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			final int row = getCurrentIndex().row();
			final QModelIndex tmpIndex = currentIndex.child(row,
					MessageFields.UNREAD.getNum());
			view.tableMessages.model().setData(tmpIndex, false);
		}
	}

	@SuppressWarnings("unused")
	private void delete() {
		final QModelIndex currentIndex = getCurrentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			view.tableMessages.model().removeRows(currentIndex.row(), 1);
		}
	}

	@SuppressWarnings("unused")
	private void createNewMessage() {
		final Osoba osoba = konfiguracjaService.getLoggedOsoba();
		if (osoba != null) {
			final String name = getPersonsName(osoba);
			final contactForm cF = new contactForm(name, CHOOSE_PERSON);
			if (cF.exec() == QDialog.DialogCode.Accepted.value())
				saveMessage(osoba, cF);
		}
	}

	@SuppressWarnings("unused")
	private void replay() {
		QModelIndex currentIndex = getCurrentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			String from, to, title;
			QModelIndex tmpIndex;

			tmpIndex = currentIndex.child(getCurrentIndex().row(),
					MessageFields.FROM.getNum());
			from = (String) getModel().data(tmpIndex);

			tmpIndex = currentIndex.child(getCurrentIndex().row(), 0);
			to = (String) getModel().data(tmpIndex, MessageRoles.TO.getNum());

			tmpIndex = currentIndex.child(getCurrentIndex().row(),
					MessageFields.TITLE.getNum());
			title = (String) getModel().data(tmpIndex);

			title = "Re: " + title;

			// Need to cross the values because this is a response to sender
			contactForm cF = new contactForm(to, from, title);
			final Osoba osoba = konfiguracjaService.getLoggedOsoba();
			if (cF.exec() == QDialog.DialogCode.Accepted.value())
				saveMessage(osoba, cF);
		}
	}

	private QModelIndex getCurrentIndex() {
		return view.tableMessages.currentIndex();
	}

	private QAbstractItemModel getModel() {
		return view.tableMessages.model();
	}

	private void saveMessage(final Osoba osoba, final contactForm cF) {
		
		final TrescKomunikatu trescKomunikatu = new TrescKomunikatu(
				cF.getTekst(), cF.getTemat());
		trescKomunikatuService.save(trescKomunikatu);

		final Komunikat komunikat = new Komunikat(osoba, cF.getOsobaDo(),
				trescKomunikatu, getCurrentDate(), null);
		komunikatService.save(komunikat);
	}

	private Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	private String getPersonsName(final Osoba osoba) {
		final StringBuilder nameBviewlder = new StringBuilder();
		nameBviewlder.append(osoba.getImie()).append(" ")
				.append(osoba.getNazwisko());
		return nameBviewlder.toString();
	}

	@SuppressWarnings("unused")
	private void messageChanged(QItemSelection is, QItemSelection was) {
		QModelIndex currentIndex = getCurrentIndex();

		if (Helpers.indexIsValid(currentIndex)) {
			view.buttonReplayMessage.setEnabled(true);
			view.buttonDeleteMessage.setEnabled(true);
			view.buttonMarkAsRead.setEnabled(true);

			QModelIndex tmpIndex;

			tmpIndex = currentIndex.child(currentIndex.row(),
					MessageFields.FROM.getNum());
			view.lineEditFromMessage
					.setText((String) getModel().data(tmpIndex));

			tmpIndex = currentIndex.child(currentIndex.row(),
					MessageFields.TITLE.getNum());
			view.lineEditTopicMessage.setText((String) getModel()
					.data(tmpIndex));

			tmpIndex = currentIndex.child(currentIndex.row(), 0);
			view.plainTextEditMessage.setPlainText((String) getModel().data(
					tmpIndex, MessageRoles.MESSAGETEXT.getNum()));
		} else {
			view.lineEditFromMessage.setText("");
			view.lineEditTopicMessage.setText("");
			view.plainTextEditMessage.setPlainText("");

			view.buttonReplayMessage.setEnabled(false);
			view.buttonDeleteMessage.setEnabled(false);
			view.buttonMarkAsRead.setEnabled(false);
		}
	}

}
