package com.hnly.provincial.service.project;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.project.Project;
import com.hnly.provincial.entity.project.ProjectVO;

import java.util.List;

/**
 * <p>
 * 项目管理 服务类
 * </p>
 *
 * @author ymd
 * @since 2021-09-08
 */
public interface IProjectService extends IService<Project> {

    /**
     * 查询项目管理分页数据
     *
     * @param projectVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<ProjectVO>> findListByPage(ProjectVO projectVO);

    /**
     * 添加项目管理
     *
     * @param projectVO   项目管理对象
     * @return false 失败   true 成功
     */
    boolean add(ProjectVO projectVO);

    /**
     * 删除项目管理
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改项目管理
     *
     * @param projectVO  项目管理对象
     * @return false 失败   true 成功
     */
    boolean updateData(ProjectVO projectVO);

    /**
     * id查询数据
     *
     * @param id id   项目管理表的自增主键id
     * @return ProjectVO  查询出来的对象数据
     */
    ProjectVO findById(Long id);


    /**
     *  添加时校验项目是否已经存在
     * @param projectVO  项目管理对象
     * @return  存在返回true </br>  不存在返回false
     */
    boolean  addCheckProject(ProjectVO projectVO);

    /**
     *  更新时校验项目是否已经存在
     * @param projectVO  项目管理对象
     * @return  存在返回true </br>  不存在返回false
     */
    boolean  updateCheck(ProjectVO projectVO);
}
