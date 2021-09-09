package com.hnly.provincial.controller.farmer;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.validation.Add;
import com.hnly.provincial.comm.validation.Update;
import com.hnly.provincial.entity.farmer.FarmerVO;
import com.hnly.provincial.service.farmer.IFarmerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.List;

/**
 * <p>
 * 农户表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-08
 */
@Slf4j
@Tag(name = "农户表")
@RestController
@RequestMapping("farmer")
public class FarmerController {

    @Resource
    private IFarmerService farmerService;

    @Operation(summary = "新增农户表")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated({Add.class, Default.class}) FarmerVO farmerVO) {
        farmerService.add(farmerVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除农户表")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        farmerService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新农户表")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated({Update.class, Default.class}) FarmerVO farmerVO) {
        farmerService.updateData(farmerVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询农户表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<FarmerVO>>> findListByPage(@RequestBody FarmerVO farmerVO) {
        return JsonBean.success(farmerService.findListByPage(farmerVO));
    }

    @Operation(summary = "id查询农户表")
    @GetMapping("{id}")
    public JsonBean<FarmerVO> findById(@PathVariable Long id) {
        return JsonBean.success(farmerService.findById(id));
    }
}
