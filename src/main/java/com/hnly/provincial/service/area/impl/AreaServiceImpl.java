package com.hnly.provincial.service.area.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.dao.area.AreaMapper;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaVO;
import com.hnly.provincial.service.area.IAreaService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-01
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public boolean deleteById(Long id) {
        Area byId = areaMapper.selectById(id);
        String code = byId.getCode();
        QueryWrapper<Area> wrapper = new QueryWrapper<>();
        wrapper.eq("father_code",code);
        List<Area> areaList = areaMapper.selectList(wrapper);
        if (areaList.size()<=0 || areaList == null ){
            areaMapper.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public IPage<Area> getAreaList(AreaVO areaVO) {
        Area area = Conversion.changeOne(areaVO, Area.class);
        //条件构造器，并且传入area中存在的条件
        QueryWrapper<Area> wrapper = new QueryWrapper<>(area);
        //获取当前页和每页显示条数
        IPage<Area> page = new Page(areaVO.getCurrent(),areaVO.getSize());
        IPage<Area> pageList = areaMapper.selectPage(page,wrapper);
        return pageList;
    }

    @Override
    public boolean saveArea(Area area) {
        String code = area.getCode();
        QueryWrapper<Area> wrapper = new QueryWrapper<>();
        wrapper.eq("code",code);
        List<Area> areaList = areaMapper.selectList(wrapper);
        if (areaList.size()<=0){
            areaMapper.insert(area);
            return true;
        }
        return false;
    }

}
