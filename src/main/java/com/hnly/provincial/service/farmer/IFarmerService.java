package com.hnly.provincial.service.farmer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.farmer.Farmer;
import com.hnly.provincial.entity.farmer.FarmerVO;

import java.util.List;

/**
 * <p>
 * 农户表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-08
 */
public interface IFarmerService extends IService<Farmer> {

    /**
     * 查询农户表分页数据
     *
     * @param farmerVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<FarmerVO>> findListByPage(FarmerVO farmerVO);

    /**
     * 添加农户表
     *
     * @param farmerVO
     * @return false 失败   true 成功
     */
    boolean add(FarmerVO farmerVO);

    /**
     * 删除农户表
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改农户表
     *
     * @param farmerVO
     * @return false 失败   true 成功
     */
    boolean updateData(FarmerVO farmerVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return FarmerVO
     */
    FarmerVO findById(Long id);
}
