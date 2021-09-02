package com.hnly.provincial.dao.area;

import com.hnly.provincial.entity.area.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 地区表 Mapper 接口
 * </p>
 *
 * @author maqh
 * @since 2021-09-01
 */
public interface AreaMapper extends BaseMapper<Area> {

    //插入area数据
    void add(Area area);
    //删除数据
    void deleteById(Long id);
    //修改数据
    void update(Area area);
    //查询code是否已经存在
    long getByCode(String code);
    //模糊查询

}
