package com.example.ProjectBeehive.repository;

import com.example.ProjectBeehive.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<Friend, BigInteger> {

    /*
    public List<Friend> findAll();

    public void upload();

    public void deleteById();

    public void deleteAll();

    public void downloadAllFriendPost();
*/
}
