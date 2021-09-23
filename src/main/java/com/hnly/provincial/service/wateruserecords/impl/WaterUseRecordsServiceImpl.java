package com.hnly.provincial.service.wateruserecords.impl;

import com.alibaba.druid.util.StringUtils;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecords;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;
import com.hnly.provincial.dao.wateruserecords.WaterUseRecordsMapper;
import com.hnly.provincial.service.wateruserecords.IWaterUseRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 用水记录表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-23
 */
@Service
public class WaterUseRecordsServiceImpl extends ServiceImpl<WaterUseRecordsMapper, WaterUseRecords> implements IWaterUseRecordsService {

    @Override
    public TableDataUtils<List<WaterUseRecordsVO>> findListByPage(WaterUseRecordsVO waterUseRecordsVO) {
        Page<WaterUseRecords> page = lambdaQuery()
                .likeRight(StringUtils.isEmpty(waterUseRecordsVO.getCode()), WaterUseRecords::getCode, waterUseRecordsVO.getCode())
                .page(waterUseRecordsVO.page());
        List<WaterUseRecordsVO> waterUseRecordsVOs = Conversion.changeList(page.getRecords(), WaterUseRecordsVO.class);
        for (WaterUseRecordsVO useRecordsVO : waterUseRecordsVOs) {
            System.out.println("useRecordsVO = " + useRecordsVO);
        }

        return TableDataUtils.success(page.getTotal(), waterUseRecordsVOs);
    }

}
