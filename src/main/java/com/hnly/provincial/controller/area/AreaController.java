package com.hnly.provincial.controller.area;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaUp;
import com.hnly.provincial.entity.area.AreaVO;
import com.hnly.provincial.service.area.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(tags = "区域号管理接口")
public class AreaController {

    @Resource
    private IAreaService iAreaServicel;

    @ApiOperation("根据ID查询")
    @GetMapping()
    public JsonBean<Area> getAreaById(Long id) {
        Area byId = iAreaServicel.getById(id);
        if (byId == null){
            return JsonBean.success(ResultEnum.NOTHINGNESS);
        }
        return JsonBean.success(byId);
    }

    @ApiOperation("插入数据")
    @PostMapping()
    public JsonBean<String> addArea(Area area){
        boolean flag = iAreaServicel.saveArea(area);
        if (flag) {
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.FAILURE);
    }

    @ApiOperation("根据ID删除数据")
    @DeleteMapping()
    public JsonBean<String> deleteById(Long id) {
        boolean flag = iAreaServicel.deleteById(id);
        if (flag){
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.NOT_DELETE);
    }

    @ApiOperation("根据ID修改用户数据")
    @PutMapping()
    public JsonBean<String> updateById(AreaUp areaUp){
        boolean flag = iAreaServicel.updateArea(areaUp);
        if (flag){
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.CHANGEFAILED);
    }

    @ApiOperation("分页查询")
    @PostMapping("/getAreaList")
    public JsonBean<TableDataUtils<List<AreaVO>>> getAreaList(AreaVO areaVO){
        TableDataUtils<List<AreaVO>> areaList = iAreaServicel.getAreaList(areaVO);
        return JsonBean.success(areaList);
    }

    @ApiOperation("查询子单位")
    @GetMapping("/getAllAreaSubordinate")
    public JsonBean<List<Area>> getAllAreaSubordinate(String code){
        List<Area> areaList = iAreaServicel.getAllAreaSubordinate(code);
        return JsonBean.success(areaList);
    }

}
