import dao.CourseDAO;
import model.Course;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class CourseQueryTests {
    static ApplicationContext context;
    static CourseDAO courseDAO;

    @BeforeClass
    public static void setUp(){
        context = new ClassPathXmlApplicationContext("beans.xml");
        courseDAO = (CourseDAO)context.getBean("courseJDBCTemplate");
        courseDAO.createDefaultDB();
    }

    /*@Test
    public void testGetAllCourses(){

        List<Course> courses = courseDAO.getAllCourses();

        assertEquals("List courses test failed!","Software Design",courses.get(0).getName());
    }*/

    @Test
    public void testAddCourse(){
        List<Course> courses = courseDAO.getAllCourses();
        int sizeBefore = courses.size();

        int id = courseDAO.getCourseId();
        String name = "nume de test";
        String teacher = "teacher de test";
        int studyYear = 142;

        courseDAO.addCourse(new Course(id,name,teacher,studyYear));

        courses = courseDAO.getAllCourses();
        int sizeAfter = courses.size();

        assertEquals("Add course test failed!",sizeBefore+1,sizeAfter);
    }

    @Test
    //@Rollback
    public void testDeleteCourse(){

        List<Course> courses = courseDAO.getAllCourses();

        int sizeBefore = courses.size();

        int id = courses.get(courses.size()-1).getId();

        courseDAO.deleteCourse(id);

        courses = courseDAO.getAllCourses();

        int sizeAfter = courses.size();
        assertEquals("Delete course test failed",sizeBefore-1,sizeAfter);

    }

    @Test
    public void testUpdateCourse(){
        List<Course> courses = courseDAO.getAllCourses();
        int id = courses.get(0).getId();
        String name = "name update test";
        String teacher = "teacher update test";
        int studyYear = 112;

        Course course = new Course(id,name,teacher,studyYear);

        courseDAO.updateCourse(course);

        courses = courseDAO.getAllCourses();

        assertEquals("Update course failed!",course,courses.get(0));
    }

}
