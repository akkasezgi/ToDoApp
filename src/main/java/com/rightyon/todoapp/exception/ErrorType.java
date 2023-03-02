package com.rightyon.todoapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {


    INTERNAL_ERROR(3100,"Sunucu Hatasi", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(2100,"Parametre Hatasi",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(2110,"Kullanici Olusturulamadi",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(2111,"Kullanici bulunamadi.",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(2112,"Kullanici adi yada sifre hatali",HttpStatus.BAD_REQUEST),
    USER_NOT_UPDATED(2113, "Kullanici bilgileri guncellenemedi." ,HttpStatus.BAD_REQUEST) ,
    VALUE_NOT_FOUND_IN_USERS(3110,"Kullaniciya ait boyle bir deger bulunamadi", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_DELETED(3111,"Kullanici silinemedi.",HttpStatus.INTERNAL_SERVER_ERROR),
    TODO_NOT_FOUND(2120,"Boyle bir todo listesi bulunamadi.",HttpStatus.BAD_REQUEST),
    TODO_NOT_UPDATED(2121, "TODO bilgileri guncellenemedi." ,HttpStatus.BAD_REQUEST) ,

    ;

    private int code;
    private String message;
    HttpStatus httpStatus;
}
