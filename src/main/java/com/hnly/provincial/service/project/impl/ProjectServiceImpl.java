package com.hnly.provincial.service.project.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.project.ProjectMapper;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.project.Project;
import com.hnly.provincial.entity.project.ProjectVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.project.IProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
                .like(!StringUtils.isEmpty(projectVO.getName()), Project::getName, projectVO.getName())
                .eq(!StringUtils.isEmpty(projectVO.getUnit()), Project::getUnit, projectVO.getUnit())
                .eq(!StringUtils.isEmpty(projectVO.getType()), Project::getType, projectVO.getType())
                .like(!StringUtils.isEmpty(projectVO.getManufacturers()), Project::getManufacturers, projectVO.getManufacturers())
                .orderByDesc(Project::getId)
                .page(projectVO.page());
        List<ProjectVO> projectVOList = Conversion.changeList(page.getRecords(), ProjectVO.class);
        for (ProjectVO vo : projectVOList) {
            replenishAreaName(vo);
        }
        return TableDataUtils.success(page.getTotal(), projectVOList);
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
        replenishAreaName(projectVO);
        return projectVO;
    }

    @Override
    public boolean addCheckProject(ProjectVO projectVO) {
        Integer count = lambdaQuery()
                .eq(Project::getName, projectVO.getName())
                .eq(Project::getType, projectVO.getType())
                .count();
        return 0 != count;
    }

    @Override
    public boolean updateCheck(ProjectVO projectVO) {
        Integer count = lambdaQuery()
                .eq(Project::getName, projectVO.getName())
                .eq(Project::getType, projectVO.getType())
                .ne(Project::getId, projectVO.getId())
                .count();
        return 0 != count;
    }

    /**
     * 获取县级和市级行政单位的名称
     *
     * @param projectVO 项目管理对象
     */
    private void replenishAreaName(ProjectVO projectVO) {
        Area xian = iAreaService.lambdaQuery().eq(Area::getCode, projectVO.getCode()).last("limit 1").one();
        if (null == xian) {
            projectVO.setCodeName("");
            projectVO.setCityName("");
            return;
        }
        projectVO.setCodeName(xian.getName());
        Area shi = iAreaService.lambdaQuery().eq(Area::getCode, xian.getFatherCode()).last("limit 1").one();
        if (null != shi) {
            projectVO.setCityName(shi.getName());
            return;
        }
        projectVO.setCityName("");
    }

}
