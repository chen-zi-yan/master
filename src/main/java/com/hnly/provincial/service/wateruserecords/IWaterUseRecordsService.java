package com.hnly.provincial.service.wateruserecords;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateruserecords.MonthSunWaterVO;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecords;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;
import com.hnly.provincial.entity.wateruserecords.YearSunWaterVO;

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
     * @param waterUseRecordsVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<WaterUseRecordsVO>> findListByPage(WaterUseRecordsVO waterUseRecordsVO);

    /**
     * 获取今年和去年的每月总用水量
     *
     * @param code 行政区划
     * @return 今年和去年的每月总用水量
     */
    MonthSunWaterVO getMonthSumWater(String code);

    /**
     * 获取今年的总用水量
     *
     * @param code 区域码
     * @return 今年的累计用水量
     */
    YearSunWaterVO getYearSunWater(String code);
}
