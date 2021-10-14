package com.hnly.provincial.service.shiyong;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.shiyong.ShiYong;
import com.hnly.provincial.entity.shiyong.ShiYongVO;

import java.util.List;

/**
 * <p>
 * 使用权证 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-14
 */
public interface IShiYongService extends IService<ShiYong> {

    /**
     * 查询使用权证分页数据
     *
     * @param shiYongVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<ShiYongVO>> findListByPage(ShiYongVO shiYongVO);

    /**
     * 添加使用权证
     *
     * @param shiYongVO
     * @return false 失败   true 成功
     */
    boolean add(ShiYongVO shiYongVO);

    /**
     * 删除使用权证
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改使用权证
     *
     * @param shiYongVO
     * @return false 失败   true 成功
     */
    boolean updateData(ShiYongVO shiYongVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return shiYongVO
     */
    ShiYongVO findById(Long id);
}
