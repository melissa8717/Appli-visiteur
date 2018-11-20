package view.agenda;

import java.util.Calendar;
import java.util.GregorianCalendar;
 
public class Date {
	int year,month,day;
 
	private Calendar calendar=new GregorianCalendar();
 
	Date(){
	    year=calendar.get(Calendar.YEAR);
	    month=calendar.get(Calendar.MONTH);
	    day=calendar.get(Calendar.DAY_OF_MONTH);
	}
 
	Date(int year,int month,int day){
		this.year=year;
		this.month=month;
		this.day=day;
	}
 
	public int firstDayOfTheMonth(){
 
		return new GregorianCalendar(year, month, 1).get(Calendar.DAY_OF_WEEK);
 
	}
 
	int getYear(){
		return year;
	}
	int getMonth(){
		return month;
	}
	int getDay(){
		return day;
	}
	void setYear(int year){
		this.year=year;
	}
	void setMonth(int month){
		this.month=month;
	}void setDay(int day){
		this.day=day;
	}
}