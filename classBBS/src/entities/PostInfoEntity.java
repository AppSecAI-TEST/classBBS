package entities;

import javax.persistence.*;


/**
 * Created by huwendi on 2017/5/23.
 */
@Entity
@Table(name = "postInfo", schema = "bbs", catalog = "")
public class PostInfoEntity implements Comparable<PostInfoEntity>{
    private String postId;
    private String classId;
    private String userId;
    private String sendDate;
    private String updateDate;
    private String isTop;
    private String title;

    @Id
    @Column(name = "postID", nullable = false, length = 11)
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "classID", nullable = false, length = 11)
    public String getClassId(){
        return classId;
    }
    public void setClassId(String classId){
        this.classId = classId;
    }

    @Basic
    @Column(name = "userID", nullable = false, length = 11)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "sendDate", nullable = false, length = 50)
    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    @Basic
    @Column(name = "updateDate", nullable = false, length = 50)
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "isTop", nullable = false, length = 11)
    public String getIsTop(){
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 20)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostInfoEntity that = (PostInfoEntity) o;

        if (postId != null ? !postId.equals(that.postId) : that.postId != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (sendDate != null ? !sendDate.equals(that.sendDate) : that.sendDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (isTop != null ? !isTop.equals(that.isTop) : that.isTop != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (isTop != null ? isTop.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostInfo postID: " + postId +
                " classID: " + classId +
                " userID: " + userId +
                " sendDate: " + sendDate +
                " updateDate: " + updateDate +
                " isTop: " + isTop +
                " title: " + title ;
    }
    @Override
    public int compareTo(PostInfoEntity postInfoEntity){
        return postInfoEntity.updateDate.compareTo(this.updateDate);
    }
    //显示帖子以更新时间为准从大到小
}
