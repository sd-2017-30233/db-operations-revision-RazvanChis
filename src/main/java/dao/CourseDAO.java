package dao;

import mapper.CourseRowMapper;
import model.Course;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class CourseDAO {

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

    public List<Course> getAllCourses(){
        String SQLcommand = "select * from course";
        List<Course> courses = jdbcTemplate.query(SQLcommand,new CourseRowMapper());
        return courses;
    }

    public void addCourse(Course course){
        String SQLcommand = "insert into course (course_id,name,teacher,study_year) values (?,?,?,?);";
        jdbcTemplate.update(SQLcommand,course.getId(),course.getName(),course.getTeacher(),course.getStudyYear());
    }

    public void updateCourse(Course course){
        String SQLcommand = "update course set name = ?, teacher = ?, study_year = ? where course_id = ?;";
        jdbcTemplate.update(SQLcommand,course.getName(),course.getTeacher(),course.getStudyYear(),course.getId());
    }

    public void deleteCourse(int id){
        String SQLcommand1 = "delete from enrollment where course_id = " + id + ";";
        String SQLcommand2 = "delete from course where course_id = " + id +";";

        jdbcTemplate.update(SQLcommand1);
        jdbcTemplate.update(SQLcommand2);
    }

    public Course courseToBeAdded(){

        Scanner in = new Scanner(System.in);

        int id = this.getCourseId();

        System.out.print("name > ");
        String name = in.nextLine();

        System.out.print("teacher > ");
        String teacher = in.nextLine();

        System.out.print("study year > ");
        int studyYear = in.nextInt();

        return new Course(id,name,teacher,studyYear);

    }

    public Course courseToBeUpdated(){
        Scanner in = new Scanner(System.in);
        System.out.print("Id > ");
        int id = in.nextInt();
        in.nextLine();

        System.out.print("name > ");
        String name = in.nextLine();

        System.out.print("teacher > ");
        String teacher = in.nextLine();

        System.out.print("study year > ");
        int studyYear = in.nextInt();

        return new Course(id,name,teacher,studyYear);

    }

    public int getIdForDelete(){

        Scanner in = new Scanner(System.in);

        System.out.print("Id > ");
        int id = in.nextInt();
        in.nextLine();

        return id;

    }

    public int getCourseId(){
        List<Course> courses = this.getAllCourses();
        int min = 1;
        if(courses.size()>0) {
            int max = courses.get(courses.size() - 1).getId();
            for (Course t : courses) {
                if (min != t.getId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }

}
