package com.example.ProjectBeehive.repository;

import com.example.ProjectBeehive.entity.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, BigInteger> {

    /*
    public Page<Friend> findAll(Pageable pageable);

    public Page<Friend> findSuggested(Pageable pageable);

    public void findCurrentPost();

    public void removeFriend();

    public void addFriend();

    public void findByID();
     */

}
