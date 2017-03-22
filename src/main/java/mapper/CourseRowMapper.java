package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Course;
import model.Student;
import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper  implements RowMapper<Course> {
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("course_id"));
        course.setName(rs.getString("name"));
        course.setTeacher(rs.getString("teacher"));
        course.setStudyYear(rs.getInt("study_year"));

        return course;
    }
}
