package university_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/** An abstract class which represents types of users
 * @author Darren Solorzano
 * @author Reynaldo Martinez
 * @author Chris Santos
 * @version 1.4
 * */
public class User {
	
	private int id;
	private String name;
	private String username;
	private String password;

	public User(int id, String name, String user, String pass) {
		this.setId(id);
		this.setName(name);
		this.username = user;
		this.password = pass;
	}

	/*Modifiers and Accessors for User class*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/** Method to create connection to the MySQL database
	 * @returns returns the Connection that was created.
	 * */
	protected Connection accessDatabase() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306","root","");
		return con;
	}
	/** Method that will create the initial database with basic tables and pre-set values
	 * @see accessDatabase
	 * */
	public void createDatabase() throws Exception{
		Statement stmt = accessDatabase().createStatement();
		stmt.execute("drop database university;"); /**REMOVE THIS LINE. THIS LINE IS FOR TESTING ONLY!!!*/ //TODO
		stmt.execute("create database university;");
		stmt.execute("use university;");
		stmt.execute("create table course(id int not null primary key, courseName VARCHAR(40), startTime VARCHAR(10), "
				+ "endTime VARCHAR(10), days VARCHAR(10), startDate DATE, endDate DATE);");
		stmt.execute("create table student(id int not null primary key, firstName VARCHAR(20), lastName VARCHAR(20), "
				+ "username VARCHAR(20), password VARCHAR(20));");
		stmt.execute("create table faculty(id int not null primary key, firstName VARCHAR(20), lastName VARCHAR(20),"
				+ "username VARCHAR(20), password VARCHAR(20));");
		
		// Dummy course values
		stmt.execute("insert into course values(805362, 'CS 2301', '3:00', '4:00', 'MTWF', '20160312', '20170914');");
		stmt.execute("insert into course values(804262, 'Math 301', '12:00', '2:00', 'MRF', '20160312', '20170914');");
		stmt.execute("insert into course values(804342, 'Science 231', '5:00', '10:00', 'TWRF', '20160312', '20170914');");
		stmt.execute("insert into course values(809362, 'History 401', '3:00', '4:00', 'RF', '20160312', '20170914');");
		stmt.execute("insert into course values(824362, 'Knitting 230', '3:00', '4:00', 'MTWF', '20160312', '20170914');");

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
	}
}