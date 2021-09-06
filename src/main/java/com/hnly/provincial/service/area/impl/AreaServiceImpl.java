package com.hnly.provincial.service.area.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.dao.area.AreaMapper;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaVO;
import com.hnly.provincial.service.area.IAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        Integer count = lambdaQuery().eq(Area::getFatherCode, byId.getCode()).count();
        if (count == 0 ){
            areaMapper.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public IPage<Area> getAreaList(AreaVO areaVO) {
        Page<Area> page1 = lambdaQuery().page(areaVO.page());
        return page1;
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
