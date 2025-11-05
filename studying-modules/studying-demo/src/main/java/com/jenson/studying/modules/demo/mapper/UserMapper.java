package com.jenson.studying.modules.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenson.studying.modules.demo.domain.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
