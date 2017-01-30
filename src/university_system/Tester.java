package university_system;


/** The test class is used to run simulations to ensure the system is working as intended
 * @author Darren Solorzano
 * @author Reynaldo Martinez
 * @author Chris Santos
 * @version 1.5
 * */
public class Tester{
	public static void main(String[] args) throws Exception {
		try {
			
			Admin temp = Admin.getInstance();
			
			temp.createDatabase();
			System.out.println("Hi fellas");
			//TODO To test your code stubs
			
		} catch (Exception e){
			System.out.println("Hi fellas");  // Will catch and print the exception you had
		}
	}
}
