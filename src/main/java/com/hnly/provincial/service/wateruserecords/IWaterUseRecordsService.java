package com.hnly.provincial.service.wateruserecords;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecords;
import com.hnly.provincial.entity.wateruserecords.WaterUseRecordsVO;

import java.util.List;

/**
 * <p>
 * 用水记录表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-23
 */
public interface IWaterUseRecordsService extends IService<WaterUseRecords> {

    /**
     * 查询用水记录表分页数据
     *
     * @param waterUseRecordsVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<WaterUseRecordsVO>> findListByPage(WaterUseRecordsVO waterUseRecordsVO);

    /**
     * 查询数据中的农户id=农户farmer表中的id
     *
     * @param id 用水数据中的farmer_id
     * @return 存在则返回农户的名字, 失败返回一个空值
     */
    String getFarmerName(Long id);

    /**
     * 查询数据中的农户id=设备device表中的id
     *
     * @param id 用水数据中的device_id
     * @return 存在则返回设备的类型, 失败返回一个空值
     */
    String getDeviceName(Long id);
}
