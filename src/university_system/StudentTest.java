package university_system;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams(){
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStream(){
		System.setOut(null);
	}
	
	@Test
	public void testStudentConstructor(){
		Student bill = new Student(42,"Billy","billy42","aoo");
		assertEquals(42,bill.getId());
		assertEquals("Billy",bill.getName());
		assertEquals("billy42",bill.getUsername());
		assertEquals("aoo",bill.getPassword());
	}
	
	@Test
	public void testCompletedCourse(){
		Student bill = new Student(42,"Billy","billy42","aoo");
		Course AOO = new Course("Advanced Obj. Or.","2", null, null, null, null, 42);
		bill.coursesCompleted.put(AOO, false);
		bill.completeCourse(AOO);
		assertEquals("Course has been completed\n", outContent.toString());
	}

}
