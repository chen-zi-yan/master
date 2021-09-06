package com.hnly.provincial.service.area;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaVO;


/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author maqh
 * @since 2021-09-01
 */
public interface IAreaService extends IService<Area> {

    boolean deleteById(Long id);

    IPage<Area> getAreaList(AreaVO areaVO);

}
