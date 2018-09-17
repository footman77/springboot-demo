package com.example.demo.controller;

import com.example.demo.pojo.MyJSONResult;
import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    @RequestMapping("/userJson")
    public MyJSONResult helloUserJson(){

        User user = new User();
        user.setName("laosan3");
        user.setPassword("hellllo2");
        user.setAge(19);
        user.setBirthday(new Date());
        user.setDesc("gogoogogogo234");

        return MyJSONResult.ok(user);

    }

    @RequestMapping("/user")
    public User helloUser(){

        User user = new User();
        user.setName("laosan");
        user.setPassword("helllo");
        user.setAge(19);
        user.setBirthday(new Date());
        user.setDesc("googogo");

        return user;

    }

}
