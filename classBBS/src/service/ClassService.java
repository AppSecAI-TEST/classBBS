package service;

import entities.ClazzEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/4.
 */
public interface ClassService {
    List<ClazzEntity> getAllClasses();
    //获取所有课程
    ClazzEntity getClassByID(String classID);
    //通过classID获取课程
    List<ClazzEntity> getUserAllClasses(String userID);
    //获取某学生所有课程
    List<String> getStudentAllClassID (String studentID);
    //获取某学生所有classID
}
