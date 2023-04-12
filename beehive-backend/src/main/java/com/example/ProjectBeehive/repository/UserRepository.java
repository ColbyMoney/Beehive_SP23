package com.example.ProjectBeehive.repository;

import com.example.ProjectBeehive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {

    Page<User> findAll(Pageable pageable);

    User save(User user);

    long count();

    //User login(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.ID = :id")
    void deleteById(@Param("id") BigInteger id);

    void delete(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u")
    void deleteAllById(Iterable<? extends BigInteger> ids);

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.ID = :id")
    boolean existsById(@Param("id") BigInteger ID);

}
