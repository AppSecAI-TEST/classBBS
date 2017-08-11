package dao.impl;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import dao.UserDAO;
import entities.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */

public class UserDAOImpl implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);
    public List<UserEntity> getAllUsers(){
        Session session = HibernateUtil.getOurSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            List<UserEntity> users = session.createQuery("from UserEntity ").list();
            return users;
        }catch (Exception e){
            transaction.rollback();
            logger.error("UserDAOImpl getAllUsers: ��ѯ�����û�ʧ��!");
        }finally {
            session.close();
        }
        return null;
    }
    //��ѯ�����û�
    public UserEntity getUserByID(String userID){
        Session session = HibernateUtil.getSession();
        UserEntity user = session.get(UserEntity.class, userID);
        if(user != null)
            return user;
        else
            return null;
    }
    //ͨ��userID��ѯ�û�
    public UserEntity getUserByAccount(String account){
        Session session = HibernateUtil.getSession();
        String hql = "select user from UserEntity user where user.account = :Account";
        List<UserEntity> user = session.createQuery(hql).setParameter("Account", account).list();
        if(user.size() != 0)
            return user.get(0);
        else
            return null;
    }
    //ͨ��account��ѯ�û�
    public boolean equalUserNickName(String nickName){
    Session session = HibernateUtil.getSession();
    List<String> allUserNickNames = session.createQuery("select user.nickName from UserEntity user").list();
        if(allUserNickNames.contains(nickName))
            return true;
        else
            return false;
    }
    //��ѯ�����nickName���ݿ��Ƿ��м�¼
    public boolean equalUserAccount(String account){
        Session session = HibernateUtil.getSession();
        List<String> allUserAccounts = session.createQuery("select user.account from UserEntity user").list();
        if(allUserAccounts.contains(account))
            return true;
        else
            return false;
    }
    //��ѯ�����account���ݿ��Ƿ��м�¼
    public boolean saveUser (UserEntity user){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            //session.getTransaction().commit();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("saveUser: �������ݿ�ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //��user����user����
    public int getTypeNumber(String type){
        Session session = HibernateUtil.getSession();
        List<String> allTypeUsers = null;
        if(type.equals("student"))
            allTypeUsers = session.createQuery("select user from UserEntity user where user.type = 'student'").list();
        else if(type.equals("teacher"))
            allTypeUsers = session.createQuery("select user from UserEntity user where user.type = 'teacher'").list();
        else if (type.equals("admin"))
            allTypeUsers = session.createQuery("select user from UserEntity user where user.type = 'admin'").list();
        return allTypeUsers.size();
    }
    //��ѯĳ�����������û�����
    public UserEntity login(UserEntity user){
        UserEntity u = getUserByAccount(user.getAccount());
        if(u != null) {
            if (user.getPassword().equals(u.getPassword()))
                return u;
            else
                return null;
        }
        else
            return null;
    }
    //��֤user��¼�Ƿ�ɹ�
    public boolean updateUser(UserEntity user){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("modifyUser: �������ݿ�ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //�������ݿ�user
    public boolean deleteUser(String userID){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "delete from UserEntity where userId = :UserID";
            Query query =  session.createQuery(hql).setParameter("UserID", userID);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("deleteContent: ɾ�����ݿ�����ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //����userIDɾ���û�


}
