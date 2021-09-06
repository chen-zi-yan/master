package com.hnly.provincial.controller.area;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaUp;
import com.hnly.provincial.entity.area.AreaVO;
import com.hnly.provincial.service.area.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @GetMapping("getAreaById")
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
        boolean flag = iAreaServicel.save(area);
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
    public JsonBean updateById(AreaUp areaUp){
        boolean flag = iAreaServicel.updateById(areaUp);
        if (flag){
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.CHANGEFAILED);
    }

    /*
     * 分页查询
     * */
    @ApiOperation("分页查询")
    @GetMapping()
    public JsonBean<IPage<Area>> getAreaList(AreaVO areaVO){
        IPage<Area> pageList = iAreaServicel.getAreaList(areaVO);
        return JsonBean.success(pageList);
    }

}
