package university_system;

import java.util.*;

/** The student class which represents users with student limited access
 * @author Darren Solorzano
 * @author Reynaldo Martinez
 * @author Chris Santos
 * @version 1.4
 * */
public class Student extends User{
	public Map<Course, Boolean> coursesCompleted = new HashMap<>();
	LinkedList<Course> courses = new LinkedList<Course>();

	public Student(int id, String name, String user, String pass) {
		super(id, name, user, pass);
	}
	/** Method to change a course's completion status to true
	 * @param c The course in catalog that will be removed;
	 * */
	public void completeCourse(Course c){
		if(coursesCompleted.containsKey(c)){
			coursesCompleted.put(c, true); // Will this replace or create a new one?
			System.out.println("Course has been completed");
		}
	}
	/** Checks is student has completed all courses required to graduate
	 * @returns true if student is ready to graduate, false otherwise
	 * */
	public boolean checkGraduation(Student student){ // Maybe these references should be static?
		if(student.coursesCompleted.containsValue(false)){
				return false;
		}
		return true;
	}
}
