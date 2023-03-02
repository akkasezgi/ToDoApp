package com.rightyon.todoapp.service;

import com.rightyon.todoapp.dto.request.CreateUserRequestDto;
import com.rightyon.todoapp.dto.request.LoginRequestDto;
import com.rightyon.todoapp.dto.request.UpdateRequestDto;
import com.rightyon.todoapp.dto.response.LoginResponseDto;
import com.rightyon.todoapp.dto.response.ToDoListResponse;
import com.rightyon.todoapp.exception.ErrorType;
import com.rightyon.todoapp.exception.ToDoAppException;
import com.rightyon.todoapp.repository.IToDoRepository;
import com.rightyon.todoapp.repository.IUserRepository;
import com.rightyon.todoapp.repository.entity.ToDo;
import com.rightyon.todoapp.repository.entity.User;
import com.rightyon.todoapp.repository.enums.Status;
import com.rightyon.todoapp.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final IToDoRepository todoRepository;
    private final JwtTokenManager jwtTokenManager;

    public boolean createUser(CreateUserRequestDto dto) {
        try {
            userRepository.save(User.builder()
                    .email(dto.getEmail())
                    .surname(dto.getSurname())
                    .password(dto.getPassword())
                    .name(dto.getName())
                    .build());

        } catch (Exception e) {
            throw new ToDoAppException(ErrorType.USER_NOT_CREATED);
        }
        return true;
    }

    public LoginResponseDto doLogin(LoginRequestDto dto) {
        Optional<User> user = userRepository.findOptionalByEmail(dto.getEmail());
        if (user.isEmpty()) {
            throw new ToDoAppException(ErrorType.USER_NOT_FOUND);
        }
        if (!user.get().getPassword().equals(dto.getPassword())) {
            throw new ToDoAppException(ErrorType.LOGIN_ERROR);
        }
        return LoginResponseDto.builder()
                .token(jwtTokenManager.createToken(user.get().getId()).get())
                .build();
    }

    public boolean updateUser(UpdateRequestDto dto, String token) {
        Optional<Long> id = jwtTokenManager.getByIdFromToken(token);
        Optional<User> user = userRepository.findOptionalById(id.get());
        if (id.isEmpty()) {
            throw new ToDoAppException(ErrorType.VALUE_NOT_FOUND_IN_USERS);
        }
        if (user.isEmpty()) {
            throw new ToDoAppException(ErrorType.USER_NOT_FOUND);
        }
        try {

            user.get().setPassword(dto.getPassword());
            user.get().setSurname(dto.getSurname());
            userRepository.save(user.get());
        } catch (Exception e) {
            throw new ToDoAppException(ErrorType.USER_NOT_UPDATED);
        }
        return true;
    }


    public Boolean deleteUser(String token) {
        Optional<Long> id = jwtTokenManager.getByIdFromToken(token);
        Optional<User> user = userRepository.findOptionalById(id.get());

        if (id.isEmpty()) {
            throw new ToDoAppException(ErrorType.VALUE_NOT_FOUND_IN_USERS);
        }
        if (user.isEmpty()) {
            throw new ToDoAppException(ErrorType.USER_NOT_FOUND);        }
        try {
            userRepository.delete(user.get());
        } catch (Exception e) {
            throw new ToDoAppException(ErrorType.USER_NOT_DELETED);
        }
        return true;
    }


    public ToDoListResponse updateToDoByStatus(String token, Status status, Long todoId) {
        Optional<Long> userid = jwtTokenManager.getByIdFromToken(token);
        Optional<User> user = userRepository.findOptionalById(userid.get());

        if (userid.isEmpty()) {
            throw new ToDoAppException(ErrorType.VALUE_NOT_FOUND_IN_USERS);
        }
        if (user.isEmpty()) {
            throw new ToDoAppException(ErrorType.USER_NOT_FOUND);        }
        Optional<ToDo> toDo = todoRepository.findOptionalById(todoId);

        if (toDo.get().getUser().getId() != userid.get()) {
            throw new ToDoAppException(ErrorType.TODO_NOT_FOUND);
        }
        try {
            toDo.get().setStatus(status);
            todoRepository.save(toDo.get());
        } catch (Exception e) {
            throw new ToDoAppException(ErrorType.TODO_NOT_UPDATED);
        }
        return ToDoListResponse.builder()
                .toDoList(user.get().getToDoList())
                .build();
    }

    public List<ToDo> getAllToDo() {
        List<ToDo> toDoList = todoRepository.findAll().stream().filter(toDo -> toDo.isPrivate()).collect(Collectors.toList());
        return toDoList;
    }

    public List<ToDo> getAllMyToDo(String token) {
        Optional<Long> userid = jwtTokenManager.getByIdFromToken(token);
        if (userid.isEmpty()) {
            throw new ToDoAppException(ErrorType.VALUE_NOT_FOUND_IN_USERS);
        }
        Optional<User> user = userRepository.findOptionalById(userid.get());
        if (user.isEmpty()) {
            throw new ToDoAppException(ErrorType.USER_NOT_FOUND);
        }
        return todoRepository.findAll().stream().filter(todo -> todo.getUser().getId() == userid.get() && !todo.getStatus().equals(Status.DELETED)).collect(Collectors.toList());

    }
}

