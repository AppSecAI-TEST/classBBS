package entities;

import javax.persistence.*;

/**
 * Created by huwendi on 2017/5/23.
 */
@Entity
@Table(name = "class", schema = "bbs", catalog = "")
public class ClazzEntity {
    private String classId;
    private String classTeacherId;
    private String classStudentId;
    private String classTime;
    private String className;

    @Id
    @Column(name = "classID", nullable = false, length = 11)
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "classTeacherID", nullable = false, length = 11)
    public String getClassTeacherId() {
        return classTeacherId;
    }

    public void setClassTeacherId(String classTeacherId) {
        this.classTeacherId = classTeacherId;
    }

    @Basic
    @Column(name = "classStudentID", nullable = false, length = 11)
    public String getClassStudentId() {
        return classStudentId;
    }

    public void setClassStudentId(String classStudentId) {
        this.classStudentId = classStudentId;
    }

    @Basic
    @Column(name = "classTime", nullable = false, length = 11)
    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    @Basic
    @Column(name = "className", nullable = false, length = 11)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClazzEntity that = (ClazzEntity) o;

        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (classTeacherId != null ? !classTeacherId.equals(that.classTeacherId) : that.classTeacherId != null)
            return false;
        if (classStudentId != null ? !classStudentId.equals(that.classStudentId) : that.classStudentId != null)
            return false;
        if (classTime != null ? !classTime.equals(that.classTime) : that.classTime != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId != null ? classId.hashCode() : 0;
        result = 31 * result + (classTeacherId != null ? classTeacherId.hashCode() : 0);
        result = 31 * result + (classStudentId != null ? classStudentId.hashCode() : 0);
        result = 31 * result + (classTime != null ? classTime.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }
    @Override
    public String toString(){
        return "Class classID: "  + classId +
                " classTeacherID: " + classTeacherId +
                " classStudentID: " + classStudentId +
                " classTime: " + classTime +
                " className: " + className;
    }
}
