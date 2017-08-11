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
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "title", message = "���ӱ��ⲻ��Ϊ��!"),
                    @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "content", message = "�������ݲ���Ϊ��!")
            },
            stringLengthFields = {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "20", fieldName = "title"
                            , message = "���ӱ��ⳤ����2~20֮��!"),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "2", maxLength = "300", fieldName = "content"
                            , message = "�������ݳ�����2~300֮��!")
            }
    )

    public String sendPost(){
        List<PostInfoEntity> allPostInfo = postInfoService.getAllPostInfo();
        postInfo.setPostId("p00" + (allPostInfo.size() + 1 + ""));
        postInfo.setUserId(userID);
        postInfo.setClassId(classID);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                        //�������ڸ�ʽ
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
        postEntity.setReplyId(postInfo.getPostId() + "f1");                                                             //��һ���ظ���ID��ʽΪpostID + f1
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(postInfoService.savePostInfo(postInfo) && postService.savePost(postEntity)) {
            request.setAttribute("SendPostSuccess", "�����ɹ�!");                                                  //����Ƿ����ɹ���ת���򽫷����ɹ���Ϣ����request
            return getClassPostInfoByID();
        }
        else{
            request.setAttribute("SendPostWrong", "����ʧ��!");
            return getClassPostInfoByID();
        }
    }
    //����
    @SkipValidation
    public String getClassPostInfoByID(){
        List<PostInfoEntity> classAllPostInfo = postInfoService.getClassAllPostInfo(classID);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(classAllPostInfo.size() != 0) {
            PostInfoEntity topPost = null;
            int size = classAllPostInfo.size();
            for(int i = 0; i < size; i++)
                if(classAllPostInfo.get(i).getIsTop().equals("yes")) {
                    topPost = classAllPostInfo.get(i);                                                                  //��ȡ�ö�������
                    Collections.swap(classAllPostInfo, 0, i);
                }
            if(topPost != null) {
                List<PostInfoEntity> index = new LinkedList<>();
                for (int j = 1; j < size; j++)
                    index.add(classAllPostInfo.get(j));
                Collections.sort(index);                                                                                //�����ö��������Ը���ʱ��Ϊ׼�Ӵ�С����
                classAllPostInfo.clear();
                classAllPostInfo.add(topPost);
                for (int k = 0; k < size - 1; k++)
                    classAllPostInfo.add(index.get(k));
            }
            else
                Collections.sort(classAllPostInfo);                                                                     //�����ö����������������Ѹ���ʱ��Ϊ׼�Ӵ�С����
            request.setAttribute("UserClassPostInfo", classAllPostInfo);                                             //���ÿγ���̳����������Ϣ����request
            ClassService classService = new ClassServiceImpl();
            request.setAttribute("ClassName", classService.getClassByID(classID).getClassName());                    //��������γ���̳���ִ���request
            return SUCCESS;
        }
        else {
            request.setAttribute("GetClassPosInfoWrong", "��ѯ�γ�����ʧ��!");
            return INPUT;
        }
    }
    //��ȡ�γ��������ӽ���
    @SkipValidation
    public String topPost(){
        List<PostInfoEntity> classAllPostInfo = postInfoService.getClassAllPostInfo(classID);
        PostInfoEntity oldTopPost = null;
        int size = classAllPostInfo.size();
        for(int i = 0; i < size; i++)
            if(classAllPostInfo.get(i).getIsTop().equals("yes"))
                oldTopPost = classAllPostInfo.get(i);                                                                   //��ȡ���ö���
        if(oldTopPost != null) {
            oldTopPost.setIsTop("no");
            postInfoService.updatePostInfo(oldTopPost);
            PostInfoEntity newTopPost = postInfoService.getPostInfoByID(topPostID);
            newTopPost.setIsTop("yes");
            postInfoService.updatePostInfo(newTopPost);
        }                                                                                                               //���о��ö����������޸ľ��ö�������,���޸����ö�������
        else {
            PostInfoEntity newTopPost = postInfoService.getPostInfoByID(topPostID);
            newTopPost.setIsTop("yes");
            postInfoService.updatePostInfo(newTopPost);
        }                                                                                                               //���޾��ö�������ֱ���޸����ö���isTop����
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        request.setAttribute("TopPostSuccess", "�ö��ɹ�!");
        return getClassPostInfoByID();
    }
    //�����ö�
    @SkipValidation
    public String cancelTop(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        PostInfoEntity postInfoEntity = postInfoService.getPostInfoByID(cancelTopPostID);
        if(postInfoEntity.getIsTop().equals("yes")){
            postInfoEntity.setIsTop("no");
            postInfoService.updatePostInfo(postInfoEntity);
            request.setAttribute("CancelTopSuccess", "ȡ���ö��ɹ�!");
            return getClassPostInfoByID();
        }
        else{
            request.setAttribute("CancelTopWrong", "�����Ӳ�δ�ö�,����Ҫȡ��!");
            return getClassPostInfoByID();
        }
    }
    //ȡ���ö�

    @SkipValidation
    public String deletePost(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        if(postInfoService.deletePostInfo(postInfoService.getPostInfoByID(deletePostID)) && postService.deletePost(deletePostID)){
            request.setAttribute("DeletePostSuccess", "ɾ�����ӳɹ�!");
            return getClassPostInfoByID();
        }
        else {
            request.setAttribute("DeletePostWrong", "ɾ������ʧ��!");
            return getClassPostInfoByID();
        }
    }
    //
}
