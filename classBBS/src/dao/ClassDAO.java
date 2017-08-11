package dao;

import entities.ClazzEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/4.
 */
public interface ClassDAO {
    List<ClazzEntity> getAllClasses();
    //��ѯ���пγ�
    ClazzEntity getClassByID(String classID);
    //ͨ��classID��ѯ�γ�
    List<ClazzEntity> getUserAllClasses(String userID);
    //ͨ��studentID��ѯ���пγ�
    List<String> getStudentAllClassID (String studentID);
    //ͨ��studentID��ѯ����classID
}
