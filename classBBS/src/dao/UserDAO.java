package dao;


import entities.UserEntity;
import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface UserDAO {
    List<UserEntity> getAllUsers ();
    //查询所有用户
    UserEntity getUserByID (String userID);
    //通过userID查询用户
    UserEntity getUserByAccount (String account);
    //通过account查询用户
    boolean equalUserNickName (String nickName);
    //查询输入的nickName数据库是否有记录
    boolean equalUserAccount (String account);
    //查询输入的account数据库是否有记录
    boolean saveUser (UserEntity user);
    //将user插入user表中
    int getTypeNumber(String type);
    //查询某个类型所有用户数量
    UserEntity login(UserEntity user);
    //验证user登录是否成功
    boolean updateUser(UserEntity user);
    //更新数据库user
    boolean deleteUser(String userID);
    //根据userID删除用户
}
