package com.hnly.provincial.service.ic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.ic.Ic;
import com.hnly.provincial.entity.ic.IcVO;

import java.util.List;

/**
 * <p>
 * 卡号表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface IIcService extends IService<Ic> {

    /**
     * 查询卡号表分页数据
     *
     * @param icVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<IcVO>> findListByPage(IcVO icVO);

    /**
     * 添加卡号表
     *
     * @param icVO
     * @return false 失败   true 成功
     */
    boolean add(IcVO icVO);

    /**
     * 删除卡号表
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改卡号表
     *
     * @param icVO
     * @return false 失败   true 成功
     */
    boolean updateData(IcVO icVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return IcVO
     */
    IcVO findById(Long id);
}
