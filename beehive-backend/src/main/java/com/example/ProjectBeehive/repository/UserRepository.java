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

    //User save(User user);

    long count();

    @Query("Select u FROM User u WHERE u.password = :password and u.username = :username")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.ID = :id")
    void deleteById(@Param("id") BigInteger id);

    void deleteByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u")
    void deleteAllById(Iterable<? extends BigInteger> ids);

    @Transactional
    @Query("SELECT u.ID FROM User u WHERE u.username = :username")
    BigInteger findIdByUsername(String username);

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByEmail(String Email);
    Optional<User> findByRole(String role);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.ID = :id")
    boolean existsById(@Param("id") BigInteger ID);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
    void changePassword(@Param("username") String username, @Param("password") String password);

    User findUserByID(BigInteger ID);
}
