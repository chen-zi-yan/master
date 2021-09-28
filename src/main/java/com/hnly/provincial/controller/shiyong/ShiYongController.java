package com.hnly.provincial.controller.shiyong;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.shiyong.ShiYongVO;
import com.hnly.provincial.service.shiyong.IShiYongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 使用权证 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-14
 */
@Slf4j
@Tag(name = "使用权证")
@RestController
@RequestMapping("shiYong")
public class ShiYongController {

    @Resource
    private IShiYongService shiYongService;

    @Operation(summary = "新增使用权证")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated ShiYongVO shiYongVO) {
        shiYongService.add(shiYongVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除使用权证")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        shiYongService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新使用权证")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated ShiYongVO shiYongVO) {
        shiYongService.updateData(shiYongVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询使用权证分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<ShiYongVO>>> findListByPage(ShiYongVO shiYongVO) {
        return JsonBean.success(shiYongService.findListByPage(shiYongVO));
    }

    @Operation(summary = "id查询使用权证")
    @GetMapping("{id}")
    public JsonBean<ShiYongVO> findById(@PathVariable Long id) {
        return JsonBean.success(shiYongService.findById(id));
    }
}
