package com.hnly.provincial.service.device.impl;

import com.alibaba.druid.util.StringUtils;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.entity.device.Device;
import com.hnly.provincial.entity.device.DeviceVO;
import com.hnly.provincial.dao.device.DeviceMapper;
import com.hnly.provincial.service.device.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
        checkCode(deviceVO.getCode());
        if (StringUtils.isEmpty(deviceVO.getDevRegistrationNo())){
            Device device = lambdaQuery().eq(Device::getId, deviceVO.getId()).last("1").one();
            checkDevRegistrationNo(deviceVO.getId(),deviceVO.getDevRegistrationNo(),device.getCode());
        }
        deviceVO.setCreateTime(new Date());
        Device device = Conversion.changeOne(deviceVO, Device.class);
        baseMapper.insert(device);
        return true;
    }

    /**
     * 校验设备注册号是否已经存在,不存在则通过
     *
     * @param id id
     * @param devRegistrationNo 设备注册号
     * @param code 区域号
     * @throws MyException 自定义异常
     */
    private void checkDevRegistrationNo(Long id, String devRegistrationNo, String code) throws MyException {
        int count = lambdaQuery().eq(Device::getDevRegistrationNo, devRegistrationNo)
                .eq(Device::getCode, code)
                .ne(Device::getId, id).count();
        if (count != 0){
            throw new MyException(ResultEnum.DEVREGISTRATIONNO_EXIST);
        }
    }

    /**
     * 校验code是否已经存在,不存在则通过
     *
     * @param code 行政区划
     * @throws MyException 自定义异常
     */
    private void checkCode(String code) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code).count();
        if (count != 0){
            throw new MyException(ResultEnum.CODE_EXIST);
        }
    }

    @Override
    public boolean delete(Long id){
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(DeviceVO deviceVO){
        if (StringUtils.isEmpty(deviceVO.getCode())){

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
