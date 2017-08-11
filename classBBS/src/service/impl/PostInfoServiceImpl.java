package service.impl;

import dao.PostInfoDAO;
import dao.impl.PostInfoDAOImpl;
import entities.PostInfoEntity;
import service.PostInfoService;

import java.util.List;

/**
 * Created by huwendi on 2017/6/9.
 */
public class PostInfoServiceImpl implements PostInfoService {
    private PostInfoDAO postInfoDAO = new PostInfoDAOImpl();
    public List<PostInfoEntity> getAllPostInfo(){
        return postInfoDAO.getAllPostInfo();
    }
    //查询所有帖子信息
    public PostInfoEntity getPostInfoByID(String postID){
        return postInfoDAO.getPostInfoByID(postID);
    }
    //通过postID查询帖子信息
    public List<PostInfoEntity> getClassAllPostInfo(String classID){
        return postInfoDAO.getClassAllPostInfo(classID);
    }
    //查询某课程所有帖子信息
    public boolean savePostInfo(PostInfoEntity postInfo){
        return postInfoDAO.savePostInfo(postInfo);
    }
    //保存帖子信息
    public boolean updatePostInfo(PostInfoEntity postInfo){
        return postInfoDAO.updatePostInfo(postInfo);
    }
    //更新帖子信息
    public boolean deletePostInfo(PostInfoEntity postInfo){
        return postInfoDAO.deletePostInfo(postInfo);
    }
    //删除帖子信息
}
