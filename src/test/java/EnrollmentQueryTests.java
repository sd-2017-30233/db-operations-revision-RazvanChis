import dao.EnrollmentDAO;
import model.Enrollment;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class EnrollmentQueryTests {

    static ApplicationContext context;
    static EnrollmentDAO enrollmentDAO;

    @BeforeClass
    public static void setUp(){
        context = new ClassPathXmlApplicationContext("beans.xml");
        enrollmentDAO = (EnrollmentDAO)context.getBean("enrollmentJDBCTemplate");
        enrollmentDAO.createDefaultDB();
    }

    @Test
    public void testEnrollStudent(){
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentIds();
        int sizeBefore = enrollments.size();

        enrollmentDAO.enrollStudent("Coca Sergiu","Analiza Matematica");

        enrollments = enrollmentDAO.getEnrollmentIds();
        int sizeAfter = enrollments.size();

        assertEquals("Enroll student test failed!",sizeBefore+1,sizeAfter);
    }

    @Test
    //@Rollback
    public void testDeleteEnrollment(){

        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentIds();

        int sizeBefore = enrollments.size();

        enrollmentDAO.deleteEnrollment("Narita Catalin-Ioan","Software Design");

        enrollments = enrollmentDAO.getEnrollmentIds();

        int sizeAfter = enrollments.size();
        assertEquals("Delete enrollment test failed",sizeBefore-1,sizeAfter);

    }

    /*@Test
    public void testUpdateEnrollment(){
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentIds();

        enrollmentDAO.updateEnrollment("Cosma Dragos","Arhitectura Calculatoarelor","Software Design");



        courses = courseDAO.getAllCourses();

        assertEquals("Update course failed!",course,courses.get(0));
    }*/

}
