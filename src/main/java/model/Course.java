package model;

public class Course {

    private int id;
    private String name;
    private String teacher;
    private int studyYear;

    public Course(){}

    public Course(int id, String name, String teacher, int studyYear) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.studyYear = studyYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", studyYear=" + studyYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (studyYear != course.studyYear) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        return teacher != null ? teacher.equals(course.teacher) : course.teacher == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + studyYear;
        return result;
    }
}
