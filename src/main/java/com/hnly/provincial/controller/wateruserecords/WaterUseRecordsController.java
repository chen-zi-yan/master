package com.hnly.provincial.controller.wateruserecords;

import com.alibaba.druid.util.StringUtils;
import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.user.CommonUser;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateruserecords.*;
import com.hnly.provincial.service.wateruserecords.IWaterUseRecordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    public JsonBean<TableDataUtils<List<WaterUseRecordsVO>>> findListByPage(FindNameVO findNameVO) {
        return JsonBean.success(waterUseRecordsService.findListByPage(findNameVO));
    }

    @Tag(name = "统计")
    @Operation(summary = "统计这年与上年每月的总用水量")
    @GetMapping("getMonthSumWater")
    public JsonBean<MonthSumWaterVO> getMonthSumWater(String code) {
        if (StringUtils.isEmpty(code)) {
            code = commonUser.getUserCode();
        }
        return JsonBean.success(waterUseRecordsService.getMonthSumWater(code));
    }

    @Tag(name = "统计")
    @Operation(summary = "统计今年累计用水量和今年与去年累计用水量的差值")
    @GetMapping("getYearSumWater")
    public JsonBean<YearSumWaterVO> getYearSumWater(String code) {
        if (StringUtils.isEmpty(code)) {
            code = commonUser.getUserCode();
        }
        return JsonBean.success(waterUseRecordsService.getYearSumWater(code));
    }

    @Tag(name = "统计")
    @Operation(summary = "根据区域规划获取各个地方的累计用水量(具体到个人)")
    @GetMapping("getUseWater")
    public JsonBean<TableDataUtils<List<UseWaterStatisticsVO>>> getUseWater(UseWaterStatisticsVO useWaterStatisticsVO) {
        return JsonBean.success(waterUseRecordsService.getUseWater(useWaterStatisticsVO));
    }

    @Tag(name = "统计")
    @Operation(summary = "获取该区域下今日的累计用水量和今日的用水次数")
    @GetMapping("getTodayUseWaterAndNumber")
    public JsonBean<TodayUseWaterAndNumberVO> getTodayUseWaterAndNumber(String code) {
        if (StringUtils.isEmpty(code)) {
            code = commonUser.getUserCode();
        }
        return JsonBean.success(waterUseRecordsService.getTodayUseWaterAndNumber(code));
    }

    @Tag(name = "统计")
    @Operation(summary = "获取该区域下今日用水人数")
    @GetMapping("getTodayUseWaterPeople")
    public JsonBean<BigDecimal> getTodayUseWaterPeople(String code) {
        if (StringUtils.isEmpty(code)) {
            code = commonUser.getUserCode();
        }
        return JsonBean.success(waterUseRecordsService.getTodayUseWaterPeople(code));
    }


}
