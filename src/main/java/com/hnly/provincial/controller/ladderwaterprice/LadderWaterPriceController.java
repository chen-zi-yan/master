package com.hnly.provincial.controller.ladderwaterprice;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.ladderwaterprice.LadderWaterPriceVO;
import com.hnly.provincial.service.ladderwaterprice.ILadderWaterPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 阶梯水价表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Slf4j
@Tag(name = "阶梯水价表")
@RestController
@RequestMapping("ladder-water-price")
public class LadderWaterPriceController {

    @Resource
    private ILadderWaterPriceService ladderWaterPriceService;

    @Operation(summary = "新增阶梯水价表")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated LadderWaterPriceVO ladderWaterPriceVO) {
        ladderWaterPriceService.add(ladderWaterPriceVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除阶梯水价表")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        ladderWaterPriceService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新阶梯水价表")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated LadderWaterPriceVO ladderWaterPriceVO) {
        ladderWaterPriceService.updateData(ladderWaterPriceVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询阶梯水价表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<LadderWaterPriceVO>>> findListByPage(LadderWaterPriceVO ladderWaterPriceVO) {
        return JsonBean.success(ladderWaterPriceService.findListByPage(ladderWaterPriceVO));
    }

    @Operation(summary = "id查询阶梯水价表")
    @GetMapping("{id}")
    public JsonBean<LadderWaterPriceVO> findById(@PathVariable Long id) {
        return JsonBean.success(ladderWaterPriceService.findById(id));
    }
}
