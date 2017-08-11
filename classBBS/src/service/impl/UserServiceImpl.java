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
    //��ȡ�����û��б�
    public UserEntity getUserByID (String userID){
        return userDAO.getUserByID(userID);
    }
    //ͨ��userID��ȡ�û���Ϣ
    public UserEntity getUserByAccount (String account){
        return userDAO.getUserByAccount(account);
    }
    //ͨ��account��ȡ�û���Ϣ
    public boolean equalUserNickName (String nickName){
        return userDAO.equalUserNickName(nickName);
    }
    //���������nickName�Ƿ��ѱ�ʹ��
    public boolean equalUserAccount (String account){
        return userDAO.equalUserAccount(account);
    }
    //���������account�Ƿ��ѱ�ʹ��
    public boolean saveUser (UserEntity user){
        return userDAO.saveUser(user);
    }
    //ע���û�
    public int getTypeNumber (String type){
        return userDAO.getTypeNumber(type);
    }
    //��ȡĳ�����û�����
    public UserEntity login (UserEntity user){
        return userDAO.login(user);
    }
    //��֤user��¼�Ƿ�ɹ�
    public boolean updateUser(UserEntity user){
        return userDAO.updateUser(user);
    }
    //�����û�
    public boolean deleteUser(String userID){
        return userDAO.deleteUser(userID);
    }
    //ɾ���û�
}
