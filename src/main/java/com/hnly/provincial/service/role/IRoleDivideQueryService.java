package com.hnly.provincial.service.role;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.entity.role.Role;
import com.hnly.provincial.entity.role.RoleVO;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface IRoleDivideQueryService extends IService<Role> {

    /**
     * 添加角色表
     *
     * @param roleVO
     * @return false 失败   true 成功
     */
    boolean add(RoleVO roleVO);

    /**
     * 删除角色表
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改角色表
     *
     * @param roleVO
     * @return false 失败   true 成功
     */
    boolean updateData(RoleVO roleVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return RoleVO
     */
    RoleVO findById(Long id);
}
