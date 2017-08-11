package service;

import entities.UserEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/5/26.
 */
public interface UserService {
    List<UserEntity> getAllUsers();
    //获取所有用户列表
    UserEntity getUserByID (String userID);
    //通过userID获取用户信息
    UserEntity getUserByAccount (String account);
    //通过account获取用户信息
    boolean equalUserNickName (String nickName);
    //检验输入的nickName是否已被使用
    boolean equalUserAccount (String account);
    //检验输入的account是否已被使用
    boolean saveUser (UserEntity user);
    //注册用户
    int getTypeNumber (String type);
    //获取某类型用户数量
    UserEntity login(UserEntity user);
    //用户登录
    boolean updateUser(UserEntity user);
    //更新用户
    boolean deleteUser(String userID);
    //删除用户

}
