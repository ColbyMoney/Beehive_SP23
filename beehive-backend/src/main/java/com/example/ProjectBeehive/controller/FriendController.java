/*
package com.example.ProjectBeehive.controller;

import com.example.ProjectBeehive.entity.Friend;
import com.example.ProjectBeehive.service.impl.FriendServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    FriendServiceImpl friendServiceImpl;

    @GetMapping("/findFriends")
    public Page<Friend> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return friendServiceImpl.findAll(pageable);
    }

*/
/*    @GetMapping("/getFriendPosts")
    public List<PostWithCommentsDto> getFriendPosts(@RequestParam String username) {
        return friendServiceImpl.getFriendPosts(username);
    }*//*


    @PostMapping("/request")
    public ResponseEntity<Void> requestFriend(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> acceptRequest(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addFriend(@RequestParam String username, @RequestParam String friendName) {
        friendServiceImpl.addFriend(username, friendName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFriend(@RequestParam String username, @RequestParam String friend_username) {
        friendServiceImpl.removeFriend(username, friend_username);
        return ResponseEntity.ok().build();
    }

}
*/
