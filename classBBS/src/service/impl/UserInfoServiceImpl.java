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
    //查询所有用户信息
    public UserInfoEntity getUserInfoByID (String userID){
        return userInfoDAO.getUserInfoByID(userID);
    }
    //通过userID查询用户信息
    public boolean saveUserInfo (UserInfoEntity userInfo){
        return userInfoDAO.saveUserInfo(userInfo);
    }
    //保存用户信息
    public boolean deleteUserInfo(String userID){
        return userInfoDAO.deleteUserInfo(userID);
    }
    //删除用户信息
}
