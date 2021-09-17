package com.hnly.provincial.dao.rechargerecords;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsDTO;

/**
 * <p>
 * 充值记录 Mapper 接口
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface RechargeRecordsMapper extends BaseMapper<RechargeRecords> {

    IPage<RechargeRecordsDTO> selectData(IPage<rechargeRecordsDTO> page, String code, String name, String icCode);

}
