package com.hnly.provincial.entity.treatybook;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Schema(name = "TreatyBook", description = "协议书")
public class TreatyBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "甲方")
    @TableField("partyA")
    private String partya;

    @Schema(description = "乙方")
    @TableField("partyB")
    private String partyb;

    @Schema(description = "设施名称")
    private String name;

    @Schema(description = "管护内容")
    @TableField("manageConent")
    private String manageconent;

    @Schema(description = "开始时间")
    @TableField("beginTime")
    private Date begintime;

    @Schema(description = "结束时间")
    @TableField("endTime")
    private Date endtime;


}
