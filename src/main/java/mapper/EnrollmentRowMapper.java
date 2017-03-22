package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Course;
import model.Enrollment;
import model.Student;
import org.springframework.jdbc.core.RowMapper;

public class EnrollmentRowMapper  implements RowMapper<Enrollment> {
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(rs.getInt("student_id"));
        enrollment.setCourseId(rs.getInt("course_id"));

        return enrollment;
    }
}