package com.hnly.provincial.controller.wateruserecords;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;
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

    @Operation(summary = "查询用水记录表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<WaterUseRecordsVO>>> findListByPage(WaterUseRecordsVO waterUseRecordsVO) {
        return JsonBean.success(waterUseRecordsService.findListByPage(waterUseRecordsVO));
    }

}
