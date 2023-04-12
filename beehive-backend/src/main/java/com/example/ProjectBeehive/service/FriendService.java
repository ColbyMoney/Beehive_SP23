package com.example.ProjectBeehive.service;

import com.example.ProjectBeehive.entity.Friend;
import com.example.ProjectBeehive.repository.FriendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    FriendRepository friendRepository;

    public Page<Friend> findAll(Pageable pageable) {
        return (Page<Friend>) friendRepository.findAll();
    }
}
