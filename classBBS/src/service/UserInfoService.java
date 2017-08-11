package service;

import entities.UserInfoEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/2.
 */
public interface UserInfoService {
    List<UserInfoEntity> getAllUsersInfo();
    //��ѯ�����û���Ϣ
    UserInfoEntity getUserInfoByID (String userID);
    //ͨ��userID��ѯ�û���Ϣ
    boolean saveUserInfo (UserInfoEntity userInfo);
    //�����û���Ϣ
    boolean deleteUserInfo(String userID);
    //ɾ���û���Ϣ
}
