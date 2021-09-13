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
import com.hnly.provincial.service.device.IDeviceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public TableDataUtils<List<DeviceVO>> findListByPage(DeviceVO deviceVO){
        Page<Device> page = lambdaQuery()
                .eq(!StringUtils.isEmpty(deviceVO.getCode()),Device::getCode,deviceVO.getCode())
                .page(deviceVO.page());
        List<DeviceVO> deviceVOs = Conversion.changeList(page.getRecords(), DeviceVO.class);
        return TableDataUtils.success(page.getTotal(), deviceVOs);
    }

    @Override
    public boolean add(DeviceVO deviceVO){
        checkCodeAndDev(deviceVO.getCode(),deviceVO.getDevRegistrationNo());
        deviceVO.setCreateTime(new Date());
        Device device = Conversion.changeOne(deviceVO, Device.class);
        baseMapper.insert(device);
        return true;
    }

    /**
     * 校验注册号是否已经存在于该区域规划内,不存在则通过
     *
     * @param code 区域规划
     * @param devRegistrationNo 设备注册号
     * @throws MyException 自定义异常
     */
    private void checkCodeAndDev(String code, String devRegistrationNo) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code).eq(Device::getDevRegistrationNo, devRegistrationNo).count();
        if (count != 0){
            throw new MyException(ResultEnum.DEVREGISTRATIONNO_EXIST);
        }
    }

    @Override
    public boolean delete(Long id){
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(DeviceVO deviceVO){
        if (!StringUtils.isEmpty(deviceVO.getCode()) && !StringUtils.isEmpty(deviceVO.getDevRegistrationNo())){
            lambdaQuery().eq(Device::getCode,deviceVO.getCode())
                    .eq(Device::getDevRegistrationNo,deviceVO.getDevRegistrationNo());
        }
        Device device = Conversion.changeOne(deviceVO, Device.class);
        baseMapper.updateById(device);
        return true;
    }

    @Override
    public DeviceVO findById(Long id){
        Device device = baseMapper.selectById(id);
        return Conversion.changeOne(device, DeviceVO.class);
    }
}
