package dao;

import entities.PostInfoEntity;
import service.PostInfoService;

import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface PostInfoDAO {
    List<PostInfoEntity> getAllPostInfo();
    //查询所有帖子信息
    PostInfoEntity getPostInfoByID(String postID);
    //通过postID查询帖子信息
    List<PostInfoEntity> getClassAllPostInfo(String classID);
    //通过classID查询该课程所有帖子信息
    boolean savePostInfo(PostInfoEntity postInfo);
    //将postInfo插入postInfo表中
    boolean updatePostInfo(PostInfoEntity postInfo);
    //更新数据库postInfo
    boolean deletePostInfo(PostInfoEntity postInfo);
    //删除数据库postInfo
}
