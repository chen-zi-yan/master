package com.hnly.provincial.dao.rechargerecords;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;

import java.util.List;

/**
 * <p>
 * 充值记录 Mapper 接口
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface RechargeRecordsMapper extends BaseMapper<RechargeRecords> {

    TableDataUtils<List<RechargeRecordsVO>> selectData(String devSn, String devRegistrationNo);
}
