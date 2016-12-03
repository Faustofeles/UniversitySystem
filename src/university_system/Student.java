package university_system;
import java.util.*;


/** The student class which represents users with student limited access
 * @author Darren Solorzano
 * @author Reynaldo Martinez
 * @author Chris Santos
 * @version 1.3
 * */

public class Student extends User{
	Map<Course, Boolean> coursesCompleted = new HashMap<>();

	public Student(int id, String name, String user, String pass) {
		super(id, name, user, pass);
		// TODO Auto-generated constructor stub
	}
	/** Method to change a course's completion status to true
	 * @param c The course in catalog that will be removed;
	 * */
	public void completeCourse(Course c){
		if(coursesCompleted.containsKey(c)){
			coursesCompleted.put(c, true);
			System.out.print("Course has been completed\n");
		}
	}
}