package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entities.ClazzEntity;
import entities.PostInfoEntity;
import entities.UserEntity;
import entities.UserInfoEntity;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.components.ActionError;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.ClassService;
import service.PostInfoService;
import service.UserInfoService;
import service.UserService;
import service.impl.ClassServiceImpl;
import service.impl.PostInfoServiceImpl;
import service.impl.UserInfoServiceImpl;
import service.impl.UserServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by huwendi on 2017/5/26.
 */
public class UserAction extends ActionSupport{
    private ClassService classService = new ClassServiceImpl();
    private UserService userService = new UserServiceImpl();
    private UserInfoService userInfoService = new UserInfoServiceImpl();
    private PostInfoService postInfoService = new PostInfoServiceImpl();
    private UserEntity user = new UserEntity();
    private UserInfoEntity userInfo = new UserInfoEntity();
    private String userID;
    private String answer;                                                                                              //��ȡ�һ������лش��ܱ�����ʱ�����ܱ������
    private String retype_password;                                                                                     //��ȡע��ʱȷ������
    private String classID;
    public UserEntity getUser (){
        return user;
    }
    public void setUser (UserEntity user){
        this.user = user;
    }

    public UserInfoEntity getUserInfo(){
        return userInfo;
    }
    public void setUserInfo(UserInfoEntity userInfo){
        this.userInfo = userInfo;
    }

    public String getRetype_password (){
        return retype_password;
    }
    public void setRetype_password (String retype_password){
        this.retype_password = retype_password;
    }

