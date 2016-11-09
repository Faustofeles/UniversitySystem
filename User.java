/** An abstract class which represents types of users
 * @author Darren Solorzano
 * @author Reynaldo Martinez
 * @author Chris Santos
 * @version 1.3
 * */
public class User {
	
	private int id;
	private String name;
	private String username;
	private String password;

	public User(int id, String name, String user, String pass) {
		this.setId(id);
		this.setName(name);
		user = username;
		pass = password;
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
}