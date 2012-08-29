package pl.polsl.bd2.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserData{
	static public List<UserDataMock> userDataConteiner;
	{
		userDataConteiner = new ArrayList<UserDataMock>();
		List<DataMock> data1 = new ArrayList<DataMock>();
		List<DataMock> data2 = new ArrayList<DataMock>();
		List<DetailsDataMock> details1 = new ArrayList<DetailsDataMock>();
		List<DetailsDataMock> details2 = new ArrayList<DetailsDataMock>();
		List<DetailsDataMock> details3 = new ArrayList<DetailsDataMock>();
		List<DetailsDataMock> details4 = new ArrayList<DetailsDataMock>();
		details1.add(new DetailsDataMock(new Date(), 3, "sprawdzian", "blazen"));
		details1.add(new DetailsDataMock(new Date(), 4, "kartkówka", "Bardzo B³azen"));
		details2.add(new DetailsDataMock(new Date(), 4, "kartkówka", "Bardzo B³azen"));
		details2.add(new DetailsDataMock(new Date(), 3, "sprawdzian", "blazen"));
		details3.add(new DetailsDataMock(new Date(), 6, "sprawdzian", "-"));
		details3.add(new DetailsDataMock(new Date(), 3, "sprawdzian", "blazen"));
		details4.add(new DetailsDataMock(new Date(), 4, "kartkówka", "-"));
		details4.add(new DetailsDataMock(new Date(), 6, "sprawdzian", "-"));
		data1.add(new DataMock("Polski", 3, "4, 5, 6, 2", 4, 3, details1));
		data1.add(new DataMock("Angielski", 3, "4, 3, 5, 7", 4, 3, details2));
		data2.add(new DataMock("Niemiecki", 3, "2, 3, 5, 3", 4, 3, details3));
		data2.add(new DataMock("Chiñski", 3, "6, 4, 2", 4, 3, details4));
		userDataConteiner.add(new UserDataMock("Adam", data1));
		userDataConteiner.add(new UserDataMock("Józek", data2));
	}
	
	public List<UserDataMock> getUserDataConteiner() {
		return userDataConteiner;
	}
	public UserData(){
		
	}
	public class DetailsDataMock {
		private Date date;
		private float rate;
		private String information;
		private String note;
		public DetailsDataMock(Date date, float rate, String information,
				String note) {
			super();
			this.date = date;
			this.rate = rate;
			this.information = information;
			this.note = note;
		}
		public Date getDate() {
			return date;
		}
		public float getRate() {
			return rate;
		}
		public String getInformation() {
			return information;
		}
		public String getNote() {
			return note;
		}
	}
	
	public class DataMock {
		private String subject;
		private double avg;
		private String rates;
		private int absence;
		private int excusedAbsence;
		private List<DetailsDataMock> detailsData;
		public DataMock(String subject, double avg, String rates, int absence,
				int excusedAbsence, List<DetailsDataMock> detailsData) {
			super();
			this.subject = subject;
			this.avg = avg;
			this.rates = rates;
			this.absence = absence;
			this.excusedAbsence = excusedAbsence;
			this.detailsData = detailsData;
		}
		public String getSubject() {
			return subject;
		}
		public double getAvg() {
			return avg;
		}
		public String getNote() {
			return rates;
		}
		public int getAbsence() {
			return absence;
		}
		public int getExcusedAbsence() {
			return excusedAbsence;
		}
		public List<DetailsDataMock> getDetailsData() {
			return detailsData;
		}	
	}
	
	public class UserDataMock {
		private String name;
		private List<DataMock> data;
		public UserDataMock(String name, List<DataMock> data) {
			super();
			this.name = name;
			this.data = data;
		}
		public String getName() {
			return name;
		}
		public List<DataMock> getData() {
			return data;
		}
	}
	

}
