package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import database.SerializeDB;

/**
 * Accessed by Staff and the system
 * This class implemented to do action, add or delete public holidays from PublicHoliday.ser
 * @author Group5
 *
 */

public class PublicHoliday {
	private ArrayList<GregorianCalendar> publicHoliday;

	/* ******************** Default Constructor *************************/
	public PublicHoliday() {
	}

	/* ******************** Methods ****************************/
	/**
	 * This method add date as public holiday to PublicHoliday.ser
	 * @param date The date to add as holiday lists
	 * @return boolean True if the adding is successful, false otherwise
	 */
	public boolean addHoliday(GregorianCalendar date) {
		long dateMS = date.getTimeInMillis();
		long calendarMS;
		publicHoliday = (ArrayList<GregorianCalendar>) SerializeDB.readSerializedObject("PublicHoliday.ser");		
		for (GregorianCalendar calendar : publicHoliday) {
			calendarMS = calendar.getTimeInMillis();
			if (calendarMS == dateMS) {
				return false;
			}
		}
		publicHoliday.add(date);
		SerializeDB.writeSerializedObject("PublicHoliday.ser", publicHoliday);
		return true;
	}

	/**
	 * The method remove date from PublicHoliday.ser
	 * @param date The date to remove from holiday lists
	 * @return boolean True if the removing is successful, false otherwise
	 */
	public boolean deleteHoliday(GregorianCalendar date) {
		long dateMS = date.getTimeInMillis();
		long calendarMS;
		publicHoliday = (ArrayList<GregorianCalendar>) SerializeDB.readSerializedObject("PublicHoliday.ser");

		for (GregorianCalendar calendar : publicHoliday) {
			calendarMS = calendar.getTimeInMillis();
			if (calendarMS == dateMS) {
				publicHoliday.remove(calendar);
				SerializeDB.writeSerializedObject("PublicHoliday.ser", publicHoliday);
				return true;
			}
		}
		return false;
	}

	/**
	 * The method check if a given date is a holiday
	 * @param date The date to check whether holiday
	 * @return boolean True if the the given date is holiday, false otherwise
	 */
	public boolean isHoliday(GregorianCalendar date) {
		date.set(GregorianCalendar.HOUR_OF_DAY, 0);
		date.set(GregorianCalendar.MINUTE, 0);
		long dateMS = date.getTimeInMillis();
		long calendarMS;

		publicHoliday = (ArrayList<GregorianCalendar>) SerializeDB.readSerializedObject("PublicHoliday.ser");

		for (GregorianCalendar calendar : publicHoliday) {
			calendarMS = calendar.getTimeInMillis();
			if (calendarMS == dateMS) {
				return true;
			}
		}		
		return false;
	}

	/**
	 * The method to get all the public holidays in a list
	 * @return publicHoliday List of public holidays
	 */
	public ArrayList<GregorianCalendar> getPublicHoliday() {
		publicHoliday = (ArrayList<GregorianCalendar>) SerializeDB.readSerializedObject("PublicHoliday.ser");
		return publicHoliday;
	}
}
