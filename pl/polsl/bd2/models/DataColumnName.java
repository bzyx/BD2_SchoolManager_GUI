package pl.polsl.bd2.models;

import java.util.HashMap;
import java.util.Map;

public enum DataColumnName {
	DataTable(1), DataDetailsTable(2);
	private final Map<Integer, String[]> columnMap = new HashMap<Integer, String[]>();
	{
		columnMap.put(1, new String[] {"Subject", "Avg.", "No 5",
				"No 4", "No 3", "No 2", "No 1"});//, "Absence", "Excused absence" });
		columnMap.put(2, new String[] { "Ajaja", "I ja.", "no ty",
				"itd", "No 3", "No 2", "No 1"});//, "Absence", "Excused absence" });
	}
	private int identifier;

	private DataColumnName(int i) {
		this.identifier = i;
	}

	public String[] returnColumnName() {
		return columnMap.get(this.identifier);
	}
}
