package com.hnly.provincial.entity.farmer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Schema(name = "Farmer", description = "农户表")
public class Farmer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "行政区划")
    private String code;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "身份证号")
    private String idCard;

    @Schema(description = "土地面积")
    private BigDecimal landArea;

    @Schema(description = "审批状态")
    private String status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;


}
