package service;

import entities.UserInfoEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/2.
 */
public interface UserInfoService {
    List<UserInfoEntity> getAllUsersInfo();
    //查询所有用户信息
    UserInfoEntity getUserInfoByID (String userID);
    //通过userID查询用户信息
    boolean saveUserInfo (UserInfoEntity userInfo);
    //保存用户信息
    boolean deleteUserInfo(String userID);
    //删除用户信息
}
