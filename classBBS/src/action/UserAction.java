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
    private String answer;                                                                                              //获取找回密码中回答密保问题时输入密保问题答案
    private String retype_password;                                                                                     //获取注册时确认密码
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
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.account", message = "请输入账户!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.password", message = "请输入密码!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "retype_password", message = "请再次输入密码!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.nickName", message = "请输入昵称!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.question", message ="请输入密保问题"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.answer", message = "请输入问题答案!")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "16", fieldName = "user.account"
                    , message = "账户名长度在2~16之间!"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "8", fieldName = "user.nickName"
                    , message = "昵称长度在2~8之间!")
            }
    )
    public String register() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(retype_password == null || !retype_password.equals(user.getPassword())){
            //addActionError("两次输入密码不一致!");
            request.setAttribute("RetypePasswordWrong", "两次输入密码不一致!");
            return INPUT;
        }
        if(userService.equalUserAccount(user.getAccount())){
            //addActionError("账户已存在，请重新输入!");
            request.setAttribute("HasAccountWrong", "账号已存在,请重新输入!");
            return INPUT;
        }
        if(userService.equalUserNickName(user.getNickName())){
            //addActionError("昵称已存在，请重新输入!");
            request.setAttribute("HasNickNameWrong", "昵称已存在,请重新输入!");
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
    //用户注册
    @SkipValidation
    public String logIn() {
        user = userService.login(user);
        if(user != null){
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("LogInUser", user);                                                                             //记住登录用户的所有信息
            UserInfoService userInfoService = new UserInfoServiceImpl();
            userInfo = userInfoService.getUserInfoByID(user.getUserId());
            session.put("LogInUserInfo", userInfo);                                                                     //记住用户详细信息
            List<ClazzEntity> classes;
            if(!user.getType().equals("admin"))
                classes = classService.getUserAllClasses(user.getUserId());
            else
                classes = classService.getAllClasses();
            session.put("UserAllClasses", classes);
            if(classes.size() != 0)
                classID = classes.get(0).getClassId();                                                                  //登陆后进入默认课程论坛
            else
                classID = null;
            return SUCCESS;
        }
        else {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("LogInErrorInfo", "账号或密码错误!");
            return INPUT;
        }
    }
    //用户登录
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
            request.setAttribute("PasswordBackAccountWrong", "账号不存在!");
            return INPUT;
        }
    }
    //确认想找回的账号
    @SkipValidation
    public String questionAnswer(){
        UserEntity userEntity = (UserEntity)ActionContext.getContext().getSession().get("PasswordBackUser");
        if(answer.equals(userEntity.getAnswer()))
            return SUCCESS;
        else{
            addActionMessage("答案错误!请重试!");                                                               //添加答案错误信息
            return INPUT;
        }
    }
    //检查密保问题答案是正确
    @SkipValidation
    public String questionAnswerFlash(){
        UserEntity userEntity = (UserEntity)ActionContext.getContext().getSession().get("PasswordBackUser");
        if(hasActionMessages()) {                                                                                       //如果是答案错误刷新页面，则存入答案错误信息
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("QuestionAnswerWrong", "答案错误!请重试!");
            if (answer != null)
                answer = "";
            if (user.getQuestion() == null)
                user.setQuestion(userEntity.getQuestion());
        }
        return INPUT;
    }
    //返回密保问题界面
    @SkipValidation
    public String updatePassword(){
        UserEntity userEntity = (UserEntity)ActionContext.getContext().getSession().get("PasswordBackUser");            //如果是找回密码，则userEntity为找回密码用户
        if(userEntity == null)
            userEntity = (UserEntity)ActionContext.getContext().getSession().get("LogInUser");                          //如果是登录以后修改密码，则userEntity为登录用户
        userEntity.setPassword(user.getPassword());
        if(userService.updateUser(userEntity))
            return SUCCESS;
        else
            return INPUT;
    }
    //更新密码
    @SkipValidation
    public String updateUserFlash(){
        /*
        if(hasActionMessages()) {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
            request.setAttribute("UpdateUserSuccess", "修改成功!");                                                      //如果是修改用户成功后刷新页面，则存入修改成功信息
        }
        return INPUT;
        */
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        UserEntity userEntity = userService.getUserByID(userID);
        if(userEntity != null){
            request.setAttribute("UserBasicInfo", userEntity);
            request.setAttribute("GetUserSuccess", "读取用户信息成功!");
            return SUCCESS;
        }
        else{
            request.setAttribute("GetUserWrong", "读取用户信息失败!");
            return INPUT;
        }
    }
    //修改用户信息页面
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
            request.setAttribute("UpdateUserSuccess", "修改成功!");                                                //添加修改成功信息
            return SUCCESS;
        }
        request.setAttribute("UpdateUserWrong", "修改失败!");
        return INPUT;
    }
    //修改用户信息
    @SkipValidation
    public String getAllUser(){
        List<UserEntity> allUsers = userService.getAllUsers();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(allUsers.size() != 0){
            request.setAttribute("AllUsers", allUsers);
            request.setAttribute("GetAllUsersSuccess", "获取所有用户成功!");
            return SUCCESS;
        }
        else{
            request.setAttribute("GetAllUsersWrong", "查询失败!");
            return INPUT;
        }
    }
    //获取所有用户
    @SkipValidation
    public String deleteUser() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if (userService.deleteUser(userID) && userInfoService.deleteUserInfo(userID)) {
            request.setAttribute("DeleteUserSuccess", "删除用户成功!");
            return SUCCESS;
        } else {
            request.setAttribute("DeleteUserWrong", "删除用户失败!");
            return SUCCESS;
        }
    }
}
