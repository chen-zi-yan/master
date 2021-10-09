package com.hnly.provincial.dao.ic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnly.provincial.entity.ic.Ic;

/**
 * <p>
 * 卡号表 Mapper 接口
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface IcMapper extends BaseMapper<Ic> {

    /**
     * 更新ic卡号状态
     *
     * @param status 状态
     * @param id id
     * @return false 失败   true 成功
     */
    boolean updateStatus(Long status, long id);
}
