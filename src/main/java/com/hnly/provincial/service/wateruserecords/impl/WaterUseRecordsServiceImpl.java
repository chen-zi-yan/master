package com.hnly.provincial.service.wateruserecords.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.date.DateTool;
import com.hnly.provincial.comm.user.CommonUser;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.wateruserecords.WaterUseRecordsMapper;
import com.hnly.provincial.entity.area.AreaName;
import com.hnly.provincial.entity.wateruserecords.*;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.wateruserecords.IWaterUseRecordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Resource
    private IAreaService iAreaService;

    @Resource
    private CommonUser commonUser;

    @Override
    public TableDataUtils<List<WaterUseRecordsVO>> findListByPage(FindNameVO findNameVO) {
        IPage<WaterUseRecordsVO> page = baseMapper.findListByPage(findNameVO.page(), findNameVO.getCode(), findNameVO.getFarmerName(), findNameVO.getDevSn(), findNameVO.getType());
        List<WaterUseRecordsVO> waterUseRecordsVO = Conversion.changeList(page.getRecords(), WaterUseRecordsVO.class);
        for (WaterUseRecordsVO vo : waterUseRecordsVO) {
            AreaName allAreaName = iAreaService.getAllAreaName(vo.getCode());
            vo.setCityName(allAreaName.getShiName());
            vo.setCountyName(allAreaName.getXianName());
            vo.setTownshipName(allAreaName.getXiangName());
            vo.setVillageName(allAreaName.getCunName());
        }
        return TableDataUtils.success(page.getTotal(), waterUseRecordsVO);
    }

    @Override
    public MonthSumWaterVO getMonthSumWater(String code) {
        List<MonthSumWaterByYearAndCodeVO> monthSumWater = baseMapper.getMonthSumWater(code, DateTool.getYear());
        List<MonthSumWaterByYearAndCodeVO> lastYearMonthSumWater = baseMapper.getMonthSumWater(code, DateTool.getLastYear());
        MonthSumWaterVO monthSumWaterVO = new MonthSumWaterVO();
        monthSumWaterVO.setYear(getMonthSumWaterByYearAndCode(monthSumWater));
        monthSumWaterVO.setLastYear(getMonthSumWaterByYearAndCode(lastYearMonthSumWater));
        return monthSumWaterVO;
    }

    @Override
    public YearSumWaterVO getYearSumWater(String code) {
        BigDecimal yearSumWater = getSumWater(baseMapper.getYearSumWater(code, DateTool.getYear()));
        BigDecimal lastYearSumWater = getSumWater(baseMapper.getYearSumWater(code, DateTool.getLastYear()));
        YearSumWaterVO yearSumWaterVO = new YearSumWaterVO();
        yearSumWaterVO.setYearSum(yearSumWater);
        yearSumWaterVO.setDiscrepancy(yearSumWater.subtract(lastYearSumWater));
        return yearSumWaterVO;
    }

    @Override
    public TableDataUtils<List<UseWaterStatisticsVO>> getUseWater(UseWaterStatisticsVO useWaterStatisticsVO) {
        String year = getYear(useWaterStatisticsVO.getYear());
        String code = commonUser.code(useWaterStatisticsVO.getCode());
        String status = getStatus(code);
        Long total = null;
        TableDataUtils<List<UseWaterStatisticsVO>> listTableDataUtils = checkSumWater(useWaterStatisticsVO.page(), year, code, status, total);
        return TableDataUtils.success(listTableDataUtils.getTotal(), listTableDataUtils.getData());
    }

    @Override
    public TodayUseWaterAndNumberVO getTodayUseWaterAndNumber(String code) {
        return baseMapper.getTodayUseWaterAndNumber(code, DateTool.getDate());
    }

    @Override
    public BigDecimal getTodayUseWaterPeople(String code) {
        return baseMapper.getTodayUseWaterPeople(code, DateTool.getDate());
    }

    @Override
    public FarmerNumberAndSumUseWaterAndWellOpeningNumberVO getFarmerNumberAndSumUseWaterAndWellOpeningNumber(String code) {
        FarmerNumberAndSumUseWaterAndWellOpeningNumberVO farmerNumberAndSumUseWaterAndWellOpeningNumberVO = new FarmerNumberAndSumUseWaterAndWellOpeningNumberVO();
        BigDecimal sumUseWaterPeople = baseMapper.getFarmerNumber(code, DateTool.getYear());
        FarmerNumberAndSumUseWaterAndWellOpeningNumberVO accumulativeUseWaterAndNumber = baseMapper.getSumUseWaterAndWellOpeningNumber(code, DateTool.getYear());
        farmerNumberAndSumUseWaterAndWellOpeningNumberVO.setSumUseWaterFarmer(sumUseWaterPeople);
        farmerNumberAndSumUseWaterAndWellOpeningNumberVO.setSumUseWater(accumulativeUseWaterAndNumber.getSumUseWater());
        farmerNumberAndSumUseWaterAndWellOpeningNumberVO.setWellOpening(accumulativeUseWaterAndNumber.getWellOpening());
        return farmerNumberAndSumUseWaterAndWellOpeningNumberVO;
    }

    @Override
    public ArrayList<Object> getMonthSumWaterByYearAndCode(Long year, String code) {
        if (year == null) {
            year = (long) DateTool.getYear();
        }
        List<MonthSumWaterByYearAndCodeVO> monthSumWaterByYearAndCodeVO = baseMapper.getMonthSumWaterByYearAndCode(year, commonUser.code(code));
        return getMonthSumWaterByYearAndCode(monthSumWaterByYearAndCodeVO);
    }

    @Override
    public SummationVO getSummation(String year, String code) {
        SummationVO summationVO = new SummationVO();
        BigDecimal useWaterLimit = getUseWaterLimit(year, code);
        BigDecimal useWater = getUseWater(year, code);
        summationVO.setSummationUseWaterLimit(useWaterLimit);
        summationVO.setSummationUseWater(useWater);
        summationVO.setSummationSurplus(useWaterLimit.subtract(useWater));
        summationVO.setSummationUseWaterRatio(checkUseWaterRatio(useWaterLimit, useWater));
        return summationVO;
    }

    /**
     * 获取该区域该年每月的累计用水量
     *
     * @param monthSumWaterByYearAndCodeVO 月份和用水量对象
     * @return 1到12月的用水数组
     */
    private ArrayList<Object> getMonthSumWaterByYearAndCode(List<MonthSumWaterByYearAndCodeVO> monthSumWaterByYearAndCodeVO) {
        ArrayList<Object> objects = new ArrayList<>(12);
        objects.addAll(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        for (MonthSumWaterByYearAndCodeVO vo : monthSumWaterByYearAndCodeVO) {
            if ("1".equals(vo.getMonth())) {
                objects.set(0, vo.getUseWater());
            } else if ("2".equals(vo.getMonth())) {
                objects.set(1, vo.getUseWater());
            } else if ("3".equals(vo.getMonth())) {
                objects.set(2, vo.getUseWater());
            } else if ("4".equals(vo.getMonth())) {
                objects.set(3, vo.getUseWater());
            } else if ("5".equals(vo.getMonth())) {
                objects.set(4, vo.getUseWater());
            } else if ("6".equals(vo.getMonth())) {
                objects.set(5, vo.getUseWater());
            } else if ("7".equals(vo.getMonth())) {
                objects.set(6, vo.getUseWater());
            } else if ("8".equals(vo.getMonth())) {
                objects.set(7, vo.getUseWater());
            } else if ("9".equals(vo.getMonth())) {
                objects.set(8, vo.getUseWater());
            } else if ("10".equals(vo.getMonth())) {
                objects.set(9, vo.getUseWater());
            } else if ("11".equals(vo.getMonth())) {
                objects.set(10, vo.getUseWater());
            } else if ("12".equals(vo.getMonth())) {
                objects.set(11, vo.getUseWater());
            }
        }
        return objects;
    }

    /**
     * 获取已用水量额度,可用水额度-计算出-剩余用水,用水量的占比
     *
     * @param page   分页
     * @param year   年
     * @param code   区域规划
     * @param status 区域划分类型
     * @param total  列表数量
     * @return 用水列表
     */
    private TableDataUtils<List<UseWaterStatisticsVO>> checkSumWater(Page<WaterUseRecords> page, String year, String code, String status, Long total) {
        TableDataUtils<List<UseWaterStatisticsVO>> listTableDataUtils = new TableDataUtils<>(total, null);
        if (status.equals(code)) {
            IPage<UseWaterStatisticsVO> farmerList = baseMapper.findByCode(page, code);
            for (UseWaterStatisticsVO vo : farmerList.getRecords()) {
                BigDecimal farmerWaterLimit = checkWaterExist(vo.getId(), year);
                BigDecimal farmerSumWater = getFarmerSumWater(year, vo.getId());
                vo.setUseWaterLimit(farmerWaterLimit);
                vo.setUseWater(farmerSumWater);
                vo.setSurplus(farmerWaterLimit.subtract(farmerSumWater));
                vo.setUseWaterRatio(checkUseWaterRatio(farmerWaterLimit, farmerSumWater));
            }
            listTableDataUtils.setTotal(farmerList.getTotal());
            listTableDataUtils.setData(farmerList.getRecords());
        } else {
            IPage<UseWaterStatisticsVO> areaList = baseMapper.findUnit(page, status, code);
            for (UseWaterStatisticsVO vo : areaList.getRecords()) {
                BigDecimal useWaterLimit = getUseWaterLimit(year, vo.getCode());
                BigDecimal useWater = getUseWater(year, vo.getCode());
                vo.setUseWaterLimit(useWaterLimit);
                vo.setUseWater(useWater);
                vo.setSurplus(useWaterLimit.subtract(useWater));
                vo.setUseWaterRatio(checkUseWaterRatio(useWaterLimit, useWater));
            }
            listTableDataUtils.setTotal(areaList.getTotal());
            listTableDataUtils.setData(areaList.getRecords());
        }
        return listTableDataUtils;
    }

    /**
     * 获取农户用水额度
     *
     * @param id   id
     * @param year 年
     * @return 农户用水额度
     */
    private BigDecimal checkWaterExist(String id, String year) {
        BigDecimal farmerWaterLimit;
        FindFarmerWaterQuota farmerWaterLimits = baseMapper.getFarmerWaterLimit(id, year);
        if (farmerWaterLimits != null) {
            if (farmerWaterLimits.getFirstOrderTotal() == null && farmerWaterLimits.getSecondOrderTotal() != null) {
                farmerWaterLimit = farmerWaterLimits.getSecondOrderTotal();
            } else if (farmerWaterLimits.getFirstOrderTotal() != null && farmerWaterLimits.getSecondOrderTotal() == null) {
                farmerWaterLimit = farmerWaterLimits.getFirstOrderTotal();
            } else if (farmerWaterLimits.getFirstOrderTotal() != null && farmerWaterLimits.getSecondOrderTotal() != null) {
                farmerWaterLimit = farmerWaterLimits.getFirstOrderTotal().add((farmerWaterLimits.getSecondOrderTotal()));
            } else {
                farmerWaterLimit = new BigDecimal("0");
            }
        } else {
            farmerWaterLimit = new BigDecimal("0");
        }
        return farmerWaterLimit;
    }

    /**
     * 获取该农户该年累计用水量
     *
     * @param year 年
     * @param id   农户id
     * @return 该农户该年累计用水量
     */
    private BigDecimal getFarmerSumWater(String year, String id) {
        BigDecimal useWater;
        BigDecimal farmerSumWater = baseMapper.getFarmerSumWater(year, id);
        if (farmerSumWater != null) {
            useWater = farmerSumWater.setScale(2, RoundingMode.DOWN);
        } else {
            useWater = new BigDecimal("0");
        }
        return useWater;
    }

    /**
     * 保留两位小数,不做舍入舍出
     *
     * @param water 数据
     * @return 保留两位小数
     */
    private BigDecimal getSumWater(BigDecimal water) {
        if (water != null) {
            return water.setScale(2, RoundingMode.DOWN);
        }
        return new BigDecimal("0");
    }


    /**
     * 计算已用水量的百分比
     *
     * @param useWaterLimit 用水的额度
     * @param useWater      用掉的水量
     * @return 用水的百分比
     */
    private BigDecimal checkUseWaterRatio(BigDecimal useWaterLimit, BigDecimal useWater) {
        BigDecimal ratio;
        if (useWaterLimit.compareTo(BigDecimal.ZERO) == 0 || useWater.compareTo(BigDecimal.ZERO) == 0) {
            ratio = new BigDecimal(0);
        } else {
            ratio = useWater.divide(useWaterLimit, 2, RoundingMode.DOWN).multiply(new BigDecimal("100"));
        }
        return ratio;
    }

    /**
     * 获取该区域该年累计用水量
     *
     * @param year 年
     * @param code 区域规划
     * @return 累计用水;量
     */
    private BigDecimal getUseWater(String year, String code) {
        BigDecimal useWater;
        BigDecimal sumUseWater = baseMapper.getUseWater(commonUser.code(code), year);
        if (sumUseWater != null) {
            useWater = sumUseWater.setScale(2, RoundingMode.DOWN);
        } else {
            useWater = new BigDecimal("0");
        }
        return useWater;
    }

    /**
     * 查询该区域该年用水量定额
     *
     * @param year 年
     * @param code 区域规划
     * @return 用水量定额
     */
    private BigDecimal getUseWaterLimit(String year, String code) {
        BigDecimal useWaterLimit;
        BigDecimal useWaterLimitValue = baseMapper.getUseWaterLimit(year, commonUser.code(code));
        if (useWaterLimitValue != null) {
            useWaterLimit = useWaterLimitValue;
        } else {
            useWaterLimit = new BigDecimal("0");
        }
        return useWaterLimit;
    }

    /**
     * 获取单位的类型
     *
     * @param code 行政区划
     * @return 返回单位的类型 0 市 1 县区 2 乡镇 3 村庄
     */
    private String getStatus(String code) {
        if (code.length() <= 2) {
            return "0";
        } else if (code.length() <= 4) {
            return "1";
        } else if (code.length() <= 6) {
            return "2";
        } else if (code.length() < 12) {
            return "3";
        } else if (code.length() == 12) {
            return code;
        }
        return "0";
    }

    /**
     * 条件:年
     *
     * @param year 年
     * @return 为空返回系统年, 返回
     */
    private String getYear(String year) {
        if (StringUtils.isEmpty(year)) {
            return String.valueOf(DateTool.getYear());
        } else {
            return year;
        }
    }
}
