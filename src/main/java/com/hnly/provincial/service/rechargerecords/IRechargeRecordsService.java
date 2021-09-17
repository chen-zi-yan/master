package com.hnly.provincial.service.rechargerecords;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsDTO;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;

import java.util.List;

/**
 * <p>
 * 充值记录 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface IRechargeRecordsService extends IService<RechargeRecords> {

    /**
     * 查询充值记录分页数据
     *
     * @param rechargeRecordsDTO 条件
     * @return 分页结果
     */
    TableDataUtils<List<RechargeRecordsVO>> findListByPage(RechargeRecordsDTO rechargeRecordsDTO);

}
