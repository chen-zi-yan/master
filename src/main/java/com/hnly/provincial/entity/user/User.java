package com.hnly.provincial.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author maqh
 * @since 2021-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@Schema(name = "User对象", description = "系统用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "0 启用 1禁用")
    private String status;

    @Schema(description = "管理范围")
    private String ange;

    @Schema(description = "单位名称")
    private String unitName;

    @Schema(description = "单位地址")
    private String unitAddress;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "行政区划")
    private String code;

    @Schema(description = "权限id")
    private Long quanxian;

    @Schema(description = "创建时间")
    private Date createtime;

    @Schema(description = "修改时间")
    private Date updatetime;

    @Schema(description = "协会人员 1")
    private String association;


}
