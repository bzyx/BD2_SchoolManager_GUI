package pl.polsl.bd2.helpers;

import com.trolltech.qt.core.QModelIndex;

/*
 * This class should have only static methods.
 * Which automates some frequently used operations. 
 */
public final class Helpers {
	
	/*
	 * Checks the QModelIndex
	 * returns true if it's valid and
	 * can be used, otherwise returns false
	 * 
	 * IMPORTANT: I'm not checking the column in model!!
	 */
	public static Boolean indexIsValid(QModelIndex index) {
		if (index != null){
			if (index.row() >= 0 && index.column()>= 0){
				if (index.row() < index.model().rowCount()){
					return true;
				}
			}
		}
		return false;
		
	}

}
