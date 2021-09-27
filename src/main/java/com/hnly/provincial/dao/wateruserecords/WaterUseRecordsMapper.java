package com.hnly.provincial.dao.wateruserecords;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecords;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;
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
     * 获取该区域下累计已用水量
     *
     * @param code 区域规划
     * @param year 年
     * @return 以区域规划分组获取已用水量
     */
    String getUseWater(String code, String year);

    /**
     * 获取行政区划
     *
     * @param status 行政区划类型
     * @return 行政区划
     */
    List<Object> findUnit(String status);

    /**
     * 查询用水记录表分页数据
     *
     * @param page 分页
     * @param code 区域规划
     * @param farmerName 农户名称
     * @param deviceName 设备名称
     * @param type 设备类型
     * @return WaterUseRecordsVO对象
     */
    IPage<WaterUseRecordsVO> findListByPage(@Param("page") IPage page, @Param("code") String code, @Param("farmerName") String farmerName, @Param("deviceName") String deviceName, @Param("type") String type);

}
