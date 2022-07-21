package com.cindy.ocrdemo.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private static final ThreadLocal<UserReturnDTO> user = new ThreadLocal<>();

    private UserContext() {
    }

    public static Long getUserId() {
        UserReturnDTO baseUserDTO = getUser();
        return baseUserDTO.getUserId();
    }

    public static UserReturnDTO getUser() {
        UserReturnDTO baseUser = user.get();
        if (null == baseUser) {
            throw new RuntimeException("登录失效，请重新登录！");
        }
        return baseUser;
    }

    public static void setBaseUser(UserReturnDTO baseUser) {
        user.set(baseUser);
    }

    public static void remove() {
        user.remove();
    }

}
