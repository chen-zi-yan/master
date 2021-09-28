package com.hnly.provincial.service.rechargerecords.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.rechargerecords.RechargeRecordsMapper;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.rechargerecords.IRechargeRecordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
            Map<String, String> allName = iAreaService.getAllAreaName(dto.getCode());
            dto.setVillageName(allName.get("cun"));
            dto.setTownshipName(allName.get("xiang"));
            dto.setCountyName(allName.get("xian"));
            dto.setCityName(allName.get("shi"));
        }
        return TableDataUtils.success(page.getTotal(), rechargeRecordsVOList);
    }


}
