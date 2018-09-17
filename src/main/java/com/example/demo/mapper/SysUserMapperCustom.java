package com.example.demo.mapper;

import com.example.demo.pojo.SysUser;
import com.example.demo.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysUserMapperCustom extends MyMapper<SysUser> {

    List<SysUser> queryUserSimplyInfoById(String id);
}