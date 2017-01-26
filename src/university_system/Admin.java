package university_system;

import java.util.*;
import java.sql.*;

/** The administrator class which represents users with administrative access
 * @author Darren Solorzano
 * @author Reynaldo Martinez
 * @author Chris Santos
 * @version 1.6
 * */
public class Admin extends User {
	static Scanner scn = new Scanner(System.in);
	
	/**Admin Singleton constructor
	 * */

	/*public Admin(int id, String name, String u, String p) {
		super(000001, "Senior Admin", "admin", "admin");
	}*/
	private void Admin(){}	// Singleton constructor for Admin
	
	public Admin(int id, String name, String u, String p) {
		super(id, name, u, p);
	}
	public static Admin getInstance(){
		try {
			Senior.createDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Senior;
	}
	
	
	/** Checks if the provided credentials for the Username is correct.
	 * @returns true if Username is correct, false otherwise
	 * */
	public boolean accessUsername(String info) throws Exception { //Idea? Move this up to User and create abstract login methods
		String found = "";
		
		Statement stmt = accessDatabase().createStatement();
		String query = "select username from student where username = '"+info+"';";
		ResultSet rs = stmt.executeQuery(query);

		if (rs.next()){
			found = rs.getString(1);
		}
		
		if (found.equals(info)){
			return true;
		} else {
			return false;
		}
	}
	/** Checks if the provided credentials for the Password is correct.
	 * @returns true if Password is correct, false otherwise
	 * */
	public boolean accessPassword(String info) throws Exception {
		String found = "";
		
		Statement stmt = accessDatabase().createStatement();
		String query = "select password from student where password = '"+info+"';";
		ResultSet rs = stmt.executeQuery(query);

		if (rs.next()){ // Could break in future
			found = rs.getString(1);
		}
		
		if (found.equals(info)){
			return true;
		} else {
			return false;
		}
	}

	/**Create Course method - creates a new Course object instance and stores the information into the database
	 * @return returns a Course Object file that has been created
	 * @param s Pass student that will have course added to his/her list of courses
	 * */
	public Object createCourse(Student s) throws Exception{
		Statement stmt = accessDatabase().createStatement();
		Course course = null;
		int CRN = 0;
		String cNum = null, courseName, sDate, credits, sTime, days ="";
		
		System.out.print("Enter the name of the course: "); // Replace with GUI implementation
		courseName = scn.nextLine();
		System.out.print("Enter the course ID: ");
		CRN = scn.nextInt();
		System.out.print("Enter the start date(yyyymmdd): ");
		sDate = scn.nextLine();
		System.out.print("Enter the end date(yyyymmdd): ");
		credits = scn.nextLine();
		System.out.print("Enter the start time: ");
		sTime = scn.nextLine();
		System.out.print("Enter what days the class will meet: ");
		days = scn.nextLine();
		
		stmt.execute("use university;");
		stmt.execute("insert into courses values ('"+cNum+"', "+courseName+", "+credits+
				", "+days+", '"+sDate+"', '"+sTime+"', '"+CRN+"');");
		course = new Course(cNum, courseName, credits, days, sDate, sTime, CRN); //Set course value
		s.coursesCompleted.put(course, false); //Add course to list of uncompleted course;
		return course;
	}
	/**Remove Course method - will search for a course with the name to be deleted and remove it
	 * @returns true or false if the course has been removed from the database.
	 * */
	public boolean removeCourse(Course c, Student s) throws Exception{
		Statement stmt = accessDatabase().createStatement();
		boolean test = false;
		
		try {
			try {
				if(s.coursesCompleted.containsKey(c)){
					s.coursesCompleted.remove(c);
					System.out.println("Course has been completed");
				}
			} catch (Exception e){
			}
			stmt.execute("use university;");
			stmt.execute("delete from courses where Title='"+c.courseName+"';");
			test = true;
		} catch (Exception e){
			return test;
		}
		return test;
	}
	
	/**Create account method - creates a new Account Object instance and stores the information into the database
	 * @return Returns a reference to an Object file of either:Student, Faculty or Null type
	 * @see accessDatabase()
	 * */
	public Object createAccount() throws Exception{	//We could maybe separate this into two methods(createStudent(), createFaculty())
		Statement stmt = accessDatabase().createStatement();
		Student nwStudent = null;
		//Faculty nwFaculty = null;
		String ans, id, fName, lName = "";
		
		System.out.print("Are you a Student or Faculty? ");
		ans = scn.nextLine();
		System.out.print("UTEP ID: ");
		id = scn.nextLine();
		System.out.print("First Name: ");
		fName = scn.nextLine();
		if (ans.toLowerCase().equals("student")){
			System.out.print("Last Name: ");
			lName = scn.nextLine();
			stmt.execute("use university");
			stmt.execute("insert into student values ("+id+", '"+fName+"', '"+lName+"');");
			nwStudent = new Student(Integer.valueOf(id), fName+" "+lName, "", "");
			return nwStudent;
		}
		if (ans.toLowerCase().equals("faculty")){
			System.out.print("Last Name: ");
			lName = scn.nextLine();
			stmt.execute("use university");
			stmt.execute("insert into faculty values ("+id+", '"+fName+"', '"+lName+"');");
			//nwFaculty = new Faculty(Integer.valueOf(id), fName+" "+lName, "", "");
			//return nwFaculty;
			return null;
		}
		return null; 
	}
	
	/** Method to create connection to the MySQL database
	 * @returns returns the Connection that was created.
	 * */
	public Connection accessDatabase() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306","root","3..1415926535Rey");
				//"jdbc:mysql://172.19.154.137:3306","username","password");
		Statement stmt = con.createStatement();
		stmt.execute("use university");
		return con;
	}
	
