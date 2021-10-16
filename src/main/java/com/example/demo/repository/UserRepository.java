package com.example.demo.repository;// Author - Orifjon Yunusjonov
// t.me/coderr24

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.User;
import com.example.demo.payload.UserPayload;
import com.example.demo.payload.UserPayloads;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
//    @Query(nativeQuery=true,
//            value="select u.fullname as fullName from users")
//    List<UserPayloads> findByIdToPayload();
}
