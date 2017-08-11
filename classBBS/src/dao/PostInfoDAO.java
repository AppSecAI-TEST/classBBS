package dao;

import entities.PostInfoEntity;
import service.PostInfoService;

import java.util.List;

/**
 * Created by huwendi on 2017/5/23.
 */
public interface PostInfoDAO {
    List<PostInfoEntity> getAllPostInfo();
    //��ѯ����������Ϣ
    PostInfoEntity getPostInfoByID(String postID);
    //ͨ��postID��ѯ������Ϣ
    List<PostInfoEntity> getClassAllPostInfo(String classID);
    //ͨ��classID��ѯ�ÿγ�����������Ϣ
    boolean savePostInfo(PostInfoEntity postInfo);
    //��postInfo����postInfo����
    boolean updatePostInfo(PostInfoEntity postInfo);
    //�������ݿ�postInfo
    boolean deletePostInfo(PostInfoEntity postInfo);
    //ɾ�����ݿ�postInfo
}
