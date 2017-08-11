package dao;

import entities.PostEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface PostDAO {
    List<PostEntity> getPostByID(String postID);
    //ͨ��postID��ѯ��������
    boolean savePost(PostEntity post);
    //��post����post����
    boolean deletePost(String postID);
    //����postIDɾ����������
    boolean deleteContent(String replyID);
    //����replyIDɾ����������
}
