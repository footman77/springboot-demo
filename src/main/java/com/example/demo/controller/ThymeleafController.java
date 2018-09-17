package com.example.demo.controller;


import com.example.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("th")
public class ThymeleafController {


    @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("name","laoshia");
        return "thymeleaf/index";
    }


    @RequestMapping("/center")
    public String center(){
        return "thymeleaf/center/center";
    }

    @RequestMapping("/test")
    public String test(ModelMap map){
        User user = new User();
        user.setName("superadmin");
        user.setPassword("1234");
        user.setBirthday(new Date());
        user.setAge(19);
        user.setDesc("<span style='color:red'>hello world!!!!!!</span>");

        User u1 = new User();
        u1.setName("lisi");
        u1.setAge(18);
        u1.setBirthday(new Date());
        u1.setPassword("83498");

        User u2 = new User();
        u2.setName("wangwu");
        u2.setAge(11);
        u2.setBirthday(new Date());
        u2.setPassword("81111");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(u1);
        userList.add(u2);
        map.addAttribute("userList",userList);
        map.addAttribute("user",user);
        return "thymeleaf/test";
    }


    @RequestMapping("postform")
    public String postform(User user){
        System.out.println(user.getName());
        System.out.println(user.getAge());

        return "redirect:/th/test";
    }

}
