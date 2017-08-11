package dao;

import entities.PostEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface PostDAO {
    List<PostEntity> getPostByID(String postID);
    //通过postID查询帖子内容
    boolean savePost(PostEntity post);
    //将post插入post表中
    boolean deletePost(String postID);
    //根据postID删除帖子内容
    boolean deleteContent(String replyID);
    //根据replyID删除帖子内容
}
