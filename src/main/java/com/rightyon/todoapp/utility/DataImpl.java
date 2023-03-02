package com.rightyon.todoapp.utility;

import com.rightyon.todoapp.repository.IToDoRepository;
import com.rightyon.todoapp.repository.IUserRepository;
import com.rightyon.todoapp.repository.entity.ToDo;
import com.rightyon.todoapp.repository.entity.User;
import com.rightyon.todoapp.repository.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl {
    private final IUserRepository userRepository;
    private final IToDoRepository toDoRepository;

    @PostConstruct
    public void initData(){

        createUserAndToDo();

    }

    private void createUserAndToDo() {
        ToDo toDo1= ToDo.builder()
                .isPrivate(true)
                .status(Status.DOING)
                .title("entity operations")
                .build();
        ToDo toDo2= ToDo.builder()
                .isPrivate(false)
                .status(Status.DOING)
                .title("hibernate relations")
                .build();
        ToDo toDo3= ToDo.builder()
                .isPrivate(true)
                .status(Status.TODO)
                .title("service operations")
                .build();
        ToDo toDo4= ToDo.builder()
                .isPrivate(false)
                .status(Status.TODO)
                .title("security config")
                .build();
        ToDo toDo5= ToDo.builder()
                .isPrivate(true)
                .status(Status.DONE)
                .title("rabbit config")
                .build();
        ToDo toDo6= ToDo.builder()
                .isPrivate(false)
                .status(Status.DONE)
                .title("user service")
                .build();
        ToDo toDo7= ToDo.builder()
                .isPrivate(false)
                .status(Status.TODO)
                .title("controller operations")
                .build();
        ToDo toDo8= ToDo.builder()
                .isPrivate(true)
                .status(Status.DELETED)
                .title("database connections")
                .build();
        ToDo toDo9= ToDo.builder()
                .isPrivate(false)
                .status(Status.DELETED)
                .title("project configurations")
                .build();
        ToDo toDo10= ToDo.builder()
                .isPrivate(true)
                .status(Status.TODO)
                .title("test")
                .build();

        List<ToDo> user1ToDoList=new ArrayList<>();
        user1ToDoList.add(toDo1);
        user1ToDoList.add(toDo3);
        user1ToDoList.add(toDo9);

        List<ToDo> user2ToDoList=new ArrayList<>();
        user2ToDoList.add(toDo2);
        user2ToDoList.add(toDo4);
        user2ToDoList.add(toDo8);


        List<ToDo> user3ToDoList=new ArrayList<>();
        user3ToDoList.add(toDo5);
        user3ToDoList.add(toDo6);
        user3ToDoList.add(toDo7);
        user3ToDoList.add(toDo10);

        User user1= User.builder()
                .name("Ezgi")
                .surname("Akkaş")
                .email("ezgi@gmail.com")
                .password("Aa123456**")
                .toDoList(user1ToDoList)
                .build();
        User user2= User.builder()
                .name("Hilal")
                .surname("Er")
                .email("hilal@gmail.com")
                .password("Aa654321**")
                .toDoList(user2ToDoList)
                .build();
        User user3= User.builder()
                .name("Cagan")
                .surname("Akkas")
                .email("cagan@gmail.com")
                .password("Aa234567**")
                .toDoList(user3ToDoList)
                .build();


        toDo1.setUser(user1);
        toDo2.setUser(user2);
        toDo3.setUser(user1);
        toDo4.setUser(user2);
        toDo5.setUser(user3);
        toDo6.setUser(user3);
        toDo7.setUser(user3);
        toDo8.setUser(user2);
        toDo9.setUser(user1);
        toDo10.setUser(user3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        toDoRepository.save(toDo1);
        toDoRepository.save(toDo2);
        toDoRepository.save(toDo3);
        toDoRepository.save(toDo4);
        toDoRepository.save(toDo5);
        toDoRepository.save(toDo6);
        toDoRepository.save(toDo7);
        toDoRepository.save(toDo8);
        toDoRepository.save(toDo9);
        toDoRepository.save(toDo10);






    }
}
