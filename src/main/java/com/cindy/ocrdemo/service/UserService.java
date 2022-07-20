package com.cindy.ocrdemo.service;

import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.UserLoginFormDTO;
import com.cindy.ocrdemo.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cindy
* @description 针对表【ocr_user(用户表)】的数据库操作Service
* @createDate 2022-07-19 11:04:01
*/
public interface UserService extends IService<User> {
    CommonResult login(UserLoginFormDTO userLoginFormDTO);

    CommonResult getUserInfo(String  token);

    void addUser(UserLoginFormDTO userLoginFormDTO);
}
