package entities;

import javax.persistence.*;

/**
 * Created by huwendi on 2017/5/23.
 */
@Entity
@Table(name = "post", schema = "bbs", catalog = "")
public class PostEntity implements Comparable<PostEntity> {
    private String replyId;
    private String postId;
    private String floor;
    private String content;
    private String replyUserId;
    private String replyDate;

    @Id
    @Column(name = "replyID", nullable = false, length = 11)
    public String getReplyId(){
        return replyId;
    }
    public void setReplyId(String replyId){
        this.replyId = replyId;
    }

    @Basic
    @Column(name = "postID", nullable = false, length = 11)
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "floor", nullable = false, length = 11)
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 300)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "replyUserID", nullable = false, length = 11)
    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    @Basic
    @Column(name = "replyDate", nullable = false, length = 50)
    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate){
        this.replyDate = replyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (postId != null ? !postId.equals(that.postId) : that.postId != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (replyUserId != null ? !replyUserId.equals(that.replyUserId) : that.replyUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (replyUserId != null ? replyUserId.hashCode() : 0);
        return result;
    }
    @Override
    public String toString(){
        return "Post replyID: " + replyId +
                " postID: " + postId +
                " floor: " + floor +
                " replyUserID: " + replyUserId +
                " replyDate: " + replyDate;
    }
    @Override
    public int compareTo(PostEntity postEntity){
        return Integer.parseInt(this.floor) - Integer.parseInt(postEntity.floor);
    }
    //使回复以楼层从小到大排序

}
