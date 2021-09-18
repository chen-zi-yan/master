package com.hnly.provincial.controller.role.menu;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.entity.role.menu.RoleMenuVO;
import com.hnly.provincial.service.role.menu.IRoleMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色菜单表 前端控制器
 * </p>
 *
 * @author maqh
 * @since 2021-09-17
 */
@Slf4j
@Tag(name = "角色菜单表")
@RestController
@RequestMapping("role-menu")
public class RoleMenuController {

    @Resource
    private IRoleMenuService roleMenuService;

    @Operation(summary = "新增角色菜单表", description = "添加角色菜单和修改角色菜单统一使用此接口")
    @PostMapping()
    public JsonBean<String> add(@RequestBody RoleMenuVO roleMenuVO) {
        roleMenuService.add(roleMenuVO);
        return JsonBean.success();
    }

    @Operation(summary = "角色获取菜单id")
    @GetMapping("{id}")
    public JsonBean<List<Long>> findById(@PathVariable Long id) {
        return JsonBean.success(roleMenuService.findByRoleId(id));
    }

}
