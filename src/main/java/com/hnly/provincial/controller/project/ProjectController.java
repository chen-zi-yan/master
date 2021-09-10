package com.hnly.provincial.controller.project;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.ResultEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.hnly.provincial.service.project.IProjectService;
import com.hnly.provincial.entity.project.Project;
import com.hnly.provincial.entity.project.ProjectVO;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 项目管理 前端控制器
 * </p>
 *
 * @author ymd
 * @since 2021-09-08
 */
@Slf4j
@Tag(name = "项目管理")
@RestController
@RequestMapping("project")
public class ProjectController {

    @Resource
    private IProjectService projectService;

    @Operation(summary = "新增项目管理")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated ProjectVO projectVO) {
        boolean project = projectService.addCheckProject(projectVO);
        if (project) {
            return JsonBean.err(ResultEnum.CODE_EXIST);
        }
        boolean add = projectService.add(projectVO);
        if (add) {
            return JsonBean.success(ResultEnum.SUCCESS);
        }
        return JsonBean.err(ResultEnum.FAILURE);
    }

    @Operation(summary = "删除项目管理")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id) {
        Project byId = projectService.getById(id);
        if (null == byId) {
            return JsonBean.err(ResultEnum.NOT_DELETE);
        }
        projectService.delete(id);
        return JsonBean.success(ResultEnum.SUCCESS);
    }

    @Operation(summary = "更新项目管理", description = "id不能为空")
    @PutMapping()
    public JsonBean<String> update(@RequestBody @Validated ProjectVO projectVO) {
        boolean tag = projectService.updateCheck(projectVO);
        if (tag) {
            return JsonBean.err(ResultEnum.CODE_EXIST);
        }
        projectService.updateData(projectVO);
        return JsonBean.success(ResultEnum.SUCCESS);
    }

    @Operation(summary = "查询项目管理分页数据")
    @GetMapping("")
    public JsonBean<TableDataUtils<List<ProjectVO>>> findListByPage(ProjectVO projectVO) {
        return JsonBean.success(projectService.findListByPage(projectVO));
    }

    @Operation(summary = "id查询项目管理")
    @GetMapping("{id}")
    public JsonBean<ProjectVO> findById(@PathVariable Long id) {
        return JsonBean.success(projectService.findById(id));
    }
}
