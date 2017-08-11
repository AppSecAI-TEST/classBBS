package dao;

import entities.ClazzEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/4.
 */
public interface ClassDAO {
    List<ClazzEntity> getAllClasses();
    //查询所有课程
    ClazzEntity getClassByID(String classID);
    //通过classID查询课程
    List<ClazzEntity> getUserAllClasses(String userID);
    //通过studentID查询所有课程
    List<String> getStudentAllClassID (String studentID);
    //通过studentID查询所有classID
}
