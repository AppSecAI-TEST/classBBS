package service;

import entities.UserEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/5/26.
 */
public interface UserService {
    List<UserEntity> getAllUsers();
    //��ȡ�����û��б�
    UserEntity getUserByID (String userID);
    //ͨ��userID��ȡ�û���Ϣ
    UserEntity getUserByAccount (String account);
    //ͨ��account��ȡ�û���Ϣ
    boolean equalUserNickName (String nickName);
    //���������nickName�Ƿ��ѱ�ʹ��
    boolean equalUserAccount (String account);
    //���������account�Ƿ��ѱ�ʹ��
    boolean saveUser (UserEntity user);
    //ע���û�
    int getTypeNumber (String type);
    //��ȡĳ�����û�����
    UserEntity login(UserEntity user);
    //�û���¼
    boolean updateUser(UserEntity user);
    //�����û�
    boolean deleteUser(String userID);
    //ɾ���û�

}
