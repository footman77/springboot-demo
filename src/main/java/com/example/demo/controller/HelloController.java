package com.example.demo.controller;

import com.example.demo.pojo.MyJSONResult;
import com.example.demo.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public String hello(){
        return "hello springboot";
    }

    @RequestMapping("/resources")
    public MyJSONResult getResources(){
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        return MyJSONResult.ok(bean);
    }
}
