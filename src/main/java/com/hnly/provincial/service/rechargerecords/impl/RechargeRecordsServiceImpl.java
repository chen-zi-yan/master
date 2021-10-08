package com.hnly.provincial.service.rechargerecords.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.rechargerecords.RechargeRecordsMapper;
import com.hnly.provincial.entity.area.AreaName;
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

        IPage<RechargeRecordsVO> page =
                rechargeRecordsMapper.selectData(rechargeRecordsVO.page(), rechargeRecordsVO.getCode(), rechargeRecordsVO.getName());
        List<RechargeRecordsVO> rechargeRecordsVOList = Conversion.changeList(page.getRecords(), RechargeRecordsVO.class);
        for (RechargeRecordsVO dto : rechargeRecordsVOList) {
            AreaName allAreaName = iAreaService.getAllAreaName(dto.getCode());
            dto.setVillageName(allAreaName.getCunName());
            dto.setTownshipName(allAreaName.getXiangName());
            dto.setCountyName(allAreaName.getXianName());
            dto.setCityName(allAreaName.getShiName());
        }
        return TableDataUtils.success(page.getTotal(), rechargeRecordsVOList);
    }


}
