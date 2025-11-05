package com.jenson.studying.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenson.studying.system.domain.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
