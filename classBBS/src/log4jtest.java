import org.apache.log4j.Logger;

/**
 * Created by huwendi on 2017/5/23.
 */
public class log4jtest {
    private static  final Logger LOGGER = Logger.getLogger(log4jtest.class);
    public static void  main(String[] agrs){
        // ��¼debug�������Ϣ
        LOGGER.debug("This is debug message.");
        // ��¼info�������Ϣ
        LOGGER.info("This is info message.");
        // ��¼warn�������Ϣ
        LOGGER.info("This is warn message.");
        // ��¼error�������Ϣ
        LOGGER.error("This is error message.");
    }
}
