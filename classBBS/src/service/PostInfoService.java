package service;

import entities.PostInfoEntity;

import java.util.List;

/**
 * Created by huwendi on 2017/6/9.
 */
public interface PostInfoService {
    List<PostInfoEntity> getAllPostInfo();
    //��ѯ����������Ϣ
    PostInfoEntity getPostInfoByID(String postID);
    //ͨ��postID��ѯ������Ϣ
    List<PostInfoEntity> getClassAllPostInfo(String classID);
    //��ѯĳ�γ�����������Ϣ
    boolean savePostInfo(PostInfoEntity postInfo);
    //����������Ϣ
    boolean updatePostInfo(PostInfoEntity postInfo);
    //����������Ϣ
    boolean deletePostInfo(PostInfoEntity postInfo);
    //ɾ��������Ϣ
}
