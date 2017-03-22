package dao;

import mapper.CourseRowMapper;
import mapper.EnrollmentRowMapper;
import mapper.StudentRowMapper;
import model.Course;
import model.Enrollment;
import model.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

public class EnrollmentDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createDefaultDB() {
        org.springframework.core.io.Resource resource = new ClassPathResource("script.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }

    public List<Enrollment> getEnrollmentIds(){
        String SQLcommand = "select * from enrollment";
        List<Enrollment> enrollments = jdbcTemplate.query(SQLcommand,new EnrollmentRowMapper());
        return enrollments;
    }

    public void getEnrollmentList(List<Enrollment> enrollments){
        int studentId;
        int courseId;
        String studentName;
        String courseName;
        for(Enrollment e : enrollments){
            studentId = e.getStudentId();
            courseId = e.getCourseId();
            studentName = jdbcTemplate.queryForObject("select name from student where student_id = " + studentId,String.class);
            courseName = jdbcTemplate.queryForObject("select name from course where course_id = "+ courseId,String.class);
            System.out.println(studentName + " enrolled for the course of " + courseName+ ".");
        }
    }

    public void enrollStudent(String studentName, String courseName){
        int studentId = jdbcTemplate.queryForObject("select student_id from student where name = '" + studentName + "';",Integer.class);
        int courseId = jdbcTemplate.queryForObject("select course_id from course where name = '" + courseName + "';",Integer.class);

        jdbcTemplate.update("insert into enrollment (student_id,course_id) values (?,?);",studentId,courseId);
    }

    public String getStudentName(){
        Scanner in = new Scanner(System.in);
        String studentName;
        System.out.print("student name > ");
        studentName = in.nextLine();
        return studentName;
    }

    public String getCourseName(){
        Scanner in = new Scanner(System.in);
        String courseName;
        System.out.print("course name > ");
        courseName = in.nextLine();
        return courseName;
    }

    public void updateEnrollment(String studentName,String oldCourse, String newCourse){
        int studentId = jdbcTemplate.queryForObject("select student_id from student where name = '" + studentName + "';",Integer.class);
        int oldCourseId = jdbcTemplate.queryForObject("select course_id from course where name = '" + oldCourse + "';",Integer.class);
        int newCourseId = jdbcTemplate.queryForObject("select course_id from course where name = '" + newCourse + "';",Integer.class);

        jdbcTemplate.update("update enrollment set course_id = ? where student_id = ? and course_id = ?;",newCourseId,studentId,oldCourseId);
    }

    public String getOldCourseName(){
        Scanner in = new Scanner(System.in);
        String courseName;
        System.out.print("old course name > ");
        courseName = in.nextLine();
        return courseName;
    }

    public String getNewCourseName(){
        Scanner in = new Scanner(System.in);
        String courseName;
        System.out.print("new course name > ");
        courseName = in.nextLine();
        return courseName;
    }

    public void deleteEnrollment(String studentName, String courseName){
        int studentId = jdbcTemplate.queryForObject("select student_id from student where name = '" + studentName + "';",Integer.class);
        int courseId = jdbcTemplate.queryForObject("select course_id from course where name = '" + courseName + "';",Integer.class);
        jdbcTemplate.update("delete from enrollment where course_id = ? and student_id = ?;",courseId,studentId);
    }

}
