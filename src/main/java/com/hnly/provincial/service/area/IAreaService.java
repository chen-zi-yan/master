package com.hnly.provincial.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.area.Area;
import com.hnly.provincial.entity.area.AreaVO;
import com.hnly.provincial.entity.area.CascadeVO;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-01
 */
public interface IAreaService extends IService<Area> {

    /**
     * 保存地区
     *
     * @param area 地区对象信息
     * @return true  保存成功    <br/> false   保存失败
     */
    boolean saveArea(Area area);

    /**
     * 删除地区
     *
     * @param id 地区id
     * @return true  删除成功    <br/> false  删除失败
     */
    boolean deleteAreaById(Long id);

    /**
     * 修改数据
     *
     * @param area 地区对象信息
     * @return true  修改成功    <br/> false   修改失败
     */
    boolean updateAreaById(Area area);

    /**
     * 分页查询
     *
     * @param areaVO 分页查询地区对象信息
     * @return TableDataUtils.success(page.getTotal (), list); <br/>第一个参数为数据总量 <br/>第二个参数为查询出来的数据
     */
    TableDataUtils<List<AreaVO>> getAreaList(AreaVO areaVO);

    /**
     * 根据传入的code查询子集
     *
     * @param code 区域号
     * @return List<Area> 数据集合
     */
    List<Area> getAllAreaSubordinate(String code);

    /**
     * 行政区划获取上级所有的名字
     *
     * @return 上级所有名字
     */
    Map<String, String> getAllAreaName(String code);

    /**
     * 获取级联数据
     *
     * @param code 行政区划
     * @return 级联数据
     */
    List<CascadeVO> getList(String code);
}
