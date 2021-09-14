package com.hnly.provincial.entity.device;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnly.provincial.comm.utils.PageWhere;
import com.hnly.provincial.comm.validation.Add;
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
@Schema(name="DeviceVO", description="设备信息表")
public class DeviceVO extends PageWhere<Device> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "类型不能null", groups = {Add.class})
    @NotEmpty(message = "类型不能为空", groups = {Add.class})
    @Schema(description = "类型：1 充值机，2 灌溉机 3 非标准设备")
    private String type;

    @Schema(description = "类型名字：1 充值机，2 灌溉机 3 非标准设备")
    private String typeName;

    @Schema(description = "设备名称")
    private String name;

    @Schema(description = "市")
    private String cityName;

    @Schema(description = "县")
    private String countyName;

    @Schema(description = "乡")
    private String townshipName;

    @Schema(description = "村")
    private String villageName;

    @NotNull(message = "行政区划不能null", groups = {Add.class})
    @NotEmpty(message = "行政区划不能为空", groups = {Add.class})
    @Schema(description = "行政区划")
    private String code;

    @NotNull(message = "设备序列号不能null", groups = {Add.class})
    @NotEmpty(message = "设备序列号不能为空", groups = {Add.class})
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

    public String getTypeName() {
        if (type.equals("1")) {
            return "充值机";
        }else if (type.equals("2")){
            return "灌溉机";
        }else if (type.equals("3")){
            return "非标准设备";
        }else {
            return "";
        }
    }
}
