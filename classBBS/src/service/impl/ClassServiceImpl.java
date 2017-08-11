package service.impl;

import dao.ClassDAO;
import dao.impl.ClassDAOImpl;
import entities.ClazzEntity;
import service.ClassService;

import java.util.List;

/**
 * Created by huwendi on 2017/6/4.
 */
public class ClassServiceImpl implements ClassService{
    private ClassDAO classDAO = new ClassDAOImpl();
    public List<ClazzEntity> getAllClasses(){
        return classDAO.getAllClasses();
    }
    //获取所有课程
    public ClazzEntity getClassByID(String classID){
        return classDAO.getClassByID(classID);
    }
    //通过classID获取课程
    public List<ClazzEntity> getUserAllClasses(String userID){
        return classDAO.getUserAllClasses(userID);
    }
    //获取某学生所有classID
    public List<String> getStudentAllClassID  (String studentID){
        return classDAO.getStudentAllClassID(studentID);
    }
    //通过studentID获取所有classID
}
