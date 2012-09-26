package pl.polsl.bd2.presentation.message;

interface MessageSlots {
	static final String CREATE_MSG = "createNewMessage()";
	static final String REPLAY_MSG = "replay()";
	static final String MSG_CHANGED = "messageChanged(QItemSelection, QItemSelection)";
	static final String SET_READED = "setAsRead()";
	static final String DELETE_MSG = "delete()";
}