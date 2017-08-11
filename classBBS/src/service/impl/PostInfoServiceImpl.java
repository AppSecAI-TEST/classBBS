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
    //��ѯ����������Ϣ
    public PostInfoEntity getPostInfoByID(String postID){
        return postInfoDAO.getPostInfoByID(postID);
    }
    //ͨ��postID��ѯ������Ϣ
    public List<PostInfoEntity> getClassAllPostInfo(String classID){
        return postInfoDAO.getClassAllPostInfo(classID);
    }
    //��ѯĳ�γ�����������Ϣ
    public boolean savePostInfo(PostInfoEntity postInfo){
        return postInfoDAO.savePostInfo(postInfo);
    }
    //����������Ϣ
    public boolean updatePostInfo(PostInfoEntity postInfo){
        return postInfoDAO.updatePostInfo(postInfo);
    }
    //����������Ϣ
    public boolean deletePostInfo(PostInfoEntity postInfo){
        return postInfoDAO.deletePostInfo(postInfo);
    }
    //ɾ��������Ϣ
}
