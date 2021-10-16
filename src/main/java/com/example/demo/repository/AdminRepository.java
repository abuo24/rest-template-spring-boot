package com.example.demo.repository;// Author - Orifjon Yunusjonov
// t.me/coderr24

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<User> findByUsername(String username);
//    @Query(nativeQuery=true,
//            value="select u.fullname as fullName from users")
//    List<UserPayloads> findByIdToPayload();
}
