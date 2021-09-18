package com.hnly.provincial.entity.farmer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import com.hnly.provincial.comm.validation.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
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

    @NotNull(message = "村名不能null")
    @NotEmpty(message = "村名不能为空")
    @Schema(description = "村")
    private String name;

    @NotNull(message = "行政区划不能null")
    @NotEmpty(message = "行政区划不能为空")
    @Schema(description = "行政区划")
    private String code;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$", message = "手机号输入错误")
    @Schema(description = "手机号(显示)")
    private String phone;

    @Schema(description = "手机号(隐藏)")
    private String phoneHidden;

    @NotNull(message = "身份证号不能null")
    @NotEmpty(message = "身份证号不能为空")
    @Pattern(regexp = "^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)?$", message = "身份证号输入错误")
    @Schema(description = "身份证号(显示)")
    private String idCard;

    @Schema(description = "身份证(隐藏)")
    private String idCardHidden;

    @Schema(description = "状态名字")
    private String statusName;

    @Digits(integer = 8, fraction = 2)
    @Schema(description = "土地面积")
    private BigDecimal landArea;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}
