package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;
import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper  implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("student_id"));
            student.setName(rs.getString("name"));
            student.setBirthDate(rs.getDate("birth_date"));
            student.setAddress(rs.getString("address"));

            return student;
        }
}

