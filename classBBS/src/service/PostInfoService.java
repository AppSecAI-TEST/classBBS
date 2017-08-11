package service;

import entities.PostInfoEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/9.
 */
public interface PostInfoService {
    List<PostInfoEntity> getAllPostInfo();
    //查询所有帖子信息
    PostInfoEntity getPostInfoByID(String postID);
    //通过postID查询帖子信息
    List<PostInfoEntity> getClassAllPostInfo(String classID);
    //查询某课程所有帖子信息
    boolean savePostInfo(PostInfoEntity postInfo);
    //保存帖子信息
    boolean updatePostInfo(PostInfoEntity postInfo);
    //更新帖子信息
    boolean deletePostInfo(PostInfoEntity postInfo);
    //删除帖子信息
}
