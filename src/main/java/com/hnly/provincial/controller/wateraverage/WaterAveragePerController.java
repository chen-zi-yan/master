package com.hnly.provincial.controller.wateraverage;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateraverage.WaterAveragePerVO;
import com.hnly.provincial.service.wateraverage.IWaterAveragePerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 亩均定额 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-22
 */
@Slf4j
@Tag(name = "亩均定额")
@RestController
@RequestMapping("water-average-per")
public class WaterAveragePerController {

    @Resource
    private IWaterAveragePerService waterAveragePerService;

    @Operation(summary = "新增亩均定额")
    @PostMapping()
    public JsonBean<String> add(@RequestBody WaterAveragePerVO waterAveragePerVO) {
        waterAveragePerService.add(waterAveragePerVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除亩均定额")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        waterAveragePerService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新亩均定额")
    @PutMapping()
    public JsonBean<String> update(@RequestBody WaterAveragePerVO waterAveragePerVO) {
        waterAveragePerService.updateData(waterAveragePerVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询亩均定额分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<WaterAveragePerVO>>> findListByPage(WaterAveragePerVO waterAveragePerVO) {
        return JsonBean.success(waterAveragePerService.findListByPage(waterAveragePerVO));
    }

    @Operation(summary = "id查询亩均定额")
    @GetMapping("{id}")
    public JsonBean<WaterAveragePerVO> findById(@PathVariable Long id) {
        return JsonBean.success(waterAveragePerService.findById(id));
    }
}
