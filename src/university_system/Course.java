package university_system;

import java.io.*;

import java.util.*;
/** The course class which represents types of courses and their relative fields
 * @author Darren Solorzano
 * @author Reynaldo Martinez
 * @author Chris Santos
 * @version 1.3
 * */
public class Course {
	String cNumber;
	String courseName;
	String credits;
	String days;
	String date;
	String time;
	int CRN;

	/** Course constructor
	 * @param cNumber holds Course number e.g. 3340
	 * @param courseName holds the name of the course
	 * @param credits holds the number of credits
	 * @param days holds the days the course is offered
	 * @param date holds the date which class is held
	 * @param time holds the time it starts
	 * @param cRN holds the course reference number(CRN)
	 * */
	public Course(String cNumber, String courseName, String credits, String days, String date, String time, int CRN) {
		super();
		this.cNumber = cNumber;
		this.courseName = courseName;
		this.credits = credits;
		this.days = days;
		this.date = date;
		this.time = time;
		this.CRN = CRN;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getcRN() {
		return CRN;
	}
	public void setcRN(int cRN) {
		this.CRN = cRN;
	}

	public String getcNumber() {
		return cNumber;
	}

	public String getCredits() {
		return credits;
	}
	
	public String toString(){
		return ""+this.getCourseName()+" CRN: "+this.getcRN();
	}
	
}
 