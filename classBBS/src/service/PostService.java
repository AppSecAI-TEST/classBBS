package service;

import entities.PostEntity;
import javafx.geometry.Pos;

import java.util.List;

/**
 * Created by huwendi on 2017/6/10.
 */
public interface PostService {
    List<PostEntity> getPostByID(String postID);
    //��ȡ��������
    boolean savePost(PostEntity post);
    //������������
    boolean deletePost(String postID);
    //ɾ����������
    boolean deleteContent(String replyID);
    //ɾ����������
}
