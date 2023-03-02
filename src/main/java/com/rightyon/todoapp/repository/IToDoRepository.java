package com.rightyon.todoapp.repository;

import com.rightyon.todoapp.repository.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IToDoRepository extends JpaRepository<ToDo,Long> {
    Optional<ToDo> findOptionalById(Long id);
}
