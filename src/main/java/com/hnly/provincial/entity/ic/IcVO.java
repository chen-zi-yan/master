package com.hnly.provincial.entity.ic;

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
import java.math.BigDecimal;
import java.util.Date;

/**
* <p>
* 卡号表
* </p>
*
* @author czy
* @since 2021-09-16
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ic")
@Schema(name="IcVO", description="卡号表")
public class IcVO extends PageWhere<Ic> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "农户id")
    private Long farmerId;

    @NotNull(message = "ic卡号不能为null")
    @NotEmpty(message = "ic卡号不能为空")
    @Schema(description = "ic卡号")
    private String icCode;

    @Schema(description = "卡内金额")
    private BigDecimal money;

    @Schema(description = "设备序列号")
    private String devSn;

    @Schema(description = "设备注册号")
    private String devRegistrationNo;

    @Schema(description = "卡状态 0 正常 1 挂失 2 失效")
    private String status;

    @Schema(description = "卡状态 0 正常 1 挂失 2 失效")
    private String statusName;

    @NotNull(message = "type不能为null")
    @NotEmpty(message = "type不能为空")
    @Schema(description = "0 标椎ic卡 1 非标准ic卡")
    private String type;

    @Schema(description = "0标椎ic卡 1非标准ic卡")
    private String typeName;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    public String getStatusName() {
        switch (status) {
            case "0":
                return "正常";
            case "1":
                return "挂失";
            case "2":
                return "失效";
            default:
                return "状态错误";
        }
    }

    public String getTypeName() {
        switch (type){
            case "0":
                return "标椎ic卡";
            case "1":
                return "非标准ic卡";
            default:
                return "错误";
        }
    }

}
