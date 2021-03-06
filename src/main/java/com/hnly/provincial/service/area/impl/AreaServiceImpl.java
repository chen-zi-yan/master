package com.hnly.provincial.service.area.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.area.AreaMapper;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaName;
import com.hnly.provincial.entity.area.AreaVO;
import com.hnly.provincial.entity.area.CascadeVO;
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
        //验证code是否已经存在
        checkCode(area.getCode());
        //验证是否存在上级
        checkSubordinate(area.getFatherCode());
        Area one = lambdaQuery().eq(Area::getCode, area.getFatherCode()).last("limit 1").one();
        if (one.getStatus() == null) {
            area.setStatus("0");
        } else {
            int status = Integer.parseInt(one.getStatus());
            area.setStatus(String.valueOf(status + 1));
        }
        area.setCreateTime(new Date());
        baseMapper.insert(area);
        return true;
    }

    @Override
    public boolean deleteAreaById(Long id) {
        Area area = baseMapper.selectById(id);
        int count = lambdaQuery().eq(Area::getFatherCode, area.getCode()).count();
        if (count <= 0) {
            baseMapper.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAreaById(Area area) {
        Area area1 = baseMapper.selectById(area.getId());
        //输入的上级单位必须存在
        if (area.getFatherCode() != null) {
            checkSubordinate(area.getFatherCode());
        }
        //验证输入的区域码是否和该正在修改的code信息相同
        if (area.getCode() == null || area.getCode().equals(area1.getCode())) {
            area.setUpdateTime(new Date());
            baseMapper.updateById(area);
            return true;
        }
        //验证code是否已经存在
        checkCode(area.getCode());
        //验证是否存在下级
        checkSuperior(area1.getCode());
        area.setUpdateTime(new Date());
        baseMapper.updateById(area);
        return true;
    }

    @Override
    public TableDataUtils<List<AreaVO>> getAreaList(AreaVO areaVO) {
        Page<Area> page = lambdaQuery()
                .eq(areaVO.getStatus() != null, Area::getStatus, areaVO.getStatus())
                .eq(StringUtils.isNotBlank(areaVO.getCode()), Area::getFatherCode, areaVO.getCode())
                .page(areaVO.page());
        List<AreaVO> list = Conversion.changeList(page.getRecords(), AreaVO.class);
        return TableDataUtils.success(page.getTotal(), list);
    }

    /**
     * 根据区域号:查询子单位列表
     *
     * @param code 区域号
     * @return 子集单位列表
     */
    @Override
    public List<Area> getAllAreaSubordinate(String code) {
        if (code == null) {
            return lambdaQuery().eq(Area::getStatus, 0).list();
        }
        return lambdaQuery().eq(Area::getFatherCode, code).list();
    }

    /**
     * 校验该区域码是否存在 不存在通过
     *
     * @param code 区域码
     */
    public void checkCode(String code) {
        int count = lambdaQuery().eq(Area::getCode, code).count();
        if (count != 0) {
            throw new MyException(ResultEnum.CODE_EXIST);
        }
    }

    /**
     * 检验该区域码是否存在下级单位,若存在则通过
     *
     * @param code 区域码
     */
    public void checkSuperior(String code) {
        int count = lambdaQuery().eq(Area::getFatherCode, code).count();
        if (count != 0) {
            throw new MyException(ResultEnum.CODE_SUBORDINATE_EXIST);
        }
    }

    /**
     * 检验该区域码是否存在上级单位,若存在则通过
     *
     * @param fatherCode 上级行政区划
     */
    public void checkSubordinate(String fatherCode) {
        int count = lambdaQuery().eq(Area::getCode, fatherCode).count();
        if (count == 0) {
            throw new MyException(ResultEnum.CODE_SUPERIOR_EXIST);
        }
    }

    @Override
    public AreaName getAllAreaName(String code) {
        AreaName areaName = new AreaName();
        recursionAreaName(code, areaName);
        return areaName;
    }

    @Override
    public List<CascadeVO> getList(String code) {
        List<Area> list = lambdaQuery().eq(Area::getFatherCode, code).list();
        List<AreaVO> areaVOList = Conversion.changeList(list, AreaVO.class);
        List<CascadeVO> cascadeVOList = Conversion.changeList(areaVOList, CascadeVO.class);
        getChildren(cascadeVOList);
        return cascadeVOList;
    }

    @Override
    public String getNameByCode(String code) {
        Area one = lambdaQuery().eq(Area::getCode, code).last("limit 1").one();
        return one.getName();
    }

    public void getChildren(List<CascadeVO> list) {
        for (CascadeVO areaVO : list) {
            List<Area> list1 = lambdaQuery().eq(Area::getFatherCode, areaVO.getValue()).list();
            List<AreaVO> areaVOS = Conversion.changeList(list1, AreaVO.class);
            List<CascadeVO> cascadeVOS = Conversion.changeList(areaVOS, CascadeVO.class);
            areaVO.setChildren(cascadeVOS);
            getChildren(cascadeVOS);
        }
    }

    /**
     * 递归查询name
     */
    public void recursionAreaName(String code, AreaName areaName) {
        Area one = lambdaQuery().eq(Area::getCode, code).last("limit 1").one();
        if (one != null && one.getStatus() != null) {
            setName(one, areaName);
            recursionAreaName(one.getFatherCode(), areaName);
        }
    }

    public void setName(Area area, AreaName areaName) {
        switch (area.getStatus()) {
            case "0":
                areaName.setShiName(area.getName());
                break;
            case "1":
                areaName.setXianName(area.getName());
                break;
            case "2":
                areaName.setXiangName(area.getName());
                break;
            default:
                areaName.setCunName(area.getName());
                break;
        }
    }

}
