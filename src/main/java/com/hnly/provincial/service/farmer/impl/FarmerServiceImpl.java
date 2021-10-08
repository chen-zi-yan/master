package com.hnly.provincial.service.farmer.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.farmer.FarmerMapper;
import com.hnly.provincial.entity.area.AreaName;
import com.hnly.provincial.entity.farmer.Farmer;
import com.hnly.provincial.entity.farmer.FarmerVO;
import com.hnly.provincial.entity.ic.Ic;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.farmer.IFarmerService;
import com.hnly.provincial.service.ic.IIcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 农户表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-08
 */
@Slf4j
@Service
public class FarmerServiceImpl extends ServiceImpl<FarmerMapper, Farmer> implements IFarmerService {

    @Resource
    private IAreaService iAreaService;

    @Resource
    private IIcService icService;

    @Override
    public TableDataUtils<List<FarmerVO>> findListByPage(FarmerVO farmerVO) {
        Page<Farmer> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(farmerVO.getName()), Farmer::getName, farmerVO.getName())
                .likeRight(!StringUtils.isEmpty(farmerVO.getCode()), Farmer::getCode, farmerVO.getCode())
                .likeRight(!StringUtils.isEmpty(farmerVO.getPhone()), Farmer::getPhone, farmerVO.getPhone())
                .likeRight(!StringUtils.isEmpty(farmerVO.getIdCard()), Farmer::getIdCard, farmerVO.getIdCard())
                .page(farmerVO.page());
        List<FarmerVO> farmerVOList = Conversion.changeList(page.getRecords(), FarmerVO.class);
        for (FarmerVO vo : farmerVOList) {
            if (!StringUtils.isEmpty(vo.getPhone())) {
                vo.setPhoneHidden(vo.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            }
            if (!StringUtils.isEmpty(vo.getIdCard())) {
                vo.setIdCardHidden(vo.getIdCard().replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2"));
            }
            AreaName allAreaName = iAreaService.getAllAreaName(vo.getCode());
            vo.setName(allAreaName.getCunName());
            vo.setTownshipName(allAreaName.getXiangName());
            vo.setCountyName(allAreaName.getXianName());
            vo.setCityName(allAreaName.getShiName());
        }
        return TableDataUtils.success(page.getTotal(), farmerVOList);
    }

    @Override
    public boolean add(FarmerVO farmerVO) {
        checkIdCard(farmerVO.getIdCard(), farmerVO.getId());
        farmerVO.setCreateTime(new Date());
        Farmer farmer = Conversion.changeOne(farmerVO, Farmer.class);
        baseMapper.insert(farmer);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        checkFarmerId(id);
        baseMapper.deleteById(id);
        return true;
    }

    /**
     * 校验该用户是否有ic卡
     *
     * @param id id
     * @throws MyException 异常抛出该用户持有ic卡
     */
    private void checkFarmerId(Long id) throws MyException {
        int count = icService.lambdaQuery().eq(Ic::getFarmerId, id).count();
        if (count != 0){
            throw new MyException(ResultEnum.HOLDICCODE_EXIST);
        }
    }

    @Override
    public boolean updateData(FarmerVO farmerVO) {
        checkIdCard(farmerVO.getIdCard(), farmerVO.getId());
        farmerVO.setUpdateTime(new Date());
        Farmer farmer = Conversion.changeOne(farmerVO, Farmer.class);
        baseMapper.updateById(farmer);
        return true;
    }

    /**
     * 校验身份证是否唯一
     *
     * @param idCard 身份证
     * @param id id
     * @throws MyException 已存在抛出异常
     */
    private void checkIdCard(String idCard, Long id) throws MyException {
        int count = lambdaQuery().eq(Farmer::getIdCard, idCard)
                .ne(id != null, Farmer::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.IDCODE_EXIST);
        }
    }

    @Override
    public FarmerVO findById(Long id) {
        Farmer farmer = baseMapper.selectById(id);
        return Conversion.changeOne(farmer, FarmerVO.class);
    }

    /**
     * 查询数据中的农户id=农户farmer表中的id
     *
     * @param farmerId 用水数据表中的farmer_id
     * @return 存在则返回农户的名字, 失败返回一个空值
     */
    @Override
    public String getFarmerName(Long farmerId) {
        Farmer byId = baseMapper.selectById(farmerId);
        if (byId == null) {
            return "";
        }
        return byId.getName();
    }
}
