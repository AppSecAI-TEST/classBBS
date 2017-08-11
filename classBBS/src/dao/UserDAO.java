package dao;


import entities.UserEntity;
import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface UserDAO {
    List<UserEntity> getAllUsers ();
    //��ѯ�����û�
    UserEntity getUserByID (String userID);
    //ͨ��userID��ѯ�û�
    UserEntity getUserByAccount (String account);
    //ͨ��account��ѯ�û�
    boolean equalUserNickName (String nickName);
    //��ѯ�����nickName���ݿ��Ƿ��м�¼
    boolean equalUserAccount (String account);
    //��ѯ�����account���ݿ��Ƿ��м�¼
    boolean saveUser (UserEntity user);
    //��user����user����
    int getTypeNumber(String type);
    //��ѯĳ�����������û�����
    UserEntity login(UserEntity user);
    //��֤user��¼�Ƿ�ɹ�
    boolean updateUser(UserEntity user);
    //�������ݿ�user
    boolean deleteUser(String userID);
    //����userIDɾ���û�
}
