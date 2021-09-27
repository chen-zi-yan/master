package com.hnly.provincial.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnly.provincial.entity.role.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色列表数据
     *
     * @return 角色数据
     */
    List<Role> findList();
}
