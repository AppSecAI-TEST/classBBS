package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.*;
import entities.UserInfoEntity;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.UserInfoService;
import service.impl.UserInfoServiceImpl;

/**
 * Created by huwendi on 2017/6/2.
 */
public class UserInfoAction extends ActionSupport {
    private UserInfoService userInfoService = new UserInfoServiceImpl();
    private UserInfoEntity userInfo = new UserInfoEntity();

    private static String userId;                                                                                       //保存注册页面传过来的userId
    public UserInfoEntity getUserInfo(){
        return userInfo;
    }
    public void setUserInfo(UserInfoEntity userInfo){
        this.userInfo = userInfo;
    }


    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "userInfo.userName", message = "真实姓名不能为空!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "userInfo.userPhone", message = "手机号不能为空!")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "1", maxLength = "5", fieldName = "userInfo.userName"
                    , message = "用户姓名在1~5位之间!"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "11", fieldName = "userInfo.userPhone"
                    , message = "手机号在2~11位之间!")
            },
            emails = {
                    @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "userInfo.userEmail", message = "请输入合法Email!")
            }
    )
    public String saveUserInfo(){
        userInfo.setUserId(userId);
        if (userInfoService.saveUserInfo(userInfo))
            return SUCCESS;
        else
            return INPUT;
    }
    @SkipValidation
    public String execute(){
        userId = userInfo.getUserId();                                                                                  //保存注册页面传过来的userId
        return INPUT;
    }
}
