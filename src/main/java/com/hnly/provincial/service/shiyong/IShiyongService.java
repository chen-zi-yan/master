package com.hnly.provincial.service.shiyong;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.shiyong.Shiyong;
import com.hnly.provincial.entity.shiyong.ShiyongVO;

import java.util.List;

/**
 * <p>
 * 使用权证 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-14
 */
public interface IShiyongService extends IService<Shiyong> {

    /**
     * 查询使用权证分页数据
     *
     * @param shiyongVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<ShiyongVO>> findListByPage(ShiyongVO shiyongVO);

    /**
     * 添加使用权证
     *
     * @param shiyongVO
     * @return false 失败   true 成功
     */
    boolean add(ShiyongVO shiyongVO);

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
     * @param shiyongVO
     * @return false 失败   true 成功
     */
    boolean updateData(ShiyongVO shiyongVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return ShiyongVO
     */
    ShiyongVO findById(Long id);
}
