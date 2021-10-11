package com.hnly.provincial.dao.wateruserecords;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnly.provincial.entity.wateruserecords.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用水记录表 Mapper 接口
 * </p>
 *
 * @author czy
 * @since 2021-09-23
 */
public interface WaterUseRecordsMapper extends BaseMapper<WaterUseRecords> {
    /**
     * 统计这年与上年每月的总用水量
     *
     * @param code 区域规划
     * @param year 年
     * @return 每月的累计用水量
     */
    List<Object> getMonthSumWater(String code, int year);

    /**
     * 统计今年累计用水量和今年与去年累计用水量的差值
     *
     * @param code 区域规划
     * @param year 年
     * @return 累计用水量
     */
    BigDecimal getYearSumWater(String code, int year);

    /**
     * 获取该区域下该年累计已用水量
     *
     * @param code 区域规划
     * @param year 年
     * @return 以区域规划分组获取已用水量
     */
    BigDecimal getUseWater(String code, String year);

    /**
     * 获取行政区划
     *
     * @param page   分页
     * @param status 单位类型
     * @param code   行政区划
     * @return 行政区划对象
     */
    IPage<UseWaterStatisticsVO> findUnit(Page<WaterUseRecords> page, String status, String code);

    /**
     * 获取地区该年的用水额度
     *
     * @param year 年
     * @param code 行政区划
     * @return 该地区的用水额度
     */
    BigDecimal getUseWaterLimit(String year, String code);

    /**
     * 查询区域用水记录表分页数据
     *
     * @param page       分页
     * @param code       区域规划
     * @param farmerName 农户名称
     * @param deviceName 设备名称
     * @param type       设备类型
     * @return WaterUseRecordsVO对象
     */
    IPage<WaterUseRecordsVO> findListByPage(@Param("page") Page<WaterUseRecords> page, @Param("code") String code, @Param("farmerName") String farmerName, @Param("deviceName") String deviceName, @Param("type") String type);

    /**
     * 查询农户用水记录表分页数据
     *
     * @param page 分页
     * @param code 行政区划
     * @return 返回农户对象
     */
    IPage<UseWaterStatisticsVO> findByCode(Page<WaterUseRecords> page, String code);

    /**
     * 获取农户该年的累计用水量
     *
     * @param year     年
     * @param farmerId 农户Id
     * @return 农户该年的累计用水量
     */
    BigDecimal getFarmerSumWater(String year, String farmerId);

    /**
     * 获取农户的用水额度
     *
     * @param farmerId 农户id
     * @param year     年
     * @return 农户用水额度
     */
    FindFarmerWaterQuota getFarmerWaterLimit(String farmerId, String year);

    /**
     * 获取该区域下今日的累计用水量和今日的用水次数
     *
     * @param code 行政区划
     * @param date 时间年月日
     * @return 今日累计用水量
     */
    TodayUseWaterAndNumberVO getTodayUseWaterAndNumber(String code, String date);

    /**
     * 获取该区域下今日用水人数
     *
     * @param code 行政区划
     * @param date 日期年月日
     * @return 今日用水人数
     */
    BigDecimal getTodayUseWaterPeople(String code, String date);

    /**
     * 获取该区域的和累计灌溉开井次数和累计用水量
     *
     * @param code 行政区划
     * @param year 年
     * @return 累计灌溉开井次数和累计用水量
     */
    FarmerNumberAndSumUseWaterAndWellOpeningNumberVO getSumUseWaterAndWellOpeningNumber(String code, Integer year);

    /**
     * 获取该区域的累计用水农户数
     *
     * @param code 行政区划
     * @param year 年
     * @return 累计用水农户数
     */
    BigDecimal getFarmerNumber(String code, Integer year);

    /**
     * 获取该区域该年每月的累计用水量
     *
     * @param year 年
     * @param code 区域
     * @return 该区域该年每月的累计用水量
     */
    List<BigDecimal> getMonthSumWaterByYear(Integer year, String code);
}
