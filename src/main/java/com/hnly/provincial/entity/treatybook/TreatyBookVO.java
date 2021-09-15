package com.hnly.provincial.entity.treatybook;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.Date;

/**
* <p>
* 协议书
* </p>
*
* @author czy
* @since 2021-09-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_treaty_book")
@Schema(name="TreatyBookVO", description="协议书")
public class TreatyBookVO extends PageWhere<TreatyBook> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "甲方不能为null")
    @NotEmpty(message = "甲方不能为空")
    @Schema(description = "甲方")
    @TableField("partyA")
    private String partya;

    @NotNull(message = "乙方不能为null")
    @NotEmpty(message = "乙方不能为空")
    @Schema(description = "乙方")
    @TableField("partyB")
    private String partyb;

    @NotNull(message = "设施名称不能为null")
    @NotEmpty(message = "设施名称不能为空")
    @Schema(description = "设施名称")
    private String name;

    @NotNull(message = "管护内容不能为null")
    @NotEmpty(message = "管护内容不能为空")
    @Schema(description = "管护内容")
    @TableField("manageConent")
    private String manageconent;

    @NotNull(message = "开始时间不能为null")
    @NotEmpty(message = "开始时间不能为空")
    @Schema(description = "开始时间")
    @TableField("beginTime")
    private Date begintime;

    @NotNull(message = "结束不能为null")
    @NotEmpty(message = "结束不能为空")
    @Schema(description = "结束时间")
    @TableField("endTime")
    private Date endtime;


}
