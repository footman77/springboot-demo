package com.example.demo.controller;

import com.example.demo.pojo.MyJSONResult;
import com.example.demo.pojo.SysUser;
import com.example.demo.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Sid sid;
	
	@RequestMapping("/saveUser")
	public MyJSONResult saveUser() throws Exception {
		
		String userId = sid.nextShort();
		
		SysUser user = new SysUser();
		user.setId(userId);
		user.setUsername("footman" + new Date());
		user.setNickname("footman" + new Date());
		user.setPassword("abc123");
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		
		userService.saveUser(user);
		
		return MyJSONResult.ok("保存成功");
	}
	
	@RequestMapping("/updateUser")
	public MyJSONResult updateUser() {
		
		SysUser user = new SysUser();
		user.setId("180917GHK0KAC6W0");
		user.setUsername("10011001-updated" + new Date());
		user.setNickname("10011001-updated" + new Date());
		user.setPassword("10011001-updated");
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		
		userService.updateUser(user);
		
		return MyJSONResult.ok("保存成功");
	}
	
	@RequestMapping("/deleteUser")
	public MyJSONResult deleteUser(String userId) {
		
		userService.deleteUser(userId);
		
		return MyJSONResult.ok("删除成功");
	}
	
	@RequestMapping("/queryUserById")
	public MyJSONResult queryUserById(String userId) {
		
		return MyJSONResult.ok(userService.queryUserById(userId));
	}
	
	@RequestMapping("/queryUserList")
	public MyJSONResult queryUserList() {
		
		SysUser user = new SysUser();
		user.setUsername("imooc");
		user.setNickname("lee");
		
		List<SysUser> userList = userService.queryUserList(user);
		
		return MyJSONResult.ok(userList);
	}
	
	@RequestMapping("/queryUserListPaged")
	public MyJSONResult queryUserListPaged(Integer page) {

		if (page == null) {
			page = 1;
		}

		int pageSize = 10;

		SysUser user = new SysUser();
//		user.setNickname("lee");

		List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);

		return MyJSONResult.ok(userList);
	}
//
	@RequestMapping("/queryUserByIdCustom")
	public MyJSONResult queryUserByIdCustom(String userId) {

		return MyJSONResult.ok(userService.queryUserByIdCustom(userId));
	}
//
	@RequestMapping("/saveUserTransactional")
	public MyJSONResult saveUserTransactional() {

		String userId = sid.nextShort();

		SysUser user = new SysUser();
		user.setId(userId);
		user.setUsername("lee" + new Date());
		user.setNickname("lee" + new Date());
		user.setPassword("abc123");
		user.setIsDelete(0);
		user.setRegistTime(new Date());

		userService.saveUserTransactional(user);

		return MyJSONResult.ok("保存成功");
	}
}
