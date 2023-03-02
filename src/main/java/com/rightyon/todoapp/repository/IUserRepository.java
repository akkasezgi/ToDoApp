package com.rightyon.todoapp.repository;

import com.rightyon.todoapp.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findOptionalByEmail(String email);

    Optional<User> findOptionalById(Long id);
}
