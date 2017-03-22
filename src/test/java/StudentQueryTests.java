import dao.StudentDAO;
import model.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentQueryTests {
   /* @Configuration
    static class ContextConfiguration{
        @Bean
        public ApplicationContext getApplicationContext(){
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            return context;
        }
    }*/


    /*@Autowired
    private static ApplicationContext context;*/
    static StudentDAO studentDAO;
    static ApplicationContext context;

    @BeforeClass
    public static void setUp(){
        context = new ClassPathXmlApplicationContext("beans.xml");
        studentDAO = (StudentDAO)context.getBean("studentJDBCTemplate");
        studentDAO.createDefaultDB();
    }



    /*@Test
    public void testGetAllStudents(){

        List<Student> students = studentDAO.getAllStudents();

        assertEquals("List students test failed!","Narita Catalin-Ioan",students.get(0).getName());
    }*/

    @Test
    public void testAddStudent(){
        List<Student> students = studentDAO.getAllStudents();
        int sizeBefore = students.size();

        int id = studentDAO.getStudentId();
        String name = "nume de test";

        Date date = null;

        try {
            date = new Date((new SimpleDateFormat("yyyy-MM-dd")).parse("1994-07-21").getTime());
        }catch(ParseException e){
            System.out.println("Parse exception!");
        }

        String address = "adresa";

        studentDAO.addStudent(new Student(id,name,date,address));

        students = studentDAO.getAllStudents();
        int sizeAfter = students.size();

        assertEquals("Add student test failed!",sizeBefore+1,sizeAfter);
    }

    @Test
    //@Rollback
    public void testDeleteStudent(){

        List<Student> students = studentDAO.getAllStudents();

        int sizeBefore = students.size();

        int id = students.get(students.size()-1).getId();

        studentDAO.deleteStudent(id);

        students = studentDAO.getAllStudents();

        int sizeAfter = students.size();
        assertEquals("Delete student test failed",sizeBefore-1,sizeAfter);

    }

    @Test
    public void testUpdateStudent(){

        List<Student> students = studentDAO.getAllStudents();
        int id = students.get(0).getId();

        String name = "name update test";
        Date date = null;

        try {
            date = new Date((new SimpleDateFormat("yyyy-MM-dd")).parse("1994-07-21").getTime());
        }catch(ParseException e){
            System.out.println("Parse exception!");
        }

        String address = "address update test";

        Student student1 = new Student(id,name,date,address);

        studentDAO.updateStudent(student1);

        students = studentDAO.getAllStudents();

        assertEquals("Update student failed!",student1,students.get(0));
    }


}
