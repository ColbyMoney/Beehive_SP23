package com.example.ProjectBeehive.repository;

import com.example.ProjectBeehive.entity.Post;
//import com.example.ProjectBeehive.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, BigInteger> {
    List<Post> findByUser_IDOrderByCreatedAtDesc(BigInteger USER_ID);

/*    @Query("SELECT NEW com.example.ProjectBeehive.entity.PostWithCommentsDto(p.POSTS_ID, p.user_id, p.image, p.createdAt, c.id, c.commenterId, c.posterId, c.postId, c.dateTime, c.edited, c.commentText) FROM Post p JOIN Friend f ON p.user_id = f.friendId LEFT JOIN Comment c ON p.POSTS_ID = c.postId WHERE p.user_id IN :friendIds AND f.status = 'ACCEPTED' ORDER BY p.createdAt DESC, c.dateTime ASC")
    List<Post> findByUserId(@Param("friendIds") List<BigInteger> friendIds);*/

    /*
    public List<Friend> findAll();

    public void upload();

    public void deleteById();

    public void deleteAll();

    public void downloadAllFriendPost();
*/
}
