package com.hnly.provincial.controller.ic;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.ic.IcUpdateStatusVO;
import com.hnly.provincial.entity.ic.IcVO;
import com.hnly.provincial.service.ic.IIcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 卡号表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Slf4j
@Tag(name = "卡号表")
@RestController
@RequestMapping("ic")
public class IcController {

    @Resource
    private IIcService icService;

    @Operation(summary = "新增卡号表")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated IcVO icVO) {
        icService.add(icVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除卡号表")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        icService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新卡号表")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated IcVO icVO) {
        icService.updateData(icVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询卡号表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<IcVO>>> findListByPage(IcVO icVO) {
        return JsonBean.success(icService.findListByPage(icVO));
    }

    @Operation(summary = "id查询卡号表")
    @GetMapping("{id}")
    public JsonBean<IcVO> findById(@PathVariable Long id) {
        return JsonBean.success(icService.findById(id));
    }

    @Operation(summary = "更新卡状态")
    @PutMapping("updateStatus")
    public JsonBean<String> updateStatus(IcUpdateStatusVO icUpdateStatusVO) {
        icService.updateStatus(icUpdateStatusVO.getStatus(), icUpdateStatusVO.getId());
        return JsonBean.success();
    }
}
