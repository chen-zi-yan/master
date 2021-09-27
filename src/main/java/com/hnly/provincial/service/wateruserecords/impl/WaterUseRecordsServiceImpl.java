package com.hnly.provincial.service.wateruserecords.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.date.DateTool;
import com.hnly.provincial.comm.user.CommonUser;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.wateruserecords.WaterUseRecordsMapper;
import com.hnly.provincial.entity.wateruserecords.*;
import com.hnly.provincial.service.area.IAreaService;
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
    private CommonUser commonUser;

    @Override
    public TableDataUtils<List<WaterUseRecordsVO>> findListByPage(FindNameVO findNameVO) {
        IPage<WaterUseRecordsVO> page = baseMapper.findListByPage(findNameVO.page(), findNameVO.getCode(), findNameVO.getFarmerName(),findNameVO.getDeviceName(), findNameVO.getType());
        List<WaterUseRecordsVO> waterUseRecordsVOs = Conversion.changeList(page.getRecords(), WaterUseRecordsVO.class);
        for (WaterUseRecordsVO vo : waterUseRecordsVOs) {
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
        String year = checkYear(useWaterStatisticsVO.getYear());
        String code = checkCode(useWaterStatisticsVO.getCode());

        IPage<UseWaterStatisticsVO> page = baseMapper.getUseWater(useWaterStatisticsVO.page(), code, year);

        List<UseWaterStatisticsVO> useWaterStatisticsVOS = Conversion.changeList(page.getRecords(), UseWaterStatisticsVO.class);
        for (UseWaterStatisticsVO vo : useWaterStatisticsVOS) {

        }

        return TableDataUtils.success(page.getTotal(), useWaterStatisticsVOS);
    }

    /**
     * 条件:行政区划
     *
     * @param code 行政区划
     * @return
     */
    private String checkCode(String code) {
        if (!StringUtils.isEmpty(code)){
            return commonUser.code(code);
        }else {
            return code;
        }

    }

    /**
     * 条件:年
     *
     * @param year 年
     * @return 为空返回系统年,返回
     */
    private String checkYear(String year) {
        if (StringUtils.isEmpty(year)){
            return String.valueOf(DateTool.getYear());
        }else {
            return year;
        }
    }
}
