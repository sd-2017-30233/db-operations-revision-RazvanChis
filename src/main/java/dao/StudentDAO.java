package dao;

import mapper.StudentRowMapper;
import model.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class StudentDAO {

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


    public List<Student> getAllStudents(){
        String SQLcommand = "select * from student";
        List<Student> students = jdbcTemplate.query(SQLcommand,new StudentRowMapper());
        return students;
    }

    public void addStudent(Student student){
        String SQLcommand = "insert into student (student_id,name,birth_date,address) values (?,?,?,?);";
        jdbcTemplate.update(SQLcommand,student.getId(),student.getName(),student.getBirthDate(),student.getAddress());
    }

    public void updateStudent(Student student){
        String SQLcommand = "update student set name = ?, birth_date = ?, address = ? where student_id = ?;";
        jdbcTemplate.update(SQLcommand,student.getName(),student.getBirthDate(),student.getAddress(),student.getId());
    }

    public void deleteStudent(int id){
        String SQLcommand1 = "delete from enrollment where student_id = " + id + ";";
        String SQLcommand2 = "delete from student where student_id = " + id +";";

        jdbcTemplate.update(SQLcommand1);
        jdbcTemplate.update(SQLcommand2);

    }

    public Student studentToBeAdded(){

        Scanner in = new Scanner(System.in);

        int id = this.getStudentId();

        System.out.print("name > ");
        String name = in.nextLine();

        System.out.print("birth date > ");
        String date = in.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;

        try {
            java.util.Date javaDate = sdf.parse(date);
            sqlDate = new java.sql.Date(javaDate.getTime());
        }catch(ParseException e){
            System.out.println("Cannot parse date!");
        }

        System.out.print("address > ");
        String address = in.nextLine();

        return new Student(id,name,sqlDate,address);

    }

    public Student studentToBeUpdated(){
        Scanner in = new Scanner(System.in);
        System.out.print("Id > ");
        int id = in.nextInt();
        in.nextLine();

        System.out.print("name > ");
        String name = in.nextLine();

        System.out.print("birth date > ");
        String date = in.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;

        try {
            java.util.Date javaDate = sdf.parse(date);
            sqlDate = new java.sql.Date(javaDate.getTime());
        }catch(ParseException e){
            System.out.println("Cannot parse date!");
        }

        System.out.print("address > ");
        String address = in.nextLine();

        return new Student(id,name,sqlDate,address);

    }

    public int getIdForDelete(){

        Scanner in = new Scanner(System.in);

        System.out.print("Id > ");
        int id = in.nextInt();
        in.nextLine();

        return id;

    }

    public int getStudentId(){
        List<Student> students = this.getAllStudents();
        int min = 1;
        if(students.size()>0) {
            int max = students.get(students.size() - 1).getId();
            for (Student t : students) {
                if (min != t.getId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }

}
