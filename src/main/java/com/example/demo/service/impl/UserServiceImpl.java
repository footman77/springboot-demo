package com.example.demo.service.impl;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.mapper.SysUserMapperCustom;
import com.example.demo.pojo.SysUser;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private SysUserMapper userMapper;

    @Autowired
    private SysUserMapperCustom userMapperCustom;

    @Override
    public void saveUser(SysUser user) throws Exception {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(SysUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)

    public void deleteUser(String userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserList(SysUser user) {
        return userMapper.selectByExample(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		// 开始分页
        PageHelper.startPage(page, pageSize);

		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		example.orderBy("registTime").desc();
		List<SysUser> userList = userMapper.selectByExample(example);

		return userList;
	}
//
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserByIdCustom(String userId) {
        List<SysUser> sysUsers = userMapperCustom.queryUserSimplyInfoById(userId);
        if(sysUsers != null && !sysUsers.isEmpty()){
            return sysUsers.get(0);
        }
        return null;
    }
//
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserTransactional(SysUser user) {

        userMapper.insert(user);

        int a = 1 / 0;

        user.setIsDelete(1);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
