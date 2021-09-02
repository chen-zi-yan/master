package com.hnly.provincial.service.area;

import com.hnly.provincial.entity.area.Area;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author maqh
 * @since 2021-09-01
 */
public interface IAreaService extends IService<Area> {

    //插入area数据
    Long add(Area area, String code);
    //删除数据
    void deleteById(Long id);
    //修改数据
    void update(Area area);
    //分页查询

}
