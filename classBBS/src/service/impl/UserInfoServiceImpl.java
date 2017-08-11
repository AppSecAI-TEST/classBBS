package service.impl;

import dao.UserInfoDAO;
import dao.impl.UserInfoDAOImpl;
import entities.UserInfoEntity;
import service.UserInfoService;

import java.util.List;

/**
 * Created by huwendi on 2017/6/2.
 */
public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoDAO userInfoDAO = new UserInfoDAOImpl();
    public List<UserInfoEntity> getAllUsersInfo (){
        return userInfoDAO.getAllUsersInfo();
    }
    //��ѯ�����û���Ϣ
    public UserInfoEntity getUserInfoByID (String userID){
        return userInfoDAO.getUserInfoByID(userID);
    }
    //ͨ��userID��ѯ�û���Ϣ
    public boolean saveUserInfo (UserInfoEntity userInfo){
        return userInfoDAO.saveUserInfo(userInfo);
    }
    //�����û���Ϣ
    public boolean deleteUserInfo(String userID){
        return userInfoDAO.deleteUserInfo(userID);
    }
    //ɾ���û���Ϣ
}
