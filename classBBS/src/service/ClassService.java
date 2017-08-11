package service;

import entities.ClazzEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/4.
 */
public interface ClassService {
    List<ClazzEntity> getAllClasses();
    //��ȡ���пγ�
    ClazzEntity getClassByID(String classID);
    //ͨ��classID��ȡ�γ�
    List<ClazzEntity> getUserAllClasses(String userID);
    //��ȡĳѧ�����пγ�
    List<String> getStudentAllClassID (String studentID);
    //��ȡĳѧ������classID
}
