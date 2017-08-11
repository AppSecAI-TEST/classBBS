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
    //��ȡ���пγ�
    public ClazzEntity getClassByID(String classID){
        return classDAO.getClassByID(classID);
    }
    //ͨ��classID��ȡ�γ�
    public List<ClazzEntity> getUserAllClasses(String userID){
        return classDAO.getUserAllClasses(userID);
    }
    //��ȡĳѧ������classID
    public List<String> getStudentAllClassID  (String studentID){
        return classDAO.getStudentAllClassID(studentID);
    }
    //ͨ��studentID��ȡ����classID
}
