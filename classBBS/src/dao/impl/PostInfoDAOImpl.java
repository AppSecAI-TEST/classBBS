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
            logger.error("PostInfoDAOImpl getAllPostInfo: 查询所有帖子失败! " + e);
        }finally {
            session.close();
        }
        return null;
    }
    //查询所有的帖子信息
    public PostInfoEntity getPostInfoByID(String postID){
        Session session = HibernateUtil.getSession();
        PostInfoEntity postInfo = session.get(PostInfoEntity.class, postID);
        return postInfo;
    }
    //通过postID查询帖子信息
    public List<PostInfoEntity> getClassAllPostInfo(String classID){
        Session session = HibernateUtil.getSession();
        String hql = "select postInfo from PostInfoEntity postInfo where postInfo.classId = :ClassID";
        List<PostInfoEntity> classAllposts = session.createQuery(hql).setParameter("ClassID", classID).list();
        return classAllposts;
    }
    //通过classID查询该课程所有帖子信息
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
            logger.error("savePostInfo: 插入数据库失败!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //将postInfo插入postInfo表中
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
            logger.error("modifyPostInfo: 更新数据库失败!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //更新数据库postInfo
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
            logger.error("deletePostInfo: 删除数据失败!" + e);
        }finally {
            session.close();
        }
        return false;
    }
    //删除数据库postInfo
}
