package com.hnly.provincial.service.wateraverage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateraverage.WaterAveragePer;
import com.hnly.provincial.entity.wateraverage.WaterAveragePerVO;

import java.util.List;

/**
 * <p>
 * 亩均定额 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-22
 */
public interface IWaterAveragePerService extends IService<WaterAveragePer> {

    /**
     * 查询亩均定额分页数据
     *
     * @param waterAveragePerVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<WaterAveragePerVO>> findListByPage(WaterAveragePerVO waterAveragePerVO);

    /**
     * 添加亩均定额
     *
     * @param waterAveragePerVO
     * @return false 失败   true 成功
     */
    boolean add(WaterAveragePerVO waterAveragePerVO);

    /**
     * 删除亩均定额
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改亩均定额
     *
     * @param waterAveragePerVO
     * @return false 失败   true 成功
     */
    boolean updateData(WaterAveragePerVO waterAveragePerVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return WaterAveragePerVO
     */
    WaterAveragePerVO findById(Long id);
}
