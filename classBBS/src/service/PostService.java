package service;

import entities.PostEntity;
import javafx.geometry.Pos;

import java.util.List;

/**
 * Created by huwendi on 2017/6/10.
 */
public interface PostService {
    List<PostEntity> getPostByID(String postID);
    //获取帖子内容
    boolean savePost(PostEntity post);
    //保存帖子内容
    boolean deletePost(String postID);
    //删除帖子内容
    boolean deleteContent(String replyID);
    //删除帖子内容
}
