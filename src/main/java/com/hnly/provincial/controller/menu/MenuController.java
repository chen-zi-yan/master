package com.hnly.provincial.controller.menu;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.menu.MenuVO;
import com.hnly.provincial.service.menu.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单功能表 前端控制器
 * </p>
 *
 * @author maqh
 * @since 2021-09-09
 */
@Slf4j
@Tag(name = "菜单功能表")
@RestController
@RequestMapping("menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Operation(summary = "新增菜单功能表")
    @PostMapping()
    public JsonBean<String> add(@RequestBody MenuVO menuVO) {
        menuService.add(menuVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除菜单功能表")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        menuService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新菜单功能表")
    @PutMapping()
    public JsonBean<String> update(@RequestBody MenuVO menuVO) {
        menuService.updateData(menuVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询菜单功能表分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<MenuVO>>> findListByPage(MenuVO menuVO) {
        return JsonBean.success(menuService.findListByPage(menuVO));
    }

    @Operation(summary = "id查询菜单功能表")
    @GetMapping("{id}")
    public JsonBean<MenuVO> findById(@PathVariable Long id) {
        return JsonBean.success(menuService.findById(id));
    }

    @Operation(summary = "获取用户菜单")
    @GetMapping("getMenu")
    public JsonBean<List<MenuVO>> getMenu() {
        return JsonBean.success(menuService.getUserMenu());
    }

    @Operation(summary = "获取所有菜单")
    @GetMapping("getMenuAll")
    public JsonBean<List<MenuVO>> getMenuAll() {
        return JsonBean.success(menuService.getMenuAll());
    }

    @GetMapping("button")
    public JsonBean<List<MenuVO>> getButtonRole(){
        return JsonBean.success(menuService.getButtonRole());
    }
}
