package com.hnly.provincial.controller.role;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.role.RoleVO;
import com.hnly.provincial.service.role.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Slf4j
@Tag(name = "角色表")
@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @Operation(summary = "新增角色表")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated RoleVO roleVO) {
        roleVO.setCreateTime(new Date());
        roleService.add(roleVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除角色表")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新角色表")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated RoleVO roleVO) {
        roleVO.setUpdateTime(new Date());
        roleService.updateData(roleVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询角色表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<RoleVO>>> findListByPage(RoleVO roleVO) {
        return JsonBean.success(roleService.findListByPage(roleVO));
    }

    @Operation(summary = "id查询角色表")
    @GetMapping("{id}")
    public JsonBean<RoleVO> findById(@PathVariable Long id) {
        return JsonBean.success(roleService.findById(id));
    }
}
