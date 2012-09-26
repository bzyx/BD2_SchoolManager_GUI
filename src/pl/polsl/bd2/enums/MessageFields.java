package pl.polsl.bd2.enums;

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
