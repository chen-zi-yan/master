package com.hnly.provincial.entity.area;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 地区表
 * </p>
 *
 * @author maqh
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_area")
@ApiModel(value="Area对象", description="地区表")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "区域号")
    private String code;

    @ApiModelProperty(value = "上级id")
    private String father_code;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "修改时间")
    private Date update_time;

    @ApiModelProperty(value = "0 市 1 县区 2 乡镇 3 村庄")
    private String status;


}
