package com.hnly.provincial.service.area.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.area.AreaMapper;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaUp;
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


    @Override
    public boolean saveArea(Area area) {
        //根据code查询数据库是否存在相同的code
        Integer countCode = lambdaQuery().eq(Area::getCode, area.getCode()).count();
        //查询fatherCode是否已存在code中(查询是否存在上级)
        Integer count = lambdaQuery().eq(Area::getCode, area.getFatherCode()).count();
        if (countCode == 0 && count == 0 && area.getFatherCode() == null){
            area.setFatherCode("0");
            area.setCreateTime(new Date());
            baseMapper.insert(area);
            return true;
        }else if(countCode == 0 && count > 0){
            Area one = lambdaQuery().eq(Area::getCode, area.getFatherCode()).one();
            if (one.getStatus()==null){
                area.setStatus("0");
            }else {
                Integer status = Integer.valueOf(one.getStatus());
                area.setStatus(String.valueOf(status+1));
            }
            area.setCreateTime(new Date());
            baseMapper.insert(area);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        Area area = baseMapper.selectById(id);
        Integer count = lambdaQuery().eq(Area::getFatherCode, area.getCode()).count();
        if (count <= 0) {
            baseMapper.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateArea(AreaUp areaUp) {
        Area area = baseMapper.selectById(areaUp.getId());
        //根据code查询数据库是否存在相同的code
        Integer countCode = lambdaQuery().eq(Area::getCode, areaUp.getCode()).count();
        //是否有下级
        Integer count = lambdaQuery().eq(Area::getFatherCode, area.getCode()).count();
        if (area.getFatherCode() == "0" || areaUp.getFatherCode() == null && countCode == 0 && count == 0){
            areaUp.setStatus(area.getStatus());
            areaUp.setUpdateTime(new Date());
            baseMapper.updateById(areaUp);
            return true;
        }
        return false;
    }

    @Override
    public TableDataUtils<List<AreaVO>> getAreaList(AreaVO areaVO) {
        Page<Area> page = lambdaQuery()
                .eq(areaVO.getStatus() != null, Area::getStatus, areaVO.getStatus())
                .eq(areaVO.getCode() != null, Area::getCode, areaVO.getCode())
                .page(areaVO.page());
        List<AreaVO> list = Conversion.changeList(page.getRecords(), AreaVO.class);
        return TableDataUtils.success(page.getTotal(), list);
    }

    @Override
    public List<Area> getAllAreaSubordinate(String code) {
            List<Area> list = lambdaQuery().eq(Area::getFatherCode, code).list();
            return list;
    }

}
