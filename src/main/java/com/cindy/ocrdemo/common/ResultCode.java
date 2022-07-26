package com.cindy.ocrdemo.common;

public enum ResultCode {
	USERID_PASSWORD_ERROR(406, "用户名或密码不正确"),
    RES_NULL(405, "结果为空"),
    LOGIN_NULL_TOKEN(1001, "您还未登录或登录失效，请重新登录！"),
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"), VALIDATE_FAILED(404, "参数检验失败"), UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}