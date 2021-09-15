package com.hnly.provincial.service.device.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.device.DeviceMapper;
import com.hnly.provincial.entity.device.Device;
import com.hnly.provincial.entity.device.DeviceVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.device.IDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备信息表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-13
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Resource
    private IAreaService iAreaService;

    @Override
    public TableDataUtils<List<DeviceVO>> findListByPage(DeviceVO deviceVO) {
        Page<Device> page = lambdaQuery()
                .eq(!StringUtils.isEmpty(deviceVO.getType()), Device::getType, deviceVO.getType())
                .likeRight(!StringUtils.isEmpty(deviceVO.getName()), Device::getName, deviceVO.getName())
                .likeRight(!StringUtils.isEmpty(deviceVO.getCode()), Device::getCode, deviceVO.getCode())
                .likeRight(!StringUtils.isEmpty(deviceVO.getDevSn()), Device::getDevSn, deviceVO.getDevSn())
                .page(deviceVO.page());
        List<DeviceVO> deviceVOs = Conversion.changeList(page.getRecords(), DeviceVO.class);
        for (DeviceVO vo : deviceVOs) {
            Map<String, String> allAreaName = iAreaService.getAllAreaName(vo.getCode());
            vo.setVillageName(allAreaName.get("cun"));
            vo.setTownshipName(allAreaName.get("xiang"));
            vo.setCountyName(allAreaName.get("xian"));
            vo.setCityName(allAreaName.get("shi"));
        }
        return TableDataUtils.success(page.getTotal(), deviceVOs);
    }

    @Override
    public boolean add(DeviceVO deviceVO) {
        checkCodeAndDevSn(deviceVO.getCode(), deviceVO.getDevSn());
        deviceVO.setCreateTime(new Date());
        Device device = Conversion.changeOne(deviceVO, Device.class);
        baseMapper.insert(device);
        return true;
    }

    /**
     * 校验设备序列号否已经存在于该区域规划内,不存在则通过
     *
     * @param code  区域规划
     * @param devSn 设备序列号
     * @throws MyException 自定义异常
     */
    private void checkCodeAndDevSn(String code, String devSn) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code).eq(Device::getDevSn, devSn).count();
        if (count != 0) {
            throw new MyException(ResultEnum.DEVSN_EXIST);
        }
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(DeviceVO deviceVO) {
        checkCodeAndDevSnDivideId(deviceVO.getId(), deviceVO.getCode(), deviceVO.getDevSn());
        deviceVO.setUpdateTime(new Date());
        Device device = Conversion.changeOne(deviceVO, Device.class);
        baseMapper.updateById(device);
        return true;
    }

    /**
     * 在code(区域规划)和devSn(设备序列号)有值时,校验该设备序列号是否存在该行政区划中
     *
     * @param id    id
     * @param code  区域规划
     * @param devSn 设备序列号
     * @throws MyException 自定义异常
     */
    private void checkCodeAndDevSnDivideId(Long id, String code, String devSn) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code)
                .eq(Device::getDevSn, devSn)
                .ne(Device::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.DEVSN_EXIST);
        }
    }

    @Override
    public DeviceVO findById(Long id) {
        Device device = baseMapper.selectById(id);
        return Conversion.changeOne(device, DeviceVO.class);
    }
}
