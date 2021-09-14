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
    public TableDataUtils<List<DeviceVO>> findListByPage(DeviceVO deviceVO){
        Page<Device> page = lambdaQuery()
                .eq(!StringUtils.isEmpty(deviceVO.getType()),Device::getType,deviceVO.getType())
                .likeRight(!StringUtils.isEmpty(deviceVO.getCode()),Device::getCode,deviceVO.getCode())
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
    public boolean add(DeviceVO deviceVO){
        if (deviceVO.getType().equals("1") || deviceVO.getType().equals("2")){
            if (!StringUtils.isEmpty(deviceVO.getDevSn())){
                checkCodeAndDevSn(deviceVO.getCode(),deviceVO.getDevSn());
            }else {
                throw new MyException(ResultEnum.DEVSN_NOTEMPTY);
            }
        }else if (deviceVO.getType().equals("3")){
            if (!StringUtils.isEmpty(deviceVO.getDevRegistrationNo())){
                checkCodeAndDevRegistrationNo(deviceVO.getCode(),deviceVO.getDevRegistrationNo());
            }else {
                throw new MyException(ResultEnum.DEVREGISTRATIONNO_NOTEMPTY);
            }
        }
        if (!StringUtils.isEmpty(deviceVO.getLongitude()) || !StringUtils.isEmpty(deviceVO.getLatitude())){
            throw new MyException(ResultEnum.LATITUDEANDLONGITUDE_NOTONLY);
        } else{
            checkLatitudeAndLongitude(deviceVO.getLongitude(),deviceVO.getLatitude());
        }
        deviceVO.setCreateTime(new Date());
        Device device = Conversion.changeOne(deviceVO, Device.class);
        baseMapper.insert(device);
        return true;
    }

    /**
     * 校验该经纬度是否已经存在,不存在通过
     *
     * @param longitude 经度
     * @param latitude 纬度
     * @throws MyException 自定义异常
     */
    private void checkLatitudeAndLongitude(String longitude,String latitude) throws MyException {
        int count = lambdaQuery().eq(Device::getLongitude, longitude).eq(Device::getLatitude, latitude).count();
        if (count != 0){
            throw new MyException(ResultEnum.LATITUDEANDLONGITUDE_EXIST);
        }
    }

    /**
     * 校验设备注册号是否已经存在于该区域规划内,不存在则通过
     *
     * @param code 区域规划
     * @param devRegistrationNo 设备注册号
     * @throws MyException 自定义异常
     */
    private void checkCodeAndDevRegistrationNo(String code, String devRegistrationNo) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code).eq(Device::getDevRegistrationNo, devRegistrationNo).count();
        if (count != 0){
            throw new MyException(ResultEnum.DEVREGISTRATIONNO_EXIST);
        }
    }

    /**
     * 校验设备序列号否已经存在于该区域规划内,不存在则通过
     *
     * @param code 区域规划
     * @param devSn 设备序列号
     * @throws MyException 自定义异常
     */
    private void checkCodeAndDevSn(String code, String devSn) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code).eq(Device::getDevRegistrationNo, devSn).count();
        if (count != 0){
            throw new MyException(ResultEnum.DEVSN_EXIST);
        }
    }

    @Override
    public boolean delete(Long id){
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(DeviceVO deviceVO){
        if (!StringUtils.isEmpty(deviceVO.getCode()) && !StringUtils.isEmpty(deviceVO.getDevSn())) {
            checkCodeAndDevSn(deviceVO.getId(), deviceVO.getCode(), deviceVO.getDevSn());
        }
        if (!StringUtils.isEmpty(deviceVO.getCode()) && !StringUtils.isEmpty(deviceVO.getDevRegistrationNo())) {
            checkCodeAndDevRegistrationNo(deviceVO.getId(), deviceVO.getCode(), deviceVO.getDevRegistrationNo());
        }
        Device device = Conversion.changeOne(deviceVO, Device.class);
        baseMapper.updateById(device);
        return true;
    }

    private void checkCodeAndDevRegistrationNo(Long id, String code, String devRegistrationNo) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code)
                .eq(Device::getDevRegistrationNo, devRegistrationNo)
                .ne(Device::getId, id).count();
        if (count != 0){
            throw new MyException(ResultEnum.DEVREGISTRATIONNO_EXIST);
        }
    }

    private void checkCodeAndDevSn(Long id, String code, String devSn) throws MyException {
        int count = lambdaQuery().eq(Device::getCode, code)
                .eq(Device::getDevSn, devSn)
                .ne(Device::getId, id).count();
        if (count != 0){
            throw new MyException(ResultEnum.DEVSN_EXIST);
        }
    }

    @Override
    public DeviceVO findById(Long id){
        Device device = baseMapper.selectById(id);
        return Conversion.changeOne(device, DeviceVO.class);
    }
}
