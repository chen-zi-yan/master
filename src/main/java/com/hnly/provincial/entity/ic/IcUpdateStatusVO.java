package com.hnly.provincial.entity.ic;

import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@Schema(name = "IcUpdateStatusVO", description = "更新ic卡号状态(入参)")
public class IcUpdateStatusVO extends PageWhere<Ic> implements Serializable {

    @Schema(description = "ic卡的状态")
    private Long status;

    @Schema(description = "该数据的id")
    private Long id;
}
