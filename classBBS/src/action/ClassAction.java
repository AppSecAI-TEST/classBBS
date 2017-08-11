package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entities.ClazzEntity;
import entities.UserEntity;
import org.apache.struts2.StrutsStatics;
import service.ClassService;
import service.impl.ClassServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by huwendi on 2017/6/4.
 */
public class ClassAction extends ActionSupport {
    private ClassService classService = new ClassServiceImpl();
    private ClazzEntity clazz = new ClazzEntity();
    private static String userID;

    public ClazzEntity getClazz(){
        return clazz;
    }
    public void setClazz(ClazzEntity clazz){
        this.clazz = clazz;
    }

/*
    public String searchClasses(){
        clazz.setClassStudentId(userID);
        List<ClazzEntity> classes = classService.getUserAllClasses();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        request.setAttribute("classes", classes);
        return SUCCESS;
    }
    */
}
