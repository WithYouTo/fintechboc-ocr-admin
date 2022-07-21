package com.cindy.ocrdemo.controller;

import com.cindy.ocrdemo.anno.NoNeedLogin;
import com.cindy.ocrdemo.common.CommonResult;
import com.cindy.ocrdemo.dto.UserContext;
import com.cindy.ocrdemo.dto.UserLoginFormDTO;
import com.cindy.ocrdemo.dto.UserReturnDTO;
import com.cindy.ocrdemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @NoNeedLogin
    public CommonResult login(@RequestBody UserLoginFormDTO loginFormDTO){
        return userService.login(loginFormDTO);
    }

    @GetMapping("/info")
    public CommonResult getUserInfo(){
        UserReturnDTO userReturnDTO = userService.getUserInfo(UserContext.getUser().getToken());
        return CommonResult.success(userReturnDTO);
    }

    @PostMapping("/add")
    public CommonResult addUser(@RequestBody UserLoginFormDTO loginFormDTO){
        userService.addUser(loginFormDTO);
        return CommonResult.success("用户添加成功");
    }

    @GetMapping("/test")
    public CommonResult test(){
        return CommonResult.success("测试成功");
    }

    @PostMapping("/logout")
    public CommonResult logout(){
        // 删除用户信息
        UserContext.remove();
        return CommonResult.success("退出成功");
    }
}
