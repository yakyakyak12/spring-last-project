package com.example.springlastproject.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJPARepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String uesrname);

    @Query(value = "select * from user_tb where username = :username", nativeQuery = true)
    User findByCheckUsername(@Param("username") String username);

    @Modifying
    @Query(value = "update user_tb set payment_status = false where id =:id", nativeQuery = true)
    void updatePaymentStatus(@Param("id") Integer id);
}
