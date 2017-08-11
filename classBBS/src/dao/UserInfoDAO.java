package dao;

import entities.UserInfoEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface UserInfoDAO {
    List<UserInfoEntity> getAllUsersInfo();
    //��ѯ�����û���Ϣ
    UserInfoEntity getUserInfoByID(String userID);
    //ͨ��userID��ѯ�û���Ϣ
    boolean saveUserInfo (UserInfoEntity userInfo);
    //��userInfo����userInfo����
    boolean deleteUserInfo(String userID);
    //����userIDɾ���û���Ϣ
}
