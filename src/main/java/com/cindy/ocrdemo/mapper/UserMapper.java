package com.cindy.ocrdemo.mapper;

import com.cindy.ocrdemo.pojo.User;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author cindy
* @description 针对表【ocr_user(用户表)】的数据库操作Mapper
* @createDate 2022-07-19 11:04:01
* @Entity com.cindy.ocrdemo.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {
	

	@Select("SELECT `ocr_role`.`role_name` FROM `ocr_user`,`ocr_user_role`,`ocr_role` \n"
			+ "WHERE `ocr_user`.`username`=#{username} "
			+ "AND `ocr_user`.`user_id`=`ocr_user_role`.`user_id` "
			+ "AND `ocr_role`.`role_id`=`ocr_user_role`.`role_id`")
	String getRole(String username);

}




