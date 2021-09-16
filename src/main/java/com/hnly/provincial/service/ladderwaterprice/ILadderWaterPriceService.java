package com.hnly.provincial.service.ladderwaterprice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.ladderwaterprice.LadderWaterPrice;
import com.hnly.provincial.entity.ladderwaterprice.LadderWaterPriceVO;

import java.util.List;

/**
 * <p>
 * 阶梯水价表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface ILadderWaterPriceService extends IService<LadderWaterPrice> {

    /**
     * 查询阶梯水价表分页数据
     *
     * @param ladderWaterPriceVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<LadderWaterPriceVO>> findListByPage(LadderWaterPriceVO ladderWaterPriceVO);

    /**
     * 添加阶梯水价表
     *
     * @param ladderWaterPriceVO
     * @return false 失败   true 成功
     */
    boolean add(LadderWaterPriceVO ladderWaterPriceVO);

    /**
     * 删除阶梯水价表
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改阶梯水价表
     *
     * @param ladderWaterPriceVO
     * @return false 失败   true 成功
     */
    boolean updateData(LadderWaterPriceVO ladderWaterPriceVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return LadderWaterPriceVO
     */
    LadderWaterPriceVO findById(Long id);
}
