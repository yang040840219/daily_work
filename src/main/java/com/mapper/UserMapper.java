package com.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.model.User;


public interface UserMapper {
	  @Select("select id,name,age from t_users where id = #{userId}")
	  public User getUser(@Param("userId") String userId);
} 