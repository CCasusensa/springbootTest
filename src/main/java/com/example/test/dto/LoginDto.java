package com.example.test.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @Size(min = 4, max = 20, message = "帳號必須在4~20字元之間")
    @NotEmpty(message = "請輸入帳號")
    private String username;

    @Size(min = 4, max = 20, message = "密碼必須在4~20字元之間")
    @NotEmpty(message = "請輸入密碼")
    private String password;
}
