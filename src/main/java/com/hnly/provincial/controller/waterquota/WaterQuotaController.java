package com.hnly.provincial.controller.waterquota;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.waterquota.WaterQuotaVO;
import com.hnly.provincial.service.waterquota.IWaterQuotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用水定额 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-28
 */
@Slf4j
@Tag(name = "用水定额")
@RestController
@RequestMapping("water-quota")
public class WaterQuotaController {

    @Resource
    private IWaterQuotaService waterQuotaService;

    @Operation(summary = "新增用水定额")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated WaterQuotaVO waterQuotaVO) {
        waterQuotaService.add(waterQuotaVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除用水定额")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        waterQuotaService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新用水定额")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated WaterQuotaVO waterQuotaVO) {
        waterQuotaService.updateData(waterQuotaVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询用水定额分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<WaterQuotaVO>>> findListByPage(WaterQuotaVO waterQuotaVO) {
        return JsonBean.success(waterQuotaService.findListByPage(waterQuotaVO));
    }

    @Operation(summary = "id查询用水定额")
    @GetMapping("{id}")
    public JsonBean<WaterQuotaVO> findById(@PathVariable Long id) {
        return JsonBean.success(waterQuotaService.findById(id));
    }
}
