package dao.impl;

import dao.PostInfoDAO;
import entities.PostInfoEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by huwendi on 2017/6/4.
 */
public class PostInfoDAOImpl implements PostInfoDAO {
    private static Logger logger = Logger.getLogger(PostInfoDAOImpl.class);
    public List<PostInfoEntity> getAllPostInfo (){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            List<PostInfoEntity> postInfo = session.createQuery("from PostInfoEntity ").list();
            return postInfo;
        }catch (Exception e){
            transaction.rollback();
            logger.error("PostInfoDAOImpl getAllPostInfo: ��ѯ��������ʧ��! " + e);
        }finally {
            session.close();
        }
        return null;
    }
    //��ѯ���е�������Ϣ
    public PostInfoEntity getPostInfoByID(String postID){
        Session session = HibernateUtil.getSession();
        PostInfoEntity postInfo = session.get(PostInfoEntity.class, postID);
        return postInfo;
    }
    //ͨ��postID��ѯ������Ϣ
    public List<PostInfoEntity> getClassAllPostInfo(String classID){
        Session session = HibernateUtil.getSession();
        String hql = "select postInfo from PostInfoEntity postInfo where postInfo.classId = :ClassID";
        List<PostInfoEntity> classAllposts = session.createQuery(hql).setParameter("ClassID", classID).list();
        return classAllposts;
    }
    //ͨ��classID��ѯ�ÿγ�����������Ϣ
    public boolean savePostInfo (PostInfoEntity postInfo){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(postInfo);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("savePostInfo: �������ݿ�ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //��postInfo����postInfo����
    public boolean updatePostInfo(PostInfoEntity postInfo){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(postInfo);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("modifyPostInfo: �������ݿ�ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //�������ݿ�postInfo
    public boolean deletePostInfo(PostInfoEntity postInfo){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(postInfo);
            transaction.commit();;
            return true;
        }catch (Exception e){
            transaction.rollback();
            logger.error("deletePostInfo: ɾ������ʧ��!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //ɾ�����ݿ�postInfo
}
