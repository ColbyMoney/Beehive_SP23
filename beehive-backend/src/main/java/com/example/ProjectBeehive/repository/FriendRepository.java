/*
package com.example.ProjectBeehive.repository;

import com.example.ProjectBeehive.entity.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, BigInteger> {

    public Page<Friend> findAll(Pageable pageable);

    //@Query("SELECT f FROM Friend f WHERE ...") // Replace "..." with your custom query condition
    //Page<Friend> findSuggested(Pageable pageable);

    Friend findByUserIdAndFriendId(BigInteger U_ID, BigInteger F_ID);

    Optional<Friend> findOptionalByUserIdAndFriendId(BigInteger U_ID, BigInteger F_ID);

    List<Friend> findByUserId(BigInteger U_ID);
}
*/
