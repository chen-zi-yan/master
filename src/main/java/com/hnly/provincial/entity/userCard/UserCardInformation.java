package com.hnly.provincial.entity.userCard;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 农户卡信息表
 * </p>
 *
 * @author czy
 * @since 2021-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_card_information")
@ApiModel(value="UserCardInformation对象", description="农户卡信息表")
public class UserCardInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键id")
    private Long id;

    @NotNull
    @NotEmpty(message = "不能为空")
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotNull
    @NotEmpty(message = "不能为空")
    @ApiModelProperty(value = "所在村庄(区域码)")
    private String areaCode;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "农户注册号(新设备使用)")
    private byte[] userRegistrationNo;

    @NotNull
    @NotEmpty(message = "不能为空")
    @ApiModelProperty(value = "ic卡号(老设备使用)")
    private String icCode;

    @ApiModelProperty(value = "余额")
    private BigDecimal money;

    @ApiModelProperty(value = "设备序列号")
    private String devSn;

    @ApiModelProperty(value = "设备注册号")
    private String devRegistrationNo;

    @ApiModelProperty(value = "卡状态：0 正常，1挂失，2失效")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


}
