package com.hnly.provincial.entity.device;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 设备信息表
 * </p>
 *
 * @author czy
 * @since 2021-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_device")
@Schema(name = "Device", description = "设备信息表")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "类型：1 充值机，2 灌溉机 3 非标准设备")
    private String type;

    @Schema(description = "设备名称")
    private String name;

    @Schema(description = "行政区划")
    private String code;

    @Schema(description = "设备序列号")
    private String devSn;

    @Schema(description = "设备注册号")
    private String devRegistrationNo;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;


}
