package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import entities.PostEntity;
import entities.PostInfoEntity;
import entities.UserEntity;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.PostInfoService;
import service.PostService;
import service.UserService;
import service.impl.PostInfoServiceImpl;
import service.impl.PostServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by huwendi on 2017/6/11.
 */
public class PostAction extends ActionSupport {
    private PostEntity post = new PostEntity();
    private String postID;
    private String content;
    private String deleteReplyID;
    private PostService postService = new PostServiceImpl();
    private PostInfoService postInfoService = new PostInfoServiceImpl();
    private UserService userService = new UserServiceImpl();

    public PostEntity getPost(){
        return post;
    }
    public void setPost(PostEntity post){
        this.post = post;
    }

    public String getPostID(){
        return postID;
    }
    public void setPostID(String postID){
        this.postID = postID;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getDeleteReplyID(){
        return deleteReplyID;
    }
    public void setDeleteReplyID(String deleteReplyID){
        this.deleteReplyID = deleteReplyID;
    }

    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "content", message = "�������ݲ���Ϊ��!")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "300", fieldName = "content"
                            , message = "�������ݳ�����2~300֮��!")
            }
    )
    public String replyPost(){
        post.setPostId(postID);
        UserEntity userEntity = (UserEntity) ActionContext.getContext().getSession().get("LogInUser");
        post.setReplyUserId(userEntity.getUserId());
        int floor = postService.getPostByID(postID).size() + 1;
        post.setFloor(floor + "");
        post.setReplyId(postID + "f" + floor);
        post.setContent(content);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                        //�������ڸ�ʽ
        String time = simpleDateFormat.format(new Date());
        post.setReplyDate(time);
        PostInfoEntity postInfoEntity = postInfoService.getPostInfoByID(postID);
        postInfoEntity.setUpdateDate(time);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(postInfoService.updatePostInfo(postInfoEntity) && postService.savePost(post)) {
            request.setAttribute("ReplyPostSuccess", "�����ɹ�!");
            return seePost();
        }
        else{
            request.setAttribute("ReplyPostWrong", "�ظ�����ʧ��!");
            return seePost();
        }
    }
    //����
    @SkipValidation
    public String seePost(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        List<PostEntity> postContent = postService.getPostByID(postID);
        Collections.sort(postContent);                                                                                  //ʹ¥���С��������
        PostInfoEntity postInfo = postInfoService.getPostInfoByID(postID);
        if(postContent.size() != 0 && postInfo != null) {
            String userNickName = userService.getUserByID(postInfo.getUserId()).getNickName();
            request.setAttribute("PostContent", postContent);
            request.setAttribute("PostInfo", postInfo);
            request.setAttribute("SendUserNickName", userNickName);
            return SUCCESS;
        }
        else{
            request.setAttribute("SeePostWrong", "�鿴��������ʧ��!");
            return INPUT;
        }
    }
    //�鿴��������
    @SkipValidation
    public String deleteReply(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(postService.deleteContent(deleteReplyID)){
            request.setAttribute("DeleteContentSuccess", "ɾ���ظ��ɹ�!");
            return seePost();
        }
        else{
            request.setAttribute("DeleteContentWrong", "ɾ���ظ�ʧ��!");
            return seePost();
        }
    }
    //ɾ�����ӻظ�
}
