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
    CODE_EXIST(40014, "已经存在"),
    CODE_SUBORDINATE_EXIST(40015, "存在下级"),
    CODE_SUPERIOR_EXIST(40016, "无上级单位"),
    CODENOTEMPTY(40017, "行政区划代码不能为空"),
    IDCODE_EXIST(40018, "该身份证号已经存在"),
    IC_EXIST(40019, "IC已存在"),
    GETUSERREGISTRATIONNO_EXIST(40020, "该农户编号已存在"),
    DEVSN_EXIST(40021, "该设备序列号已存在"),
    BIGDECIMAL2_EXIST(40022, "必须为小数模式(且整数位最多两位,小数最多两位)"),
    BIGDECIMAL1_EXIST(40023, "必须为小数模式(且整数位最多一位,小数最多两位)"),
    BIGDECIMAL_EXIST(40024, "电价必须为小数模式(且整数位最多四位,小数不做限制)"),

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
