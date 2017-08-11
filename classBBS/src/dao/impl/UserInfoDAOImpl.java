package dao.impl;

import dao.UserInfoDAO;
import entities.UserInfoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * Created by huwendi on 2017/6/2.
 */
public class UserInfoDAOImpl implements UserInfoDAO {
    private static Logger logger = Logger.getLogger(UserInfoDAOImpl.class);
    public List<UserInfoEntity> getAllUsersInfo(){
        Session session = HibernateUtil.getOurSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            List<UserInfoEntity> users = session.createQuery("from UserInfoEntity ").list();
            return users;
        }catch (Exception e){
            transaction.rollback();
            logger.error("UserDAOImpl getAllUsers: 查询所有用户失败!");
        }finally {
            session.close();
        }
        return null;
    }
    //查询所有用户信息
    public UserInfoEntity getUserInfoByID(String userID){
        Session session = HibernateUtil.getSession();
        UserInfoEntity userInfo = session.get(UserInfoEntity.class, userID);
        return userInfo;
    }
    //通过userID查询用户信息
    public boolean saveUserInfo (UserInfoEntity userInfo){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(userInfo);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("Save UserInfo: 插入数据库失败 " + e);
        }finally {
            session.close();
        }
        return false;
    }
    //将userInfo插入userInfo表中
    public boolean deleteUserInfo(String userID){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "delete from UserInfoEntity where userId = :UserID";
            Query query =  session.createQuery(hql).setParameter("UserID", userID);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("deleteUserInfo: 删除数据库数据失败!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //根据userID删除用户信息
}
