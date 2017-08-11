package utils;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;


/**
 * Created by huwendi on 2017/5/21.
 */
public class HibernateUtil {
    private static SessionFactory ourSessionFactory;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();
    //SessionFactory和Configuration对象的实例，全局唯一
    private static Configuration configuration = new Configuration();

    private static ServiceRegistry serviceRegistry;

    static {
        try {
            /*
            //读取Hibernate配置文件 hibernate.cfd.xml
            configuration.configure();
            //创建服务注册构建器对象，通过配置对象中加载所有的配置信息
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            //创建注册服务
            //serviceRegistry = registryBuilder.build();
            //创建会话工厂
            //ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            ourSessionFactory = configuration.buildSessionFactory();
            */
            //Hibernate4创建SessionFactory的方式

            //注册服务
            serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            //创建会话工厂
            ourSessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
            //会话对象
            ourSessionFactory.openSession();
            //Hibernate5创建SessionFactory方式
            //Hibernate5中使用Hibernate4创建SessionFactory的方式，代码编译不会报错，但在运行时会抛出一个QuerySyntaxException异常：XXX is not mapped。
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        /*
        Session session = threadLocal.get();
        if(session == null || !session.isOpen()){
            if(ourSessionFactory == null)
                rebuildSessionFactory();
            session = (ourSessionFactory != null) ?
                    ourSessionFactory.openSession() : null;
            threadLocal.set(session);
        }
        return session;
        */
        return ourSessionFactory.openSession();
    }
    /*
    public static void rebuildSessionFactory(){
        try {
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                    .build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e){
            System.out.println("%%Error Creating SessionFactory%%" + e);
            e.printStackTrace();
        }
    }
    */
    public static void closeSession() throws HibernateException{
        Session session = threadLocal.get();
        threadLocal.set(null);
        if(session != null)
            session.clear();
    }
    public static SessionFactory getOurSessionFactory(){
        return ourSessionFactory;
    }
    public static Configuration getConfiguration(){
        return configuration;
    }
/*
    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            session.beginTransaction();
            List<UserEntity> users = session.createQuery("select user.nickName from UserEntity user").list();
            for (Iterator<UserEntity> iterator = users.iterator(); iterator.hasNext();){
                UserEntity user = iterator.next();
                System.out.println(user.toString());
            }
            /*
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }

        }catch (Exception e){
            Transaction transaction = session.getTransaction();
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
    */
}