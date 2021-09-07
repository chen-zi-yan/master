package com.hnly.provincial.comm;

/**
 * <p>
 *
 * </p>
 *
 * @author maqh
 * @version 1.0
 * @since 2021-05-25
 */
public enum ResultEnum {
    //状态码对应信息
    SUCCESS(200, "成功"),
    NOT_AUTHORITY(401, "非法访问"),
    USER_ALREADY_EXISTS(40001, "该用户名已存在"),
    NOSESSION(40002, "未登录,或session过期"),
    USER_NOT_LOGIN(40003, "账号被禁用"),
    USER_USERNAME_PASSWORD_EXISTS(40004, "账号或密码错误"),
    SAVE_ERROR(40005, "保存错误"),
    NOT_DELETE(40006, "无法删除"),
    HIVE_NOT_DELETE(40007, "已经被使用,无法被删除"),
    OLDPASSWORD_ERROR(40008, "老密码错误"),
    VALIDATION_ERR(40009, "验证错误"),
    UNKNOWN_ERR(-1, "未知错误"),
    TOKEN_EXPIRED(40010, "验证错误"),
    NOTHINGNESS(40011, "不存在"),
    FAILURE(40012, "失败"),
    CHANGEFAILED(40013, "修改失败"),
    CODE_EXIST(40014, "行政区划已经存在"),
    CODE_SUBORDINATE_EXIST(40015, "本条信息存在下级单位,不能修改行政区划代码"),
    CODE_SUPERIOR_EXIST(40015, "本条信息不存在上级单位,不能插入行政区划代码"),
    CODENOTEMPTY(40015, "行政区划代码不能为空"),


    ;
    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态信息
     */
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    }
