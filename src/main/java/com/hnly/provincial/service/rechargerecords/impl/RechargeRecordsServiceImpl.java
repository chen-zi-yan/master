package com.hnly.provincial.service.rechargerecords.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.rechargerecords.RechargeRecordsMapper;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.rechargerecords.IRechargeRecordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 充值记录 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Service
public class RechargeRecordsServiceImpl extends ServiceImpl<RechargeRecordsMapper, RechargeRecords> implements IRechargeRecordsService {

    @Resource
    private RechargeRecordsMapper rechargeRecordsMapper;

    @Resource
    private IAreaService iAreaService;

    @Override
    public TableDataUtils<List<RechargeRecordsVO>> findListByPage(RechargeRecordsVO rechargeRecordsVO) {

        rechargeRecordsMapper.selectData(rechargeRecordsVO.getDevSn(), rechargeRecordsVO.getDevRegistrationNo());




        Page<RechargeRecords> page = lambdaQuery().eq(RechargeRecords::getDevSn, rechargeRecordsVO.getDevSn())
                .page(rechargeRecordsVO.page());


//        return TableDataUtils.success(, maps);
        return null;
    }

}
