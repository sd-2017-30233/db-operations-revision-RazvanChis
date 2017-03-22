package run;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import model.Course;
import model.Enrollment;
import model.Student;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) throws BeansException {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        StudentDAO studentDAO = (StudentDAO)context.getBean("studentJDBCTemplate");
        CourseDAO courseDAO = (CourseDAO)context.getBean("courseJDBCTemplate");
        EnrollmentDAO enrollmentDAO = (EnrollmentDAO)context.getBean("enrollmentJDBCTemplate");

        List<Student> students = studentDAO.getAllStudents();
        List<Course> courses = courseDAO.getAllCourses();
        List<Enrollment> enrollments =  enrollmentDAO.getEnrollmentIds();


        //System.out.println(students);
        //System.out.println(courses);
        //System.out.println(enrollments);

        //studentDAO.addStudent(studentDAO.studentToBeAdded());
        //studentDAO.updateStudent(studentDAO.studentToBeUpdated());
        //studentDAO.deleteStudent(studentDAO.getIdForDelete());

        //courseDAO.addCourse(courseDAO.courseToBeAdded());
        //courseDAO.updateCourse(courseDAO.courseToBeUpdated());
        //courseDAO.deleteCourse(courseDAO.getIdForDelete());

        //enrollmentDAO.getEnrollmentList(enrollments);
        //enrollmentDAO.enrollStudent(enrollmentDAO.getStudentName(),enrollmentDAO.getCourseName());
        //enrollmentDAO.updateEnrollment(enrollmentDAO.getStudentName(),enrollmentDAO.getOldCourseName(),enrollmentDAO.getNewCourseName());
        //enrollmentDAO.deleteEnrollment(enrollmentDAO.getStudentName(),enrollmentDAO.getCourseName());


    }


}
