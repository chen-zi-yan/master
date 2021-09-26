package com.hnly.provincial.dao.wateruserecords;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hnly.provincial.entity.wateruserecords.UseWaterStatisticsVO;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecords;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;

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
     * 获取该区域下累计已用水量
     *
     * @param page 分页
     * @param code 区域规划
     * @param year 年
     * @return 以区域规划分组获取已用水量
     */
    IPage<UseWaterStatisticsVO> getUseWater(IPage page, String code, String year);

    /**
     * 模糊查询设备名称
     *
     * @param page 分页
     * @param deviceName
     * @return
     */
    IPage<WaterUseRecordsVO> findByDeviceName(IPage page, String deviceName);

    /**
     * 模糊查询农户名称
     *
     * @param page
     * @param farmerName
     * @return
     */
    IPage<WaterUseRecordsVO> findByFarmerName(IPage page, String farmerName);
}
