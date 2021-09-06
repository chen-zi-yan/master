package com.hnly.provincial.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import com.hnly.provincial.comm.validation.Add;
import com.hnly.provincial.comm.validation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
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
@ApiModel(value = "User对象", description = "系统用户表")
public class UserVO extends PageWhere<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Null(groups = {Update.class})
    @NotEmpty(message = "账号不能为空", groups = {Add.class})
    @Size(min = 4, max = 12, message = "账号长度大于4字符小于12字符")
    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "用户名")
    private String name;

    @Null(message = "不能修改密码", groups = {Update.class})
    @NotEmpty(message = "密码不能为空", groups = {Add.class})
    @Size(min = 4, max = 16, message = "账号长度大于4字符小于12字符")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "0 启用 1禁用")
    private String status;

    @ApiModelProperty(value = "管理范围")
    private String ange;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "单位地址")
    private String unitAddress;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "行政区划")
    private String code;

    @ApiModelProperty(value = "权限id")
    private Long quanxian;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "修改时间")
    private Date updatetime;

    @ApiModelProperty(value = "协会人员 1")
    private String association;


}
