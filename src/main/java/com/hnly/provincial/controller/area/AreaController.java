package com.hnly.provincial.controller.area;


import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.service.area.IAreaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author maqh
 * @since 2021-09-01
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    @Resource
    private IAreaService iAreaServicel;

    @ApiOperation("查询所有用户")
    @GetMapping("getAllAreaList")
    public JsonBean<List<Area>> getAllAreaList() {
        List<Area> allAreaList = iAreaServicel.list();
        return JsonBean.success(allAreaList);
    }

    /*
    * queryPageBean
    * */
    @ApiOperation("模糊查询")
    @PostMapping("getArea")
    public JsonBean getArea() {

        return JsonBean.success();
    }

    @ApiOperation("根据ID查询")
    @PostMapping("getById")
    public JsonBean<Area> getAreaById(Long id) {
        Area byId = iAreaServicel.getById(id);
        if (byId.equals(null)){
            return JsonBean.success(ResultEnum.NOTHINGNESS);
        }
        return JsonBean.success(byId);
    }

    @ApiOperation("插入数据")
    @PostMapping("add")
    public JsonBean<String> addArea(Area area,String code){
        Long count = iAreaServicel.add(area, code);
        System.out.println("count = " + count);
        if (count <= 0){
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.success(ResultEnum.FAILURE);
    }

    @ApiOperation("根据ID删除数据")
    @PutMapping("deleteById")
    public JsonBean<String> deleteById(Long id) {
        Area byId = iAreaServicel.getById(id);
        if (byId == null){
            return JsonBean.success(ResultEnum.NOT_DELETE);
        }else {
            iAreaServicel.deleteById(id);
            return JsonBean.success(ResultEnum.SUCCESS);
        }
    }

    @ApiOperation("根据ID修改用户数据")
    @PostMapping("updateById")
    public JsonBean updateById(Area area){
        iAreaServicel.updateById(area);
        return JsonBean.success("修改成功!");
    }

}
