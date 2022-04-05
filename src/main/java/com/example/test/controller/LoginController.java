package com.example.test.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.test.bean.User;
import com.example.test.dto.LoginDto;
import com.example.test.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private final UserMapper usersMapper;

    public LoginController(UserMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @RequestMapping("/login")
    public String show(HttpSession session) {
        session.setAttribute("errormsg", "");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        if (session.getAttribute("username") != null) {
            System.out.println(String.format("userName: %s logout!",session.getAttribute("username")));
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid LoginDto payload, BindingResult br, String username, String password, HttpSession session) {
        if (br.hasErrors()) {
            session.setAttribute("errormsg", br.getFieldError().getDefaultMessage());
            return "login";
        }
        List<User> users = usersMapper.selectByMap(new HashMap<>() {{
            put("username", username);
            put("password", password);
        }});

        if (users == null || users.isEmpty()) {
            session.setAttribute("errormsg", "帳號或密碼錯誤");
            return "login";
        }
        User user = users.get(0);
        if (!user.isPermission()) {
            session.setAttribute("errormsg", "登入權限不足!");
            return "login";
        }
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("errormsg", "");
        System.out.println(String.format("userName: %s logged in!", user.getUsername()));
        return "success";
    }

    @RequestMapping(value = "/modifyUser", params="addUser", method = RequestMethod.POST)
    public String addUser(String username, String password, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            session.setAttribute("errormsg", "帳號或密碼不能為空");
            return "success";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        if (usersMapper.selectByMap(map).isEmpty()) {
            session.setAttribute("errormsg", "帳號已存在，請重新輸入!");
            return "success";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        usersMapper.insert(user);
        session.setAttribute("errormsg", "新增帳戶成功");
        System.out.println(String.format("addUser :%s", user.getUsername()));
        return "success";
    }

    @RequestMapping(value = "/modifyUser", params="delUser", method = RequestMethod.POST)
    public String delUser(String username, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        if (username == null || username.isEmpty()) {
            session.setAttribute("errormsg", "帳號不能為空");
            return "success";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        if (usersMapper.selectByMap(map).isEmpty()) {
            session.setAttribute("errormsg", "帳號不存在，請重新輸入!");
            return "success";
        }

        usersMapper.deleteByMap(map);
        session.setAttribute("errormsg", "帳號刪除成功!");
        System.out.println(String.format("delUser :%s", username));
        return "success";
    }

    @RequestMapping(value = "/modifyUser", params="modifyUser", method = RequestMethod.POST)
    public String editUser(String username, String password, String newPassword, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        if (username == null || username.isEmpty() || password == null || password.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            session.setAttribute("errormsg", "帳號或密碼或新密碼為空");
            return "success";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        if (usersMapper.selectByMap(map).isEmpty()) {
            session.setAttribute("errormsg", "帳號不存在，請重新輸入!");
            return "success";
        } else if (password.equals(newPassword)) {
            session.setAttribute("errormsg", "新密碼不能與舊密碼相同!");
            return "success";
        }
        User user = new User();
        user.setPassword(newPassword);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username);
        usersMapper.update(user, updateWrapper);
        session.setAttribute("errormsg", "該用戶密碼更新成功!");
        System.out.println(String.format("username: %s updatePassword!", username));
        return "success";
    }
}
