package com.rightyon.todoapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestDto {

    @NotBlank(message = "Kullanici adi bos gecilemez")
    private String name;
    private String surname;
    @NotBlank(message = "e-mail bilgisi bos gecilemez.")
    @Email(message = "Lutfen gecerli bir e-mail adresi giriniz.")
    private String email;
    @NotBlank(message = "Sifre bos gecilemez")
    @Size(min = 8,max = 64)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String password;
}
