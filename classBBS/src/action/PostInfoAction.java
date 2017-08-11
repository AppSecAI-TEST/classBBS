package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import entities.ClazzEntity;
import entities.PostEntity;
import entities.PostInfoEntity;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.ClassService;
import service.PostInfoService;
import service.PostService;
import service.impl.ClassServiceImpl;
import service.impl.PostInfoServiceImpl;
import service.impl.PostServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by huwendi on 2017/6/4.
 */
public class PostInfoAction extends ActionSupport {
    private PostInfoEntity postInfo = new PostInfoEntity();
    private PostInfoService postInfoService = new PostInfoServiceImpl();
    private PostService postService = new PostServiceImpl();
    private String topPostID;
    private String cancelTopPostID;
    private String deletePostID;
    private String classID;
    private String userID;
    private String title;
    private String content;

    public String getUserID(){
        return userID;
    }
    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getClassID(){
        return classID;
    }
    public void setClassID(String classID){
        this.classID = classID;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getTopPostID(){
        return topPostID;
    }
    public void setTopPostID(String topPostID){
        this.topPostID = topPostID;
    }

    public String getCancelTopPostID(){
        return cancelTopPostID;
    }
    public void setCancelTopPostID(String cancelTopPostID){
        this.cancelTopPostID = cancelTopPostID;
    }

    public String getDeletePostID(){
        return deletePostID;
    }
    public void setDeletePostID(String deletePostID){
        this.deletePostID = deletePostID;
    }

    @Validations(
            requiredStrings = {
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "title", message = "帖子标题不能为空!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "content", message = "帖子内容不能为空!")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "20", fieldName = "title"
                            , message = "帖子标题长度在2~20之间!"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "300", fieldName = "content"
                            , message = "帖子内容长度在2~300之间!")
            }
    )

    public String sendPost(){
        List<PostInfoEntity> allPostInfo = postInfoService.getAllPostInfo();
        postInfo.setPostId("p00" + (allPostInfo.size() + 1 + ""));
        postInfo.setUserId(userID);
        postInfo.setClassId(classID);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                        //设置日期格式
        String time = simpleDateFormat.format(new Date());
        postInfo.setSendDate(time);
        postInfo.setUpdateDate(time);
        postInfo.setIsTop("no");
        postInfo.setTitle(title);
        PostEntity postEntity = new PostEntity();
        postEntity.setPostId(postInfo.getPostId());
        postEntity.setFloor("1");
        postEntity.setReplyDate(time);
        postEntity.setReplyUserId(postInfo.getUserId());
        postEntity.setContent(content);
        postEntity.setReplyId(postInfo.getPostId() + "f1");                                                             //第一个回复的ID格式为postID + f1
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(postInfoService.savePostInfo(postInfo) && postService.savePost(postEntity)) {
            request.setAttribute("SendPostSuccess", "发帖成功!");                                                  //如果是发帖成功跳转，则将发帖成功信息存入request
            return getClassPostInfoByID();
        }
        else{
            request.setAttribute("SendPostWrong", "发帖失败!");
            return getClassPostInfoByID();
        }
    }
    //发帖
    @SkipValidation
    public String getClassPostInfoByID(){
        List<PostInfoEntity> classAllPostInfo = postInfoService.getClassAllPostInfo(classID);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(classAllPostInfo.size() != 0) {
            PostInfoEntity topPost = null;
            int size = classAllPostInfo.size();
            for(int i = 0; i < size; i++)
                if(classAllPostInfo.get(i).getIsTop().equals("yes")) {
                    topPost = classAllPostInfo.get(i);                                                                  //获取置顶的帖子
                    Collections.swap(classAllPostInfo, 0, i);
                }
            if(topPost != null) {
                List<PostInfoEntity> index = new LinkedList<>();
                for (int j = 1; j < size; j++)
                    index.add(classAllPostInfo.get(j));
                Collections.sort(index);                                                                                //将非置顶的帖子以更新时间为准从大到小排序
                classAllPostInfo.clear();
                classAllPostInfo.add(topPost);
                for (int k = 0; k < size - 1; k++)
                    classAllPostInfo.add(index.get(k));
            }
            else
                Collections.sort(classAllPostInfo);                                                                     //若无置顶帖，则将所有帖子已更新时间为准从大到小排序
            request.setAttribute("UserClassPostInfo", classAllPostInfo);                                             //将该课程论坛所有帖子信息存入request
            ClassService classService = new ClassServiceImpl();
            request.setAttribute("ClassName", classService.getClassByID(classID).getClassName());                    //将所浏览课程论坛名字存入request
            return SUCCESS;
        }
        else {
            request.setAttribute("GetClassPosInfoWrong", "查询课程帖子失败!");
            return INPUT;
        }
    }
    //获取课程所有帖子界面
    @SkipValidation
    public String topPost(){
        List<PostInfoEntity> classAllPostInfo = postInfoService.getClassAllPostInfo(classID);
        PostInfoEntity oldTopPost = null;
        int size = classAllPostInfo.size();
        for(int i = 0; i < size; i++)
            if(classAllPostInfo.get(i).getIsTop().equals("yes"))
                oldTopPost = classAllPostInfo.get(i);                                                                   //获取旧置顶帖
        if(oldTopPost != null) {
            oldTopPost.setIsTop("no");
            postInfoService.updatePostInfo(oldTopPost);
            PostInfoEntity newTopPost = postInfoService.getPostInfoByID(topPostID);
            newTopPost.setIsTop("yes");
            postInfoService.updatePostInfo(newTopPost);
        }                                                                                                               //若有旧置顶帖，则先修改旧置顶帖属性,再修改新置顶帖属性
        else {
            PostInfoEntity newTopPost = postInfoService.getPostInfoByID(topPostID);
            newTopPost.setIsTop("yes");
            postInfoService.updatePostInfo(newTopPost);
        }                                                                                                               //若无旧置顶帖，则直接修改新置顶帖isTop属性
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        request.setAttribute("TopPostSuccess", "置顶成功!");
        return getClassPostInfoByID();
    }
    //帖子置顶
    @SkipValidation
    public String cancelTop(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        PostInfoEntity postInfoEntity = postInfoService.getPostInfoByID(cancelTopPostID);
        if(postInfoEntity.getIsTop().equals("yes")){
            postInfoEntity.setIsTop("no");
            postInfoService.updatePostInfo(postInfoEntity);
            request.setAttribute("CancelTopSuccess", "取消置顶成功!");
            return getClassPostInfoByID();
        }
        else{
            request.setAttribute("CancelTopWrong", "该帖子并未置顶,不需要取消!");
            return getClassPostInfoByID();
        }
    }
    //取消置顶

    @SkipValidation
    public String deletePost(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(postInfoService.deletePostInfo(postInfoService.getPostInfoByID(deletePostID)) && postService.deletePost(deletePostID)){
            request.setAttribute("DeletePostSuccess", "删除帖子成功!");
            return getClassPostInfoByID();
        }
        else {
            request.setAttribute("DeletePostWrong", "删除帖子失败!");
            return getClassPostInfoByID();
        }
    }
    //
}
