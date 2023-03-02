package com.rightyon.todoapp.dto.response;

import com.rightyon.todoapp.repository.entity.ToDo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoListResponse {
    private List<ToDo> toDoList;
}
