package com.hnly.provincial.controller.shiyong;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.shiyong.ShiyongVO;
import com.hnly.provincial.service.shiyong.IShiyongService;
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
@RequestMapping("shiyong")
public class ShiyongController {

    @Resource
    private IShiyongService shiyongService;

    @Operation(summary = "新增使用权证")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated ShiyongVO shiyongVO){
        shiyongService.add(shiyongVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除使用权证")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id){
        shiyongService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新使用权证")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated ShiyongVO shiyongVO){
        shiyongService.updateData(shiyongVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询使用权证分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<ShiyongVO>>> findListByPage(ShiyongVO shiyongVO){
        return JsonBean.success(shiyongService.findListByPage(shiyongVO));
    }

    @Operation(summary = "id查询使用权证")
    @GetMapping("{id}")
    public JsonBean<ShiyongVO> findById(@PathVariable Long id){
        return JsonBean.success(shiyongService.findById(id));
    }
}
