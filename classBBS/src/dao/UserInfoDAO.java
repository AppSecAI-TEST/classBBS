package dao;

import entities.UserInfoEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface UserInfoDAO {
    List<UserInfoEntity> getAllUsersInfo();
    //查询所有用户信息
    UserInfoEntity getUserInfoByID(String userID);
    //通过userID查询用户信息
    boolean saveUserInfo (UserInfoEntity userInfo);
    //将userInfo插入userInfo表中
    boolean deleteUserInfo(String userID);
    //根据userID删除用户信息
}
