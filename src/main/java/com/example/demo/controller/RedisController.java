package com.example.demo.controller;

import com.example.demo.pojo.MyJSONResult;
import com.example.demo.pojo.SysUser;
import com.example.demo.pojo.User;
import com.example.demo.utils.JsonUtils;

import com.example.demo.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("redis")
public class RedisController {
	
	@Autowired
	private StringRedisTemplate strRedis;
	
	@Autowired
	private RedisOperator redis;
	
	@RequestMapping("/test")
	public MyJSONResult test() {
		
		strRedis.opsForValue().set("f-cache", "hello world!!!!!!");
		
		SysUser user = new SysUser();
		user.setId("100111");
		user.setUsername("fffffff");
		user.setPassword("abc123");
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));

		SysUser jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), SysUser.class);
		
		return MyJSONResult.ok(jsonUser);

//		return MyJSONResult.ok(strRedis.opsForValue().get("f-cache"));
	}
//
	@RequestMapping("/getJsonList")
	public MyJSONResult getJsonList() {

		User user = new User();
		user.setAge(18);
		user.setName("gege");
		user.setPassword("123456");
		user.setBirthday(new Date());

		User u1 = new User();
		u1.setAge(19);
		u1.setName("jiejie");
		u1.setPassword("123456");
		u1.setBirthday(new Date());

		User u2 = new User();
		u2.setAge(17);
		u2.setName("shushu");
		u2.setPassword("123456");
		u2.setBirthday(new Date());

		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(u1);
		userList.add(u2);

		redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);

		String userListJson = redis.get("json:info:userlist");
		List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);

		return MyJSONResult.ok(userListBorn);
	}
}