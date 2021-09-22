package com.hnly.provincial.controller.log;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.ResultEnum;
import org.springframework.web.bind.annotation.*;
import com.hnly.provincial.service.log.IUserOperateLogService;
import com.hnly.provincial.entity.log.UserOperateLog;
import com.hnly.provincial.entity.log.UserOperateLogVO;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
* <p>
* 用户操作记录 前端控制器
* </p>
*
* @author czy
* @since 2021-09-22
*/
@Slf4j
@Tag(name = "用户操作记录")
@RestController
@RequestMapping("user-operate-log")
public class UserOperateLogController {

    @Resource
    private IUserOperateLogService userOperateLogService;

    @Operation(summary = "新增用户操作记录")
    @PostMapping()
    public JsonBean<String> add(@RequestBody UserOperateLogVO userOperateLogVO){
        userOperateLogService.add(userOperateLogVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除用户操作记录")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id){
        userOperateLogService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新用户操作记录")
    @PutMapping()
    public JsonBean<String> update(@RequestBody UserOperateLogVO userOperateLogVO){
        userOperateLogService.updateData(userOperateLogVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询用户操作记录分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<UserOperateLogVO>>> findListByPage(UserOperateLogVO userOperateLogVO){
        return JsonBean.success(userOperateLogService.findListByPage(userOperateLogVO));
    }

    @Operation(summary = "id查询用户操作记录")
    @GetMapping("{id}")
    public JsonBean<UserOperateLogVO> findById(@PathVariable Long id){
        return JsonBean.success(userOperateLogService.findById(id));
    }
}