	/** Method that will create the initial database with basic tables and pre-set values
	 * @see accessDatabase
	 * */
	public boolean createDatabase() throws Exception{
		boolean test = true;
		try {
			Statement stmt = accessDatabase().createStatement();
			stmt.execute("drop database university;"); /**REMOVE THIS LINE. THIS LINE IS FOR TESTING ONLY!!!*/
			stmt.execute("create database university;");
			stmt.execute("use university;");
			stmt.execute("create table course(courseNumber int not null primary key, courseName VARCHAR(40), credits VARCHAR(10), "
					+ "days VARCHAR(10), date VARCHAR(10), time varchar(15), CRN int not null);");
			stmt.execute("create table student(id int not null primary key, firstName VARCHAR(20), lastName VARCHAR(20), "
					+ "username VARCHAR(20), password VARCHAR(20));");
			stmt.execute("create table faculty(id int not null primary key, firstName VARCHAR(20), lastName VARCHAR(20),"
					+ "username VARCHAR(20), password VARCHAR(20));");
			
			// Dummy course values
			stmt.execute("insert into course values(5362, 'CS 2301', '3', 'MWF', '10/4', '12pm', '201312');");
			stmt.execute("insert into course values(4262, 'Math 301', '1', 'TR', '11/11', '12pm','2060312');");
			stmt.execute("insert into course values(4342, 'Science 231', '5', 'TR', '11/11', '11am','201612');");
			stmt.execute("insert into course values(9362, 'History 401', '3', 'F', '12/1', '1pm','201612');");
			stmt.execute("insert into course values(4362, 'Knitting 230', '3', 'SS', '12/25', '3am','200312')");

			// Dummy student values
			stmt.execute("insert into student values (808080, 'Darren', 'Solor','dsolor', 'secret');");// Test statements
			stmt.execute("insert into student values (808081, 'Daniel', 'Quinones','dquino', 'password');");// Test statements
			stmt.execute("insert into student values (808082, 'John', 'Smith', 'jsmith', 'password2');");// Test statements
			stmt.execute("insert into student values (808083, 'Mac', 'book', 'mbook', 'garbagePass');");// Test statements
			
			// Dummy faculty values
			stmt.execute("insert into faculty values (808084, 'Derek', 'Solor','dsolor', 'secret');");// Test statements
			stmt.execute("insert into faculty values (808085, 'John', 'Quinones','dquino', 'password');");// Test statements
			stmt.execute("insert into faculty values (808086, 'Steve', 'Smith', 'jsmith', 'password2');");// Test statements
			stmt.execute("insert into faculty values (808087, 'Gibson', 'book', 'mbook', 'garbagePass');");// Test statements
		} catch (Exception e){
			test = false;
		}
		return test;
	}
}