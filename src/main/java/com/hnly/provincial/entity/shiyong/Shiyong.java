package com.hnly.provincial.entity.shiyong;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Schema(name="Shiyong", description="使用权证")
public class Shiyong  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "号")
    private String number;

    @Schema(description = "设施所在行政区域")
    private String area;

    @Schema(description = "设施具体位置")
    private String position;

    @Schema(description = "产权所有人")
    private String propertyOwner;

    @Schema(description = "使用权人")
    private String rightHolder;

    @Schema(description = "使用权人身份证或法人证书号码")
    private String idNumber;

    @Schema(description = "使用权获得方式")
    private String mode;

    @Schema(description = "主要设施情况（包括设施数量、受益范围）")
    private String details;

    @Schema(description = "其他")
    private String other;


}
