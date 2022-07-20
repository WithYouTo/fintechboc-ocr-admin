package com.cindy.ocrdemo.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.common.ResultCode;
import com.cindy.ocrdemo.dto.UserLoginFormDTO;
import com.cindy.ocrdemo.dto.UserReturnDTO;
import com.cindy.ocrdemo.pojo.User;
import com.cindy.ocrdemo.service.UserService;
import com.cindy.ocrdemo.mapper.UserMapper;
import com.cindy.ocrdemo.util.EncryptUtil;
import com.cindy.ocrdemo.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
* @author cindy
* @description 针对表【ocr_user(用户表)】的数据库操作Service实现
* @createDate 2022-07-19 11:04:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public CommonResult login(UserLoginFormDTO userLoginFormDTO) {

        String username = userLoginFormDTO.getUsername();
        String password = userLoginFormDTO.getPassword();
        // 参数校验
        if(StrUtil.isEmpty(username) || StrUtil.isEmpty(password)){
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return CommonResult.failed(ResultCode.RES_NULL);
        }
        // 验证用户名密码是否正确
        String inputPsw = EncryptUtil.encryptPassword(password);
        if(!inputPsw.equals(user.getPassword())){
            // 密码不正确
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
        // 生成token
        String token = JwtUtil.createToken(user);
        // 返回页面数据
        UserReturnDTO userReturnDTO = new UserReturnDTO();
        userReturnDTO.setUserId(user.getUserId());
        userReturnDTO.setUsername(user.getUsername());
        userReturnDTO.setRole("超级管理员");
        userReturnDTO.setToken(token);
        return CommonResult.success(userReturnDTO);
    }

    @Override
    public CommonResult getUserInfo(String token) {
        Long userId = JwtUtil.getUserIdTokenInfo(token);
        User user = userMapper.selectById(userId);
        UserReturnDTO userReturnDTO = new UserReturnDTO();
        userReturnDTO.setUserId(user.getUserId());
        userReturnDTO.setUsername(user.getUsername());
        userReturnDTO.setToken(token);
        return CommonResult.success(userReturnDTO);
    }

    @Override
    public void addUser(UserLoginFormDTO userLoginFormDTO) {
        // 创建用户
        String password = userLoginFormDTO.getPassword();
        // 密码加密
       String digestPsw = EncryptUtil.encryptPassword(password);
        User user = new User();
        user.setUsername(userLoginFormDTO.getUsername());
        user.setPassword(digestPsw);
        user.setSalt("$1$BOC-FINTECH-SALT");
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
    }
}




