package com.hnly.provincial.entity.certificate;

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
 * 产权证
 * </p>
 *
 * @author czy
 * @since 2021-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_certificate")
@Schema(name = "Certificate", description = "产权证")
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "编号")
    private String code;

    @Schema(description = "设施名称")
    private String name;

    @Schema(description = "设施地址")
    private String address;

    @Schema(description = "设置管理模式")
    private String mode;

    @Schema(description = "产权权利人")
    private String propertyOwner;

    @Schema(description = "身份证号码或组织机构代码")
    private String carId;

    @Schema(description = "设施整治或建设时间")
    private String constructionTime;

    @Schema(description = "水源工程")
    private String waterEngineering;

    @Schema(description = "渠道工程")
    private String channelEngineering;

    @Schema(description = "排涝工程")
    private String drainageProject;

    @Schema(description = "输水配电工程")
    private String waterElectricity;

    @Schema(description = "田间灌溉设施")
    private String fieldIrrigationFacilities;

    @Schema(description = "配套建筑物")
    private String supportingBuildings;

    @Schema(description = "其他设施")
    private String otherFacilities;


}
