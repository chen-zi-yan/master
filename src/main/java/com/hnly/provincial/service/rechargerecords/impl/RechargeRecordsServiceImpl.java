package com.hnly.provincial.service.rechargerecords.impl;

import com.alibaba.druid.util.StringUtils;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;
import com.hnly.provincial.dao.rechargerecords.RechargeRecordsMapper;
import com.hnly.provincial.service.rechargerecords.IRechargeRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

    @Override
    public TableDataUtils<List<RechargeRecordsVO>> findListByPage(RechargeRecordsVO rechargeRecordsVO) {
        Page<RechargeRecords> page = lambdaQuery()
                .likeRight(StringUtils.isEmpty(rechargeRecordsVO.getOrderNo()), RechargeRecords::getOrderNo, rechargeRecordsVO.getOrderNo())
                .likeRight(StringUtils.isEmpty(rechargeRecordsVO.getIcCode()), RechargeRecords::getIcCode, rechargeRecordsVO.getIcCode())
                .likeRight(StringUtils.isEmpty(rechargeRecordsVO.getDevSn()), RechargeRecords::getDevSn, rechargeRecordsVO.getDevSn())
                .page(rechargeRecordsVO.page());
        List<RechargeRecordsVO> rechargeRecordsVOs = Conversion.changeList(page.getRecords(), RechargeRecordsVO.class);
        return TableDataUtils.success(page.getTotal(), rechargeRecordsVOs);
    }

}
