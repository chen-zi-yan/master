package com.hnly.provincial.controller.area;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.validation.Add;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaVO;
import com.hnly.provincial.service.area.IAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-01
 */
@RestController
@RequestMapping("/area")
@Tag(name = "区域号管理接口")
public class AreaController {

    @Resource
    private IAreaService iAreaServicel;

    @Operation(summary = "根据ID查询")
    @GetMapping("/getAreaById")
    public JsonBean<Area> getAreaById(Long id) {
        Area byId = iAreaServicel.getById(id);
        if (byId == null) {
            return JsonBean.success(ResultEnum.NOTHINGNESS);
        }
        return JsonBean.success(byId);
    }

    @Operation(summary = "插入数据", description = "条件:该区域号唯一")
    @PostMapping()
    public JsonBean<String> addArea(@RequestBody @Validated({Add.class, Default.class}) Area area) {
        boolean flag = iAreaServicel.saveArea(area);
        if (flag) {
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.FAILURE);
    }

    @Operation(summary = "根据ID删除数据", description = "条件:该数据不能含有下级")
    @DeleteMapping()
    public JsonBean<String> deleteAreaById(Long id) {
        boolean flag = iAreaServicel.deleteAreaById(id);
        if (flag) {
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.NOT_DELETE);
    }


    @Operation(summary = "根据ID修改用户数据", description = "条件:区域号唯一,且不能含有下级")
    @PutMapping()
    public JsonBean<String> updateAreaById(@RequestBody Area area) {
        boolean flag = iAreaServicel.updateAreaById(area);
        if (flag) {
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.CHANGEFAILED);
    }

    @Operation(summary = "分页查询")
    @GetMapping()
    public JsonBean<TableDataUtils<List<AreaVO>>> getAreaList(AreaVO areaVO) {
        TableDataUtils<List<AreaVO>> areaList = iAreaServicel.getAreaList(areaVO);
        return JsonBean.success(areaList);
    }

    @Operation(summary = "查询子单位")
    @GetMapping("/getAllAreaSubordinate")
    public JsonBean<List<Area>> getAllAreaSubordinate(String code) {
        List<Area> areaList = iAreaServicel.getAllAreaSubordinate(code);
        return JsonBean.success(areaList);
    }

}
