package com.hnly.provincial.service.area.impl;

import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.dao.area.AreaMapper;
import com.hnly.provincial.service.area.IAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-09-01
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Autowired
    protected AreaMapper areaMapper;

    //添加数据
    @Override
    public Long add(Area area, String code) {
        //判断区域号是否存在
        Long count = areaMapper.getByCode(code);
        if (count <= 0){
            areaMapper.add(area);
        }
        return count;
    }
    //删除数据
    @Override
    public void deleteById(Long id) {
        areaMapper.deleteById(id);
    }
    //修改数据
    @Override
    public void update(Area area) {
        areaMapper.update(area);
    }

}
