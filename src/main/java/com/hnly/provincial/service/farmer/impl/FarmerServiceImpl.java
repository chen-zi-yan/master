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
import com.hnly.provincial.service.farmer.IFarmerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public TableDataUtils<List<FarmerVO>> findListByPage(FarmerVO farmerVO) {
        Page<Farmer> page = lambdaQuery()
                .eq(farmerVO.getName() != null, Farmer::getName, farmerVO.getName())
                .eq(farmerVO.getCode() != null, Farmer::getCode, farmerVO.getCode())
                .eq(farmerVO.getPhone() != null, Farmer::getPhone, farmerVO.getPhone())
                .eq(farmerVO.getIdCard() != null, Farmer::getIdCard, farmerVO.getIdCard())
                .eq(farmerVO.getIcCode() != null, Farmer::getIcCode, farmerVO.getIcCode())
                .eq(farmerVO.getStatus() != null, Farmer::getStatus, farmerVO.getStatus())
                .page(farmerVO.page());
        List<FarmerVO> farmerVOs = Conversion.changeList(page.getRecords(), FarmerVO.class);
        return TableDataUtils.success(page.getTotal(), farmerVOs);
    }

    @Override
    public boolean add(FarmerVO farmerVO) {
        //校验该区划里面是否存在该身份证(在同一个行政区划中,身份证号不能存在相同的)
        checkIdCard(farmerVO.getIdCard());
        //校验卡号,同一个行政区划中只能存在一个
        if (!StringUtils.isEmpty(farmerVO.getIcCode())) {
            checkCodeAndIcCode(farmerVO.getIcCode(), farmerVO.getCode());
        }
        Farmer farmer = Conversion.changeOne(farmerVO, Farmer.class);
        farmerVO.setCreateTime(new Date());
        farmerVO.setStatus("0");
        baseMapper.insert(farmer);
        return true;
    }

    /**
     * 校验该区划里面是否存在该身份证(在同一个行政区划中,身份证号不能存在相同的)
     *
     * @param idCard 身份证号
     * @throws MyException 已存在抛出异常
     */
    private void checkIdCard(String idCard) throws MyException {
        int count = lambdaQuery().eq(Farmer::getIdCard, idCard).count();
        if (count != 0) {
            throw new MyException(ResultEnum.IDCODE_EXIST);
        }
    }

    /**
     * 校验该ic卡号是否存在该行政区划中
     *
     * @param icCode ic卡号
     * @throws MyException 已存在抛出异常
     */
    private void checkCodeAndIcCode(String icCode, String code) throws MyException {
        int count = lambdaQuery().eq(Farmer::getIcCode, icCode)
                .eq(Farmer::getCode, code).count();
        log.debug("{},{}长度{}", icCode, code, count);
        if (count != 0) {
            throw new MyException(ResultEnum.IC_EXIST);
        }
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(FarmerVO farmerVO) {
        //校验身份证号更新
        if (farmerVO.getIdCard() != null) {
            checkIdCardDivideId(farmerVO.getIdCard(), farmerVO.getId());
        }
        //校验行政区划和校验ic卡号-不为空的时候同区域规划内的ic卡号必须唯一
        Farmer farmerData = baseMapper.selectById(farmerVO.getId());
        if (farmerVO.getIcCode() != null && farmerVO.getCode() != null) {
            checkIcCodeAndCode(farmerData.getId(), farmerVO.getIcCode(), farmerVO.getCode());
        } else if (farmerVO.getIcCode() != null) {
            checkIcCode(farmerData.getId(), farmerData.getCode(), farmerVO.getIcCode());
        } else if (farmerVO.getCode() != null) {
            checkCode(farmerData.getId(), farmerData.getIcCode(), farmerVO.getCode());
        }
        Farmer farmer = Conversion.changeOne(farmerVO, Farmer.class);
        farmer.setUpdateTime(new Date());
        baseMapper.updateById(farmer);
        return true;
    }

    /**
     * 当区域规划有值的时候,校验该ic卡号是否存在该行政区划中
     *
     * @param id     id
     * @param icCode 原数据的ic卡号
     * @param code   传入进来的行政区划码
     * @throws MyException
     */
    private void checkCode(Long id, String icCode, String code) throws MyException {
        int count = lambdaQuery().eq(Farmer::getIcCode, icCode)
                .eq(Farmer::getCode, code)
                .ne(Farmer::getId, id).count();
        log.debug("数量{}", count);
        if (count != 0) {
            throw new MyException(ResultEnum.IC_EXIST);
        }
    }

    /**
     * 当ic卡号有值的时候,校验该ic卡号是否存在该行政区划中
     *
     * @param id     id
     * @param code   原数据中的区域规划码
     * @param icCode 传入进来的ic卡号
     * @throws MyException 已存在抛出异常
     */
    private void checkIcCode(Long id, String code, String icCode) throws MyException {
        int count = lambdaQuery().eq(Farmer::getIcCode, icCode)
                .eq(Farmer::getCode, code)
                .ne(Farmer::getId, id).count();
        log.debug("数量{}", count);
        if (count != 0) {
            throw new MyException(ResultEnum.IC_EXIST);
        }
    }

    /**
     * 在icCode(ic卡号)和code(行政区划)有值时,校验该ic卡号是否存在该行政区划中
     *
     * @param id     id
     * @param icCode 传入进来的ic卡号
     * @param code   传入进来的行政区划码
     * @throws MyException 已存在抛出异常
     */
    private void checkIcCodeAndCode(Long id, String icCode, String code) throws MyException {
        int count = lambdaQuery().eq(Farmer::getIcCode, icCode)
                .eq(Farmer::getCode, code)
                .ne(Farmer::getId, id).count();
        log.debug("数量{}", count);
        if (count != 0) {
            throw new MyException(ResultEnum.IC_EXIST);
        }
    }

    /**
     * 排除自身,校验身份证
     *
     * @param idCard 身份证
     * @param id     id
     * @throws MyException 已存在抛出异常
     */
    private void checkIdCardDivideId(String idCard, Long id) throws MyException {
        int count = lambdaQuery().eq(Farmer::getIdCard, idCard)
                .ne(Farmer::getId, id).count();
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
