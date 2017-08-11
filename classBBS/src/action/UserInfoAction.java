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

    private static String userId;                                                                                       //����ע��ҳ�洫������userId
    public UserInfoEntity getUserInfo(){
        return userInfo;
    }
    public void setUserInfo(UserInfoEntity userInfo){
        this.userInfo = userInfo;
    }


    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "userInfo.userName", message = "��ʵ��������Ϊ��!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "userInfo.userPhone", message = "�ֻ��Ų���Ϊ��!")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "1", maxLength = "5", fieldName = "userInfo.userName"
                    , message = "�û�������1~5λ֮��!"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "11", fieldName = "userInfo.userPhone"
                    , message = "�ֻ�����2~11λ֮��!")
            },
            emails = {
                    @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "userInfo.userEmail", message = "������Ϸ�Email!")
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
        userId = userInfo.getUserId();                                                                                  //����ע��ҳ�洫������userId
        return INPUT;
    }
}
