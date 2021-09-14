package com.hnly.provincial.entity.farmer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import com.hnly.provincial.comm.validation.Add;
import com.hnly.provincial.comm.validation.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 农户表
 * </p>
 *
 * @author czy
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_farmer")
@Schema(name = "FarmerVO", description = "农户表")
public class FarmerVO extends PageWhere<Farmer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能null", groups = {Update.class})
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "市")
    private String cityName;

    @Schema(description = "县")
    private String countyName;

    @Schema(description = "乡")
    private String townshipName;

    @NotNull(message = "村名不能null", groups = {Add.class})
    @NotEmpty(message = "村名不能为空", groups = {Add.class})
    @Schema(description = "村")
    private String name;

    @NotNull(message = "行政区划不能null", groups = {Add.class})
    @NotEmpty(message = "行政区划不能为空", groups = {Add.class})
    @Schema(description = "行政区划")
    private String code;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$", message = "手机号输入错误")
    @Schema(description = "手机号(被隐藏)")
    private String phone;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$", message = "手机号输入错误")
    @Schema(description = "手机号(显示)")
    private String phoneShow;

    @NotNull(message = "身份证号不能null", groups = {Add.class})
    @NotEmpty(message = "身份证号不能为空", groups = {Add.class})
    @Pattern(regexp = "^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)?$", message = "身份证号输入错误")
    @Schema(description = "身份证号(被隐藏)")
    private String idCard;

    @Pattern(regexp = "^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)?$", message = "身份证号输入错误")
    @Schema(description = "身份证(显示)")
    private String idCardShow;

    @Schema(description = "农户编号")
    private String userRegistrationNo;

    @Schema(description = "非标准设备 ic卡号")
    private String icCode;

    @Schema(description = "余额")
    private BigDecimal money;

    @Schema(description = "设备序列号")
    private String devSn;

    @Schema(description = "设备注册号")
    private String devRegistrationNo;

    @Schema(description = "卡状态：0 正常，1挂失，2失效")
    private String status;

    @Schema(description = "状态名字")
    private String statusName;

    @Schema(description = "土地面积")
    private BigDecimal landArea;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    public String getStatusName() {
        if (status.equals("0")){
            return "正常";
        }else if (status.equals("1")){
            return "挂失";
        }else if (status.equals("2")){
            return "失效";
        }else {
            return "状态错误";
        }
    }
}
