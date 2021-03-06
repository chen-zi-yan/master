package com.hnly.provincial.service.wateruserecords;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateruserecords.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用水记录表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-23
 */
public interface IWaterUseRecordsService extends IService<WaterUseRecords> {

    /**
     * 查询用水记录表分页数据
     *
     * @param findNameVO 入参对象
     * @return 分页结果
     */
    TableDataUtils<List<WaterUseRecordsVO>> findListByPage(FindNameVO findNameVO);

    /**
     * 获取今年和去年的每月总用水量
     *
     * @param code 行政区划
     * @return 今年和去年的每月总用水量
     */
    MonthSumWaterVO getMonthSumWater(String code);

    /**
     * 获取今年的总用水量
     *
     * @param code 区域码
     * @return 今年的累计用水量
     */
    YearSumWaterVO getYearSumWater(String code);

    /**
     * 获取该区域下累计已用水量\剩余水量\已用占比
     *
     * @param useWaterStatisticsVO 实体类对象
     * @return 累计已用水量\剩余水量\已用占比
     */
    TableDataUtils<List<UseWaterStatisticsVO>> getUseWater(UseWaterStatisticsVO useWaterStatisticsVO);

    /**
     * 获取该区域下今日的累计用水量和今日的用水次数
     *
     * @param code 行政区划
     * @return 今日累计用水量
     */
    TodayUseWaterAndNumberVO getTodayUseWaterAndNumber(String code);

    /**
     * 获取河南省该年每月的累计用水量
     *
     * @param code 行政区划
     * @return 今日用水人数
     */
    BigDecimal getTodayUseWaterPeople(String code);

    /**
     * 获取该区域的累计用水农户数和累计灌溉开井次数和累计用水量
     *
     * @param code 行政区划
     * @return 累计用水农户数和累计灌溉开井次数和累计用水量
     */
    FarmerNumberAndSumUseWaterAndWellOpeningNumberVO getFarmerNumberAndSumUseWaterAndWellOpeningNumber(String code);

    /**
     * 获取该区域该年每月的累计用水量
     *
     * @param year 年
     * @param code 区域
     * @return 该区域该年每月的累计用水量
     */
    ArrayList<Object> getMonthSumWaterByYearAndCode(Long year, String code);

    /**
     * 获取合计用水额度合计已用水量合计剩余水量合计已用占比
     *
     * @param year 年
     * @param code 行政区划
     * @return 合计用水额度-合计已用水量-合计剩余水量-合计已用占比
     */
    SummationVO getSummation(String year, String code);
}
