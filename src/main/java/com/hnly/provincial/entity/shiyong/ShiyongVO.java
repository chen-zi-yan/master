package com.hnly.provincial.entity.shiyong;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* <p>
* 使用权证
* </p>
*
* @author czy
* @since 2021-09-14
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shiyong")
@Schema(name="ShiyongVO", description="使用权证")
public class ShiyongVO extends PageWhere<Shiyong> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "号不能为null")
    @NotEmpty(message = "号不能为空")
    @Schema(description = "号")
    private String number;

    @NotNull(message = "设施所在行政区域不能为null")
    @NotEmpty(message = "设施所在行政区域不能为空")
    @Schema(description = "设施所在行政区域")
    private String area;

    @NotNull(message = "设施具体位置不能为null")
    @NotEmpty(message = "设施具体位置不能为空")
    @Schema(description = "设施具体位置")
    private String position;

    @NotNull(message = "产权所有人不能为null")
    @NotEmpty(message = "产权所有人不能为空")
    @Schema(description = "产权所有人")
    private String propertyOwner;

    @NotNull(message = "使用权人不能为null")
    @NotEmpty(message = "使用权人不能为空")
    @Schema(description = "使用权人")
    private String rightHolder;

    @NotNull(message = "使用权人身份证或法人证书号码不能为null")
    @NotEmpty(message = "使用权人身份证或法人证书号码不能为空")
    @Schema(description = "使用权人身份证或法人证书号码")
    private String idNumber;

    @NotNull(message = "使用权获得方式不能为null")
    @NotEmpty(message = "使用权获得方式不能为空")
    @Schema(description = "使用权获得方式")
    private String mode;

    @NotNull(message = "主要设施情况不能为null")
    @NotEmpty(message = "主要设施情况不能为空")
    @Schema(description = "主要设施情况（包括设施数量、受益范围）")
    private String details;

    @Schema(description = "其他")
    private String other;


}
