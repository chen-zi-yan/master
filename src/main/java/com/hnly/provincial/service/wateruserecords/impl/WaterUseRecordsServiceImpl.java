package com.hnly.provincial.service.wateruserecords.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.wateruserecords.WaterUseRecordsMapper;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecords;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.device.IDeviceService;
import com.hnly.provincial.service.farmer.IFarmerService;
import com.hnly.provincial.service.wateruserecords.IWaterUseRecordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Resource
    private IAreaService iAreaService;

    @Resource
    private IFarmerService farmerService;

    @Resource
    private IDeviceService deviceService;

    @Override
    public TableDataUtils<List<WaterUseRecordsVO>> findListByPage(WaterUseRecordsVO waterUseRecordsVO) {
        Page<WaterUseRecords> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(waterUseRecordsVO.getCode()), WaterUseRecords::getCode, waterUseRecordsVO.getCode())
                .page(waterUseRecordsVO.page());
        List<WaterUseRecordsVO> waterUseRecordsVOs = Conversion.changeList(page.getRecords(), WaterUseRecordsVO.class);
        for (WaterUseRecordsVO vo : waterUseRecordsVOs) {
            vo.setFarmerName(farmerService.getFarmerName(vo.getFarmerId()));
            vo.setDeviceName(deviceService.getDeviceName(vo.getDeviceId()));
            Map<String, String> allAreaName = iAreaService.getAllAreaName(vo.getCode());
            vo.setCityName(allAreaName.get("shi"));
            vo.setCountyName(allAreaName.get("xian"));
            vo.setTownshipName(allAreaName.get("xiang"));
            vo.setVillageName(allAreaName.get("cun"));
        }
        return TableDataUtils.success(page.getTotal(), waterUseRecordsVOs);
    }
}
