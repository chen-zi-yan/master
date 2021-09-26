package com.hnly.provincial.controller.wateruserecords;

import com.alibaba.druid.util.StringUtils;
import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.user.CommonUser;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateruserecords.MonthSunWaterVO;
import com.hnly.provincial.entity.wateruserecords.UseWaterStatisticsVO;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;
import com.hnly.provincial.entity.wateruserecords.YearSunWaterVO;
import com.hnly.provincial.service.wateruserecords.IWaterUseRecordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用水记录表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-23
 */
@Slf4j
@Tag(name = "用水记录表")
@RestController
@RequestMapping("water-use-records")
public class WaterUseRecordsController {

    @Resource
    private IWaterUseRecordsService waterUseRecordsService;

    @Resource
    private CommonUser commonUser;

    @Operation(summary = "查询用水记录表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<WaterUseRecordsVO>>> findListByPage(WaterUseRecordsVO waterUseRecordsVO) {
        return JsonBean.success(waterUseRecordsService.findListByPage(waterUseRecordsVO));
    }

    @Tag(name = "统计")
    @Operation(summary = "统计这年与上年每月的总用水量")
    @GetMapping("getMonthSumWater")
    public JsonBean<MonthSunWaterVO> getMonthSumWater(String code) {
        if (StringUtils.isEmpty(code)) {
            code = commonUser.getUserCode();
        }
        return JsonBean.success(waterUseRecordsService.getMonthSumWater(code));
    }

    @Tag(name = "统计")
    @Operation(summary = "统计今年累计用水量和今年与去年累计用水量的差值")
    @GetMapping("getYearSunWater")
    public JsonBean<YearSunWaterVO> getYearSunWater(String code) {
        if (StringUtils.isEmpty(code)) {
            code = commonUser.getUserCode();
        }
        return JsonBean.success(waterUseRecordsService.getYearSunWater(code));
    }

    @Tag(name = "统计")
    @Operation(summary = "根据区域规划获取各个地方的累计用水量")
    @GetMapping("getUseWater")
    public JsonBean<TableDataUtils<List<UseWaterStatisticsVO>>> getUseWater(UseWaterStatisticsVO useWaterStatisticsVO) {
        return JsonBean.success(waterUseRecordsService.getUseWater(useWaterStatisticsVO));
    }

}
