import dao.ClassDAO;
import dao.impl.ClassDAOImpl;
import dao.impl.UserDAOImpl;
import entities.ClazzEntity;
import entities.UserEntity;
import service.ClassService;
import service.PostInfoService;
import service.PostService;
import service.UserService;
import service.impl.ClassServiceImpl;
import service.impl.PostInfoServiceImpl;
import service.impl.PostServiceImpl;
import service.impl.UserServiceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by huwendi on 2017/5/22.
 */
public class test {

    public static void main(String[] args) {
        //Session session = HibernateUtil.getOurSessionFactory().openSession();
        //Transaction transaction = null;
        try {
            //开启事务
          //  transaction = session.beginTransaction();

            /*
            List<Object[]> users = session.createSQLQuery("select * from postInfo where postID = 'p001'").list();
            for (Object[] e: users) {
                int k = e.length;
                for(int i = 0;i < k;i++)
                    System.out.println(e[i] + " ");
            }
            */
/*
            List<UserEntity> users = session.createQuery("select user from UserEntity user where user.userId = '101'").list();
            for (Iterator<UserEntity> iterator = users.iterator(); iterator.hasNext(); ) {
                UserEntity user = iterator.next();
                System.out.println(user.toString());
            }
*/
            Scanner input = new Scanner(System.in);
            System.out.println("输入要删除的replyID");
            String replyID = input.next();
            PostService postService = new PostServiceImpl();
            postService.deleteContent(replyID);
/*
            UserService userService = new UserServiceImpl();
            System.out.println(userService.getUserByID("301").toString());
*/
        }catch (Exception e) {
            //transaction.rollback();
            e.printStackTrace();
        }finally {
            //session.close();
        }
    }

}
