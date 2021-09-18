package com.hnly.provincial.service.device;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.device.Device;
import com.hnly.provincial.entity.device.DeviceVO;

import java.util.List;

/**
 * <p>
 * 设备信息表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-13
 */
public interface IDeviceService extends IService<Device> {

    /**
     * 查询设备信息表分页数据
     *
     * @param deviceVO 条件
     * @return 分页结果
     */
    TableDataUtils<List<DeviceVO>> findListByPage(DeviceVO deviceVO);

    /**
     * 添加设备信息表
     *
     * @param deviceVO
     * @return false 失败   true 成功
     */
    boolean add(DeviceVO deviceVO);

    /**
     * 删除设备信息表
     *
     * @param id 主键
     * @return false 失败   true 成功
     */
    boolean delete(Long id);

    /**
     * 修改设备信息表
     *
     * @param deviceVO
     * @return false 失败   true 成功
     */
    boolean updateData(DeviceVO deviceVO);

    /**
     * id查询数据
     *
     * @param id id
     * @return DeviceVO
     */
    DeviceVO findById(Long id);
}
