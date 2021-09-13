package com.hnly.provincial.service.project.impl;

import com.alibaba.druid.util.StringUtils;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.project.Project;
import com.hnly.provincial.entity.project.ProjectVO;
import com.hnly.provincial.dao.project.ProjectMapper;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.project.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 项目管理 服务实现类
 * </p>
 *
 * @author ymd
 * @since 2021-09-08
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Resource
    private IAreaService iAreaService;

    @Override
    public TableDataUtils<List<ProjectVO>> findListByPage(ProjectVO projectVO) {
        Page<Project> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(projectVO.getCode()), Project::getCode, projectVO.getCode())
                .eq(!StringUtils.isEmpty(projectVO.getName()), Project::getName, projectVO.getName())
                .eq(!StringUtils.isEmpty(projectVO.getUnit()), Project::getUnit, projectVO.getUnit())
                .eq(!StringUtils.isEmpty(projectVO.getType()), Project::getType, projectVO.getType())
                .eq(!StringUtils.isEmpty(projectVO.getManufacturers()), Project::getManufacturers, projectVO.getManufacturers())
                .page(projectVO.page());
        List<ProjectVO> projectVOS = Conversion.changeList(page.getRecords(), ProjectVO.class);
        for (ProjectVO vo : projectVOS) {
            Area xian = iAreaService.getByCode(vo.getCode());
            Area shi = iAreaService.getByCode(xian.getFatherCode());
            vo.setCodeName(xian.getName());
            vo.setCityName(shi.getName());
            vo.setTypeName(vo.getTypeName());
            vo.setUnitName(vo.getUnitName());
        }
        return TableDataUtils.success(page.getTotal(), projectVOS);
    }

    @Override
    public boolean add(ProjectVO projectVO) {
        Project project = Conversion.changeOne(projectVO, Project.class);
        project.setCreateTime(new Date());
        baseMapper.insert(project);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(ProjectVO projectVO) {
        Project project = Conversion.changeOne(projectVO, Project.class);
        project.setUpdateTime(new Date());
        baseMapper.updateById(project);
        return true;
    }

    @Override
    public ProjectVO findById(Long id) {
        Project project = baseMapper.selectById(id);
        ProjectVO projectVO = Conversion.changeOne(project, ProjectVO.class);
        Area xian = iAreaService.getByCode(projectVO.getCode());
        Area shi = iAreaService.getByCode(xian.getFatherCode());
        projectVO.setCodeName(xian.getName());
        projectVO.setCityName(shi.getName());
        projectVO.setTypeName(projectVO.getTypeName());
        projectVO.setUnitName(projectVO.getUnitName());
        return projectVO;
    }

    @Override
    public boolean addCheckProject(ProjectVO projectVO) {
        Integer count = lambdaQuery()
                .eq(Project::getName, projectVO.getName())
                .eq(Project::getType, projectVO.getType())
                .count();
        if (0 == count) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCheck(ProjectVO projectVO) {
        Integer count = lambdaQuery()
                .eq(Project::getName, projectVO.getName())
                .eq(Project::getType, projectVO.getType())
                .ne(Project::getId, projectVO.getId())
                .count();
        if (0 == count) {
            return false;
        }
        return true;
    }



}
