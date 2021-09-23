package com.hnly.provincial.dao.wateruserecords;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecords;

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
     * 查询这个地区这年每月的累计用水量
     *
     * @param code 区域
     * @param year 年份
     */
    List<Object> getMonthSumWater(String code, int year);
}
