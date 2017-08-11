package dao.impl;

import dao.PostDAO;
import entities.PostEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by huwendi on 2017/6/10.
 */
public class PostDAOImpl implements PostDAO {
    private static Logger logger = Logger.getLogger(PostInfoDAOImpl.class);
    public List<PostEntity> getPostByID(String postID){
        Session session = HibernateUtil.getSession();
        String hql = "select post from PostEntity post where post.postId = :PostID";
        List<PostEntity> postContent = session.createQuery(hql). setParameter("PostID", postID).list();
        return postContent;
    }
    //ͨ��postID��ѯ��������
    public boolean savePost (PostEntity post){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("savePost: �������ݿ�ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //��post����post����
    public boolean deletePost (String postID){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "delete from PostEntity where postId = :PostID";
            Query query =  session.createQuery(hql).setParameter("PostID", postID);
            query.executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("deletePost: ɾ�����ݿ�����ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //����postIDɾ����������
    public boolean deleteContent(String replyID){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "delete from PostEntity where replyId = :ReplyID";
            Query query =  session.createQuery(hql).setParameter("ReplyID", replyID);
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
    //����replyIDɾ����������
}
