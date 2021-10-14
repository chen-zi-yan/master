package com.hnly.provincial.service.waterquota;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.waterquota.WaterQuota;
import com.hnly.provincial.entity.waterquota.WaterQuotaVO;

import java.util.List;

/**
 * <p>
 * 用水定额 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-28
 */
public interface IWaterQuotaService extends IService<WaterQuota> {

    /**
     * 查询用水定额分页数据
     *
     * @param waterQuotaVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<WaterQuotaVO>> findListByPage(WaterQuotaVO waterQuotaVO);

    /**
     * 添加用水定额
     *
     * @param waterQuotaVO
     * @return false 失败   true 成功
     */
    boolean add(WaterQuotaVO waterQuotaVO);

    /**
     * 删除用水定额
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改用水定额
     *
     * @param waterQuotaVO
     * @return false 失败   true 成功
     */
    boolean updateData(WaterQuotaVO waterQuotaVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return WaterQuotaVO
     */
    WaterQuotaVO findById(Long id);
}
