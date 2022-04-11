package com.markerhub.controller;

import com.markerhub.common.lang.Result;
import com.markerhub.entity.User;
import com.markerhub.mapper.UserMapper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserMapper usersMapper;

    public UserController(UserMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = usersMapper.selectById(1);
        return Result.success(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.success(user);
    }

}
