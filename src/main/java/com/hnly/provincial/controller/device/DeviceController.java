package com.hnly.provincial.controller.device;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.device.DeviceVO;
import com.hnly.provincial.service.device.IDeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 设备信息表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-13
 */
@Slf4j
@Tag(name = "设备信息表")
@RestController
@RequestMapping("device")
public class DeviceController {

    @Resource
    private IDeviceService deviceService;

    @Operation(summary = "新增设备信息表")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated DeviceVO deviceVO) {
        deviceService.add(deviceVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除设备信息表")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        deviceService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新设备信息表")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated DeviceVO deviceVO) {
        deviceService.updateData(deviceVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询设备信息表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<DeviceVO>>> findListByPage(DeviceVO deviceVO) {
        return JsonBean.success(deviceService.findListByPage(deviceVO));
    }

    @Operation(summary = "id查询设备信息表")
    @GetMapping("{id}")
    public JsonBean<DeviceVO> findById(@PathVariable Long id) {
        return JsonBean.success(deviceService.findById(id));
    }
}