    public String getAnswer(){
        return answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getClassID(){
        return classID;
    }
    public void setClassID(String classID){
        this.classID = classID;
    }

    public String getUserID(){
        return userID;
    }
    public void setUserID(String userID){
        this.userID = userID;
    }

    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.account", message = "�������˻�!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.password", message = "����������!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "retype_password", message = "���ٴ���������!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.nickName", message = "�������ǳ�!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.question", message ="�������ܱ�����"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.answer", message = "�����������!")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "16", fieldName = "user.account"
                    , message = "�˻���������2~16֮��!"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "8", fieldName = "user.nickName"
                    , message = "�ǳƳ�����2~8֮��!")
            }
    )
    public String register() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(retype_password == null || !retype_password.equals(user.getPassword())){
            //addActionError("�����������벻һ��!");
            request.setAttribute("RetypePasswordWrong", "�����������벻һ��!");
            return INPUT;
        }
        if(userService.equalUserAccount(user.getAccount())){
            //addActionError("�˻��Ѵ��ڣ�����������!");
            request.setAttribute("HasAccountWrong", "�˺��Ѵ���,����������!");
            return INPUT;
        }
        if(userService.equalUserNickName(user.getNickName())){
            //addActionError("�ǳ��Ѵ��ڣ�����������!");
            request.setAttribute("HasNickNameWrong", "�ǳ��Ѵ���,����������!");
            return INPUT;
        }
        if(user.getType().equals("student"))
            user.setUserId("10" + (userService.getTypeNumber("student") + 1 + ""));
        else if(user.getType().equals("teacher"))
            user.setUserId("20" + (userService.getTypeNumber("teacher") + 1 + ""));
        else
            user.setUserId("30" + (userService.getTypeNumber("admin")  + 1 + ""));
        if(userService.saveUser(user))
            return SUCCESS;
        else
            return INPUT;
    }
    //�û�ע��
    @SkipValidation
    public String logIn() {
        user = userService.login(user);
        if(user != null){
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("LogInUser", user);                                                                             //��ס��¼�û���������Ϣ
            UserInfoService userInfoService = new UserInfoServiceImpl();
            userInfo = userInfoService.getUserInfoByID(user.getUserId());
            session.put("LogInUserInfo", userInfo);                                                                     //��ס�û���ϸ��Ϣ
            List<ClazzEntity> classes;
            if(!user.getType().equals("admin"))
                classes = classService.getUserAllClasses(user.getUserId());
            else
                classes = classService.getAllClasses();
            session.put("UserAllClasses", classes);
            if(classes.size() != 0)
                classID = classes.get(0).getClassId();                                                                  //��½�����Ĭ�Ͽγ���̳
            else
                classID = null;
            return SUCCESS;
        }
        else {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("LogInErrorInfo", "�˺Ż��������!");
            return INPUT;
        }
    }
    //�û���¼
    @SkipValidation
    public String passwordBack(){
        if(userService.equalUserAccount(user.getAccount())){
            user = userService.getUserByAccount(user.getAccount());
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("PasswordBackUser", user);
            return SUCCESS;
        }
        else{
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("PasswordBackAccountWrong", "�˺Ų�����!");
            return INPUT;
        }
    }
    //ȷ�����һص��˺�
    @SkipValidation
    public String questionAnswer(){
        UserEntity userEntity = (UserEntity)ActionContext.getContext().getSession().get("PasswordBackUser");
        if(answer.equals(userEntity.getAnswer()))
            return SUCCESS;
        else{
            addActionMessage("�𰸴���!������!");                                                               //��Ӵ𰸴�����Ϣ
            return INPUT;
        }
    }
    //����ܱ����������ȷ
    @SkipValidation
    public String questionAnswerFlash(){
        UserEntity userEntity = (UserEntity)ActionContext.getContext().getSession().get("PasswordBackUser");
        if(hasActionMessages()) {                                                                                       //����Ǵ𰸴���ˢ��ҳ�棬�����𰸴�����Ϣ
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("QuestionAnswerWrong", "�𰸴���!������!");
            if (answer != null)
                answer = "";
            if (user.getQuestion() == null)
                user.setQuestion(userEntity.getQuestion());
        }
        return INPUT;
    }
    //�����ܱ��������
    @SkipValidation
    public String updatePassword(){
        UserEntity userEntity = (UserEntity)ActionContext.getContext().getSession().get("PasswordBackUser");            //������һ����룬��userEntityΪ�һ������û�
        if(userEntity == null)
            userEntity = (UserEntity)ActionContext.getContext().getSession().get("LogInUser");                          //����ǵ�¼�Ժ��޸����룬��userEntityΪ��¼�û�
        userEntity.setPassword(user.getPassword());
        if(userService.updateUser(userEntity))
            return SUCCESS;
        else
            return INPUT;
    }
    //��������
    @SkipValidation
    public String updateUserFlash(){
        /*
        if(hasActionMessages()) {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("UpdateUserSuccess", "�޸ĳɹ�!");                                                      //������޸��û��ɹ���ˢ��ҳ�棬������޸ĳɹ���Ϣ
        }
        return INPUT;
        */
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        UserEntity userEntity = userService.getUserByID(userID);
        if(userEntity != null){
            request.setAttribute("UserBasicInfo", userEntity);
            request.setAttribute("GetUserSuccess", "��ȡ�û���Ϣ�ɹ�!");
            return SUCCESS;
        }
        else{
            request.setAttribute("GetUserWrong", "��ȡ�û���Ϣʧ��!");
            return INPUT;
        }
    }
    //�޸��û���Ϣҳ��
    @SkipValidation
    public String updateUser() {
        UserEntity userEntity = (UserEntity) ActionContext.getContext().getSession().get("LogInUser");
        HttpServletRequest request = ServletActionContext.getRequest();
        String account = request.getParameter("account");
        String nickName = request.getParameter("nickName");

        if(userEntity.getType().equals("admin"))
            userEntity = userService.getUserByID(userID);

        if (!userEntity.getAccount().equals(account) || !userEntity.getNickName().equals(nickName)) {
            userEntity.setAccount(account);
            userEntity.setNickName(nickName);
            userService.updateUser(userEntity);
            request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("UpdateUserSuccess", "�޸ĳɹ�!");                                                //����޸ĳɹ���Ϣ
            return SUCCESS;
        }
        request.setAttribute("UpdateUserWrong", "�޸�ʧ��!");
        return INPUT;
    }
    //�޸��û���Ϣ
    @SkipValidation
    public String getAllUser(){
        List<UserEntity> allUsers = userService.getAllUsers();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(allUsers.size() != 0){
            request.setAttribute("AllUsers", allUsers);
            request.setAttribute("GetAllUsersSuccess", "��ȡ�����û��ɹ�!");
            return SUCCESS;
        }
        else{
            request.setAttribute("GetAllUsersWrong", "��ѯʧ��!");
            return INPUT;
        }
    }
    //��ȡ�����û�
    @SkipValidation
    public String deleteUser() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if (userService.deleteUser(userID) && userInfoService.deleteUserInfo(userID)) {
            request.setAttribute("DeleteUserSuccess", "ɾ���û��ɹ�!");
            return SUCCESS;
        } else {
            request.setAttribute("DeleteUserWrong", "ɾ���û�ʧ��!");
            return SUCCESS;
        }
    }
}
