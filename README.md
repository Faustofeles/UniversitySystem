# Advanced Object Oriented Programming Course CS 3331

The University Registration System Interface can navigate through several functionalities. It opens a Login interface where a user can attempt to access the system. In this demos, all functionalities are available and no restrictions appear to anyone that tries it. Additionally, the interfaces provide a smooth transition from one functionality to the next. Bare in mind that this demo shows the front end capabilities of our system and many of the fields displayed within JTextFields, JLists, JTables, and others, just serve as an outline as to what is to be developed while we wait for our back end oriented team to finish.

# How to use

The Universtity of Texas at El Paso - Registration System

Start at the Login Page. You can type a username and a password in the fields available and press "Log in", instead you can press the "Sign Up" button to have a window pop up. This Window will prompt you for your information and create a user profile for you (not explicitly implemented).

![Log in page](https://github.com/Faustofeles/UniversitySystem/blob/master/images/log_in.jpg?raw=true "Log in frame")

After passing the Login page, you will appear in the Home Page interface. From here you can navigate the entire application. You have four options: Registration, Check Records, Management, and Search Students. We'll go one by one:

![Home page](https://github.com/Faustofeles/UniversitySystem/blob/master/images/home_page.jpg?raw=true "Home page window")
  
Registration:

    Click on "Registration". You will appear in the interface that allows 
    the user to add and drop a class. You have two lists, Courses and 
    Selected, respectively. You can select 1 Course from the Courses list 
    and transfer it to the Selected list by clicking the "Select" button.
    The intention is to later be able to modify the courses shown in the
    Courses list by selecting different items from the JComboBox displayed
    just above it, in "Department". Alternatively, the JComboBox on top of 
    the Selected list "Academic Year", will have the capability to arrange 
    the displayed courses by the year that they where offered.

![Registration page](https://github.com/Faustofeles/UniversitySystem/blob/master/images/registration.jpg?raw=true "Registration page window")
 
    Once a course is passed over to the Selected list, the user can go back
    to search for more courses to add, or he/she may choose to add to their
    schedule a course already selected, by clicking the "Add to Schedule"
    button. They also have the option to remove the selected course from the
    list if they so change their mind, "Remove from list". The intention of
    the Selected list is to give the user the necessary freedom to keep on
    browsing without loosing track of their interest and without being
    pressured into choosing right away. 

    One last capability exists in the fact that a small pop-up window
    appears if you double click on an element of the list, this pop-up 
    window will display a short description of the course.
    
Check Records:

    You can go back to Home Page by clicking in the Home button (upper-right).
    The "Check Status" button will take you to the records interface. Here the
    user will find a tabbed pane with three tabs in total. The first tab gives
    the user the ability to go through a degree evaluation by choosing a major,
    a minor, and clicking the "Evaluate" button (not explicitly implemented).
    Once the evaluation is complete, the result will appears in the JTable 
    below. I will tell the user the classes required for the degree and whether
    they have passed or not the class.
  
![Records part 1](https://github.com/Faustofeles/UniversitySystem/blob/master/images/records_1.jpg?raw=true "Records part 1 windows")

    The second tab displays a nonfunctional calendar that, in its current form,
    is called by clicking the "Press for Calendar" button. The third and last
    tab displays a student's transcripts. This can either be a user that is a
    student, which has inherit access, or an administrator looking at the 
    account. You can take notice that the fields appear as question marks as 
    we wait for the back-end team's results.

![Records part 2](https://github.com/Faustofeles/UniversitySystem/blob/master/images/records_2.jpg?raw=true "Records part 2 window")

Management:

    Click on "Management" in the Home Page. The displayed interface is meant
    for authorized personnel only: an administrator. Here the Administrator 
    will be able to browse courses in the database using different criteria
    by taking advantage of the "Search Courses By" and "Sort By" sections. 
    Here the user can also double click the courses on the list to have a 
    small display describing a course. 
    
![Management page](https://github.com/Faustofeles/UniversitySystem/blob/master/images/management.jpg?raw=true "Management page window")

    The administrator can then choose to add, edit, or delete a course from the
    database. By clicking any of these buttons, a pop-up window appears that
    either prompts the administrator to edit the selected course, add the fields
    of a new course, or make sure he wants to delete an existing course.
    
Search Students:

    Click on "Search Students" in the Home Page. Lastly, this interface allows
    authorized personnel to search for students using different criteria, like
    seen in the sections "By Personal Information", "By Group". The idea behind
    this interface is to help the university find specific students that hold
    certain characteristics. The capability may be used for sending graduation
    notices, scholarship and job opportunities, security alerts, facilitate 
    advising, and provide information about events to students that may be 
    interested.   

![Student search page](https://github.com/Faustofeles/UniversitySystem/blob/master/images/student_search.jpg?raw=true "Student search page window")

    Here also, the authorized user can double-click on the student list, select
    a student and go to the "Check Records" interface. The intention with this
    is that the authorized user will have access to evaluate the student 
    against his degree plan and provide feedback by looking at his school 
    calendar and academic transcript. This will most likely be the job of an 
    advisor.
    
# Things to Consider

The application, in this version, has a plain "Back" button with no functionality right next to the "Home" button. The vision behind it is to create stack that keeps count of the individual interfaces that a user travels through. Then it just pops the top frame when the "Back" button is pressed. We are discussing the probability of getting rid of it, since the application, due to its size, is self-sustained with only a "Home" button.

Other difficulties arise from preventing unauthorized users to access interfaces restricted to only a selected group of individuals. Our idea is to pass a User object through the constructor of the Home Page, and depending on its type, have the constructor draw only the components the specific User type is allowed to access. The main idea is to take advantage of the setVisible(boolean b) method when deciding to add JComponents to the main frame. We are still looking into the most common practice/convention to achieve this task.
