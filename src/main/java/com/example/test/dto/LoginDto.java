package com.example.test.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @Size(min = 1, max = 20)
    @NotNull(message = "請輸入帳號")
    private String username;

    @Size(min = 4, max = 20)
    @NotNull(message = "請輸入密碼")
    private String password;
}
