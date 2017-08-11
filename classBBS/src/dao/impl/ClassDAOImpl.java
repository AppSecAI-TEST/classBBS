package dao.impl;

import dao.ClassDAO;
import entities.ClazzEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by huwendi on 2017/6/4.
 */
public class ClassDAOImpl implements ClassDAO {
    private static Logger logger = Logger.getLogger(ClassDAOImpl.class);
    public List<ClazzEntity> getAllClasses(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            List<ClazzEntity> classes = session.createQuery("from ClazzEntity ").list();
            return classes;
        }catch (Exception e){
            transaction.rollback();
            logger.error("ClassDAOImpl getAllClasses: ��ѯ���пγ�ʧ��! " + e);
        }finally {
            session.close();
        }
        return null;
    }
    //��ѯ���пγ�
    public ClazzEntity getClassByID(String classID){
        Session session = HibernateUtil.getSession();
        ClazzEntity theClass = session.get(ClazzEntity.class, classID);
        return theClass;
    }
    //ͨ��classID��ѯ�γ�
    public List<ClazzEntity> getUserAllClasses(String userID){
        Session session = HibernateUtil.getSession();
        String hql = "from ClazzEntity theClass where theClass.classStudentId = :UserID or theClass.classTeacherId = :UserID";
        List<ClazzEntity> classes = session.createQuery(hql).setParameter("UserID", userID).list();
        return classes;
    }
    //ͨ��studentID��ѯ���пγ�
    public List<String> getStudentAllClassID(String studentID){
        Session session = HibernateUtil.getSession();
        String hql = "select theClass.classId from ClazzEntity theClass where theClass.classStudentId = :StudentID";
        List<String> classes = session.createQuery(hql).setParameter("StudentID", studentID).list();
        return classes;
    }
    //ͨ��studentID��ѯ����classID
}
