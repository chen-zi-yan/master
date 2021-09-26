package com.hnly.provincial.service.wateruserecords.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.date.DateTool;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.wateruserecords.WaterUseRecordsMapper;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.wateruserecords.*;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.device.IDeviceService;
import com.hnly.provincial.service.farmer.IFarmerService;
import com.hnly.provincial.service.wateruserecords.IWaterUseRecordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    public TableDataUtils<List<WaterUseRecordsVO>> findListByPage(FindNameVO findNameVO) {
        Page<WaterUseRecords> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(findNameVO.getCode()), WaterUseRecords::getCode, findNameVO.getCode())
                .page(findNameVO.page());
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

    @Override
    public MonthSunWaterVO getMonthSumWater(String code) {
        List<Object> monthSumWater = baseMapper.getMonthSumWater(code, DateTool.getYear());
        List<Object> lastYearMonthSumWater = baseMapper.getMonthSumWater(code, DateTool.getLastYear());
        MonthSunWaterVO monthSunWaterVO = new MonthSunWaterVO();
        monthSunWaterVO.setYear(monthSumWater);
        monthSunWaterVO.setLastYear(lastYearMonthSumWater);
        return monthSunWaterVO;
    }

    @Override
    public YearSunWaterVO getYearSunWater(String code) {
        BigDecimal yearSumWater = baseMapper.getYearSumWater(code, DateTool.getYear());
        BigDecimal lastYearSumWater = baseMapper.getYearSumWater(code, DateTool.getLastYear());
        YearSunWaterVO yearSunWaterVO = new YearSunWaterVO();
        yearSunWaterVO.setYearSum(yearSumWater);
        yearSunWaterVO.setDiscrepancy(yearSumWater.subtract(lastYearSumWater));
        return yearSunWaterVO;
    }

    @Override
    public TableDataUtils<List<UseWaterStatisticsVO>> getUseWater(UseWaterStatisticsVO useWaterStatisticsVO) {
        String year = useWaterStatisticsVO.getYear();
        if (StringUtils.isEmpty(useWaterStatisticsVO.getYear())){
            year = String.valueOf(DateTool.getYear());
        }
        IPage<UseWaterStatisticsVO> page = baseMapper.getUseWater(useWaterStatisticsVO.page(), useWaterStatisticsVO.getCode(), year);
        List<UseWaterStatisticsVO> useWaterStatisticsVOS = Conversion.changeList(page.getRecords(), UseWaterStatisticsVO.class);
        for (UseWaterStatisticsVO waterStatisticsVO : useWaterStatisticsVOS) {
            waterStatisticsVO.setName(checkName(useWaterStatisticsVO.getCode(), waterStatisticsVO.getCode()));
        }
        return TableDataUtils.success(page.getTotal(), useWaterStatisticsVOS);
    }

    /**
     * 获取单位名称
     *
     * @param code 查询时传入的code
     * @param code1 查询数据库之后传回的code
     * @return 单位名称
     */
    private String checkName(String code, String code1) {
        if (StringUtils.isEmpty(code)){
            Map<String, String> allAreaName = iAreaService.getAllAreaName(code1);
            return allAreaName.get("shi");
        }else {
            Area area = iAreaService.lambdaQuery().likeRight(Area::getFatherCode, code).last("limit 1").one();
            return area.getName();
        }
    }
}
