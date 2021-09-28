package com.hnly.provincial.service.wateruserecords.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.date.DateTool;
import com.hnly.provincial.comm.user.CommonUser;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.wateruserecords.WaterUseRecordsMapper;
import com.hnly.provincial.entity.area.Area;
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
        IPage<WaterUseRecordsVO> page = baseMapper.findListByPage(findNameVO.page(), findNameVO.getCode(), findNameVO.getFarmerName(), findNameVO.getDeviceName(), findNameVO.getType());
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
        String status = checkStatus(code);
        IPage<UseWaterStatisticsVO> areaList = baseMapper.findUnit(useWaterStatisticsVO.page(), status, code);
        List<UseWaterStatisticsVO> waterUseRecordsVOs = Conversion.changeList(areaList.getRecords(), UseWaterStatisticsVO.class);
        for (UseWaterStatisticsVO vo : waterUseRecordsVOs) {
            BigDecimal useWaterLimit = CheckUseWaterLimit(year, vo.getCode());
            BigDecimal useWater = checkUseWater(year, vo.getCode());
            BigDecimal ratio = CheckUseWaterRatio(useWaterLimit, useWater);
            vo.setName(checkName(code, vo.getCode()));
            vo.setUseWaterLimit(useWaterLimit);
            vo.setUseWater(useWater);
            vo.setSurplus(useWaterLimit.subtract(useWater));
            vo.setUseWaterRatio(ratio.multiply(new BigDecimal("100")));
        }
        return TableDataUtils.success(areaList.getTotal(), waterUseRecordsVOs);
    }

    /**
     * 计算已用水量的百分比
     *
     * @param useWaterLimit 用水的额度
     * @param useWater      用掉的水量
     * @return 用水的百分比
     */
    private BigDecimal CheckUseWaterRatio(BigDecimal useWaterLimit, BigDecimal useWater) {
        BigDecimal ratio = new BigDecimal(0);
        if (!useWaterLimit.equals(BigDecimal.ZERO)) {
            ratio = useWater.divide(useWaterLimit).setScale(2, BigDecimal.ROUND_DOWN);
        }
        return ratio;
    }

    /**
     * 获取累计用水量
     *
     * @param year 年
     * @param code 区域规划
     * @return 累计用水;量
     */
    private BigDecimal checkUseWater(String year, String code) {
        BigDecimal useWater = baseMapper.getUseWater(checkCode(code), year);
        if (useWater != null) {
            useWater = useWater.setScale(2, BigDecimal.ROUND_DOWN);
        } else {
            useWater = new BigDecimal("0");
        }
        return useWater;
    }

    /**
     * 查询该区域的用水量定额
     *
     * @param year 年
     * @param code 区域规划
     * @return 用水量定额
     */
    private BigDecimal CheckUseWaterLimit(String year, String code) {
        BigDecimal useWaterLimit = baseMapper.getUseWaterLimit(year, checkCode(code));
        if (useWaterLimit == null) {
            useWaterLimit = new BigDecimal("0");
        }
        return useWaterLimit;
    }

    /**
     * 获取单位名称
     *
     * @param code   入参的区域规划
     * @param voCode 分类之后单位的区域规划
     * @return 返回区域规划的单位名称
     */
    private String checkName(String code, String voCode) {
        Map<String, String> allAreaName = iAreaService.getAllAreaName(voCode);
        Area one = iAreaService.lambdaQuery().likeRight(Area::getFatherCode, code).last("limit 1").one();
        if (one.getStatus() == null || one.getStatus().equals("0")) {
            return allAreaName.get("shi");
        } else if (one.getStatus().equals("1")) {
            return allAreaName.get("xian");
        } else if (one.getStatus().equals("2")) {
            return allAreaName.get("xiang");
        } else if (one.getStatus().equals("3")) {
            return allAreaName.get("cun");
        }
        return allAreaName.get("shi");
    }

    /**
     * 获取单位的类型
     *
     * @param code 行政区划
     * @return 返回单位的类型 0 市 1 县区 2 乡镇 3 村庄
     */
    private String checkStatus(String code) {
        if (code.length() <= 2) {
            return "0";
        } else if (code.length() <= 4) {
            return "1";
        } else if (code.length() <= 6) {
            return "2";
        } else if (code.length() >= 9) {
            return "3";
        }
        return "0";
    }

    /**
     * 条件:行政区划
     *
     * @param code 行政区划
     * @return
     */
    private String checkCode(String code) {
        if (!StringUtils.isEmpty(code)) {
            return commonUser.code(code);
        } else {
            return "41";
        }

    }

    /**
     * 条件:年
     *
     * @param year 年
     * @return 为空返回系统年, 返回
     */
    private String checkYear(String year) {
        if (StringUtils.isEmpty(year)) {
            return String.valueOf(DateTool.getYear());
        } else {
            return year;
        }
    }
}
