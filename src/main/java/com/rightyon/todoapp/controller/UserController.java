package com.rightyon.todoapp.controller;

import com.rightyon.todoapp.dto.request.CreateUserRequestDto;
import com.rightyon.todoapp.dto.request.LoginRequestDto;
import com.rightyon.todoapp.dto.request.UpdateRequestDto;
import com.rightyon.todoapp.dto.response.LoginResponseDto;
import com.rightyon.todoapp.dto.response.ToDoListResponse;
import com.rightyon.todoapp.repository.entity.ToDo;
import com.rightyon.todoapp.repository.enums.Status;
import com.rightyon.todoapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.rightyon.todoapp.constants.ApiUrl.*;
@RestController
@RequestMapping(USER)
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(CREATEUSER)
    @Operation(summary = "Kullanici olusturulan method")
    public ResponseEntity<Boolean> createUser(@RequestBody @Valid CreateUserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping(DOLOGIN)
    @Operation(summary = "Kullanici girisi yapilan method")
    public ResponseEntity<LoginResponseDto> doLogin(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(userService.doLogin(dto));
    }

    @PutMapping(UPDATE)
    @Operation(summary = "Kullanici guncellenen method")
    public ResponseEntity<Boolean> updateUser(@RequestBody @Valid UpdateRequestDto dto, @RequestHeader String token){
        return ResponseEntity.ok(userService.updateUser(dto,token));
    }

    @DeleteMapping(DELETE)
    @Operation(summary = "Kullanici silinen method")
    public ResponseEntity<Boolean> deleteUser(@RequestHeader String token){
        return ResponseEntity.ok(userService.deleteUser(token));
    }

    @PutMapping(UPDATETODOBYSTATUS)
    @Operation(summary = "Todo Status guncelleyen method")
    public ResponseEntity<ToDoListResponse> updateToDoByStatus(@RequestHeader String token, Status status, Long todoId){
        return ResponseEntity.ok(userService.updateToDoByStatus(token, status, todoId));
    }

    @GetMapping(GETALL)
    @Operation(summary = "Genel ozellikleri listeleyen method")
    public ResponseEntity<List<ToDo>> getAllToDo(){
        return ResponseEntity.ok(userService.getAllToDo());
    }

    @GetMapping(GETALLMYTODO)
    @Operation(summary = "Ozel özellikleri listeleyen method")
    public ResponseEntity<List<ToDo>> getAllMyToDo (@RequestHeader String token){
        return ResponseEntity.ok(userService.getAllMyToDo(token));
    }
}
