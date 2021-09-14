package com.hnly.provincial.entity.certificate;

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
@Schema(name="CertificateVO", description="产权证")
public class CertificateVO extends PageWhere<Certificate> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "编号不能null")
    @NotEmpty(message = "编号不能为空")
    @Schema(description = "编号")
    private String code;

    @NotNull(message = "设施名称不能null")
    @NotEmpty(message = "设施名称不能为空")
    @Schema(description = "设施名称")
    private String name;

    @NotNull(message = "设施地址不能null")
    @NotEmpty(message = "设施地址不能为空")
    @Schema(description = "设施地址")
    private String address;

    @NotNull(message = "设置管理模式不能null")
    @NotEmpty(message = "设置管理模式不能为空")
    @Schema(description = "设置管理模式")
    private String mode;

    @NotNull(message = "产权权利人不能null")
    @NotEmpty(message = "产权权利人不能为空")
    @Schema(description = "产权权利人")
    private String propertyOwner;

    @NotNull(message = "身份证号码或组织机构代码不能null")
    @NotEmpty(message = "身份证号码或组织机构代码不能为空")
    @Schema(description = "身份证号码或组织机构代码")
    private String carId;

    @NotNull(message = "设施整治或建设时间不能null")
    @NotEmpty(message = "设施整治或建设时间不能为空")
    @Schema(description = "设施整治或建设时间")
    private String constructionTime;

    @NotNull(message = "水源工程不能null")
    @NotEmpty(message = "水源工程不能为空")
    @Schema(description = "水源工程")
    private String waterEngineering;

    @NotNull(message = "渠道工程不能null")
    @NotEmpty(message = "渠道工程不能为空")
    @Schema(description = "渠道工程")
    private String channelEngineering;

    @NotNull(message = "排涝工程不能null")
    @NotEmpty(message = "排涝工程不能为空")
    @Schema(description = "排涝工程")
    private String drainageProject;

    @NotNull(message = "输水配电工程不能null")
    @NotEmpty(message = "输水配电工程不能为空")
    @Schema(description = "输水配电工程")
    private String waterElectricity;

    @NotNull(message = "田间灌溉设施不能null")
    @NotEmpty(message = "田间灌溉设施不能为空")
    @Schema(description = "田间灌溉设施")
    private String fieldIrrigationFacilities;

    @NotNull(message = "配套建筑物不能null")
    @NotEmpty(message = "配套建筑物不能为空")
    @Schema(description = "配套建筑物")
    private String supportingBuildings;

    @Schema(description = "其他设施")
    private String otherFacilities;


}
