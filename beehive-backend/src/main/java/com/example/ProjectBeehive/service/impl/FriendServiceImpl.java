/*
package com.example.ProjectBeehive.service.impl;

import com.azure.storage.file.share.implementation.FilesImpl;
import com.example.ProjectBeehive.entity.*;
import com.example.ProjectBeehive.repository.PostRepository;
import com.example.ProjectBeehive.repository.FriendRepository;
import com.example.ProjectBeehive.repository.CommentRepository;
import com.example.ProjectBeehive.repository.UserRepository;
import com.example.ProjectBeehive.service.FriendService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    public FriendServiceImpl(FriendRepository friendRepository, UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository; // Add this line
    }

    public Page<Friend> findAll(Pageable pageable) {
        return friendRepository.findAll(pageable);
    }

   */
/* public Page<Friend> findSuggested(Pageable pageable) {
        // Add your custom logic to suggest friends
        return friendRepository.findSuggested(pageable);
    }*//*


    public void removeFriend(String username, String friend_username) {
        Friend friend = friendRepository.findByUserIdAndFriendId(userRepository.findIdByUsername(username), userRepository.findIdByUsername(friend_username));
        if (friend != null) {
            friendRepository.delete(friend);
        }
    }

    public void addFriend(String username, String friend_username) {
        User user = userRepository.findUserById(userRepository.findIdByUsername(username));
        User friendUser = userRepository.findUserById(userRepository.findIdByUsername(friend_username));

        if (user != null && friendUser != null) {
            Friend friend = new Friend();
            friend.setUserId(userRepository.findIdByUsername(username));
            friend.setFriendId(userRepository.findIdByUsername(friend_username));
            friendRepository.save(friend);
        }
    }

    public Friend findById(String username) {
        return friendRepository.findById(userRepository.findIdByUsername(username)).orElse(null);
    }

*/
/*    public List<CommentDto> getFriendPosts(String username) {

    }*//*


    */
/*private List<PostWithCommentsDto> combinePostsAndComments(List<Post> friendPosts, List<Comment> postComments) {
        List<PostWithCommentsDto> result = new ArrayList<>();

        for (Post post : friendPosts) {
            PostWithCommentsDto postDto = new PostWithCommentsDto(post.getPOSTS_ID(), post.getUser_id(), post.getImage(), post.getCreatedAt(),
                    null, null, null, null, null, null, null);
            result.add(postDto);

            postComments.stream()
                    .filter(comment -> comment.getPostId().equals(post.getPOSTS_ID()))
                    .forEach(comment -> {
                        PostWithCommentsDto commentDto = new PostWithCommentsDto(null, null, null, null,
                                comment.getId(), comment.getCommenterId(), comment.getPosterId(), comment.getPostId(),
                                comment.getDateTime(), comment.getEdited(), comment.getCommentText());
                        result.add(commentDto);
                    });
        }

        return result;
    }*//*


    public boolean requestExists(String username, String friendUsername) {
        BigInteger userId = userRepository.findIdByUsername(username);
        BigInteger friendId = userRepository.findIdByUsername(friendUsername);

        if (userId == null || friendId == null) {
            return false;
        }

        Optional<Friend> existingRequest = friendRepository.findOptionalByUserIdAndFriendId(userId, friendId);
        return existingRequest.isPresent();
    }

}
*/
