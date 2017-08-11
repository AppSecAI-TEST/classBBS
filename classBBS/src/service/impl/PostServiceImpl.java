package service.impl;

import dao.PostDAO;
import dao.impl.PostDAOImpl;
import entities.PostEntity;
import service.PostService;

import java.util.List;

/**
 * Created by huwendi on 2017/6/10.
 */
public class PostServiceImpl implements PostService {
    private PostDAO postDAO = new PostDAOImpl();
    public List<PostEntity> getPostByID(String postID){
        return postDAO.getPostByID(postID);
    }
    //��ȡ��������
    public boolean savePost(PostEntity post){
        return postDAO.savePost(post);
    }
    //������������
    public boolean deletePost(String postID){
        return postDAO.deletePost(postID);
    }
    //ɾ����������������
    public boolean deleteContent(String replyID){
        return postDAO.deleteContent(replyID);
    }
    //ɾ����������
}
