package service.impl;

import action.UserAction;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entities.UserEntity;
import service.UserService;

import java.util.List;

/**
 * Created by huwendi on 2017/5/26.
 */
public class UserServiceImpl implements UserService{
    private UserDAO userDAO = new UserDAOImpl();
    public List<UserEntity> getAllUsers(){
        return userDAO.getAllUsers();
    }
    //获取所有用户列表
    public UserEntity getUserByID (String userID){
        return userDAO.getUserByID(userID);
    }
    //通过userID获取用户信息
    public UserEntity getUserByAccount (String account){
        return userDAO.getUserByAccount(account);
    }
    //通过account获取用户信息
    public boolean equalUserNickName (String nickName){
        return userDAO.equalUserNickName(nickName);
    }
    //检验输入的nickName是否已被使用
    public boolean equalUserAccount (String account){
        return userDAO.equalUserAccount(account);
    }
    //检验输入的account是否已被使用
    public boolean saveUser (UserEntity user){
        return userDAO.saveUser(user);
    }
    //注册用户
    public int getTypeNumber (String type){
        return userDAO.getTypeNumber(type);
    }
    //获取某类型用户数量
    public UserEntity login (UserEntity user){
        return userDAO.login(user);
    }
    //验证user登录是否成功
    public boolean updateUser(UserEntity user){
        return userDAO.updateUser(user);
    }
    //更新用户
    public boolean deleteUser(String userID){
        return userDAO.deleteUser(userID);
    }
    //删除用户
}
