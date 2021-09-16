package com.hnly.provincial.service.farmer.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.farmer.FarmerMapper;
import com.hnly.provincial.entity.farmer.Farmer;
import com.hnly.provincial.entity.farmer.FarmerVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.farmer.IFarmerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public TableDataUtils<List<FarmerVO>> findListByPage(FarmerVO farmerVO) {
        Page<Farmer> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(farmerVO.getName()), Farmer::getName, farmerVO.getName())
                .likeRight(!StringUtils.isEmpty(farmerVO.getCode()), Farmer::getCode, farmerVO.getCode())
                .likeRight(!StringUtils.isEmpty(farmerVO.getPhone()), Farmer::getPhone, farmerVO.getPhone())
                .likeRight(!StringUtils.isEmpty(farmerVO.getIdCard()), Farmer::getIdCard, farmerVO.getIdCard())
                .page(farmerVO.page());
        List<FarmerVO> farmerVOs = Conversion.changeList(page.getRecords(), FarmerVO.class);
        for (FarmerVO vo : farmerVOs) {
            if (!StringUtils.isEmpty(vo.getPhone())){
                vo.setPhoneHidden(vo.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
            }
            vo.setIdCardHidden(vo.getIdCard().replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2"));
            Map<String, String> allAreaName = iAreaService.getAllAreaName(vo.getCode());
            vo.setName(allAreaName.get("cun"));
            vo.setTownshipName(allAreaName.get("xiang"));
            vo.setCountyName(allAreaName.get("xian"));
            vo.setCityName(allAreaName.get("shi"));
        }
        return TableDataUtils.success(page.getTotal(), farmerVOs);
    }

    @Override
    public boolean add(FarmerVO farmerVO) {
        //身份证号不能存在相同的
        checkIdCard(farmerVO.getIdCard(), farmerVO.getId());
        farmerVO.setCreateTime(new Date());
        Farmer farmer = Conversion.changeOne(farmerVO, Farmer.class);
        baseMapper.insert(farmer);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(FarmerVO farmerVO) {
        //校验身份证号更新
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
                .ne(id != null,Farmer::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.IDCODE_EXIST);
        }
    }

    @Override
    public FarmerVO findById(Long id) {
        Farmer farmer = baseMapper.selectById(id);
        return Conversion.changeOne(farmer, FarmerVO.class);
    }


}
