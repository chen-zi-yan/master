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
    TOKEN_OVERDUE(40003, "token即将过期"),
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
    DEVSN_EXIST(40020, "该设备序列号已存在"),
    HOLDICCODE_EXIST(40021, "该用户持有ic卡,请查询后在操作"),
    CARID_EXIST(40022, "该身份证号码或组织机构代码已经存在"),
    AREA_EXIST(40023, "该设施所在行政区域已经存在"),
    NUMBER_EXIST(40024, "该号码已经存在"),
    COUNTY_EXIST(40025, "该县已经存在"),
    ROLE_EXIST(40026, "该角色名称已经存在"),
    CODELIMIT_EXIST(40027, "该区域规划已经填写过用水定额"),


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
