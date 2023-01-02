package com.example.linecloneback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.linecloneback.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

}
