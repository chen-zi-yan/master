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
                .eq(!StringUtils.isEmpty(farmerVO.getName()), Farmer::getName, farmerVO.getName())
                .likeRight(!StringUtils.isEmpty(farmerVO.getCode()), Farmer::getCode, farmerVO.getCode())
                .likeRight(!StringUtils.isEmpty(farmerVO.getPhone()), Farmer::getPhone, farmerVO.getPhone())
                .eq(!StringUtils.isEmpty(farmerVO.getIdCard()), Farmer::getIdCard, farmerVO.getIdCard())
                .eq(!StringUtils.isEmpty(farmerVO.getIcCode()), Farmer::getIcCode, farmerVO.getIcCode())
                .eq(!StringUtils.isEmpty(farmerVO.getStatus()), Farmer::getStatus, farmerVO.getStatus())
                .page(farmerVO.page());
        List<FarmerVO> farmerVOs = Conversion.changeList(page.getRecords(), FarmerVO.class);
        for (FarmerVO vo : farmerVOs) {
            vo.setPhone(vo.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
            vo.setIdCard(vo.getIdCard().replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2"));
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
        //校验该区划里面是否存在该身份证(在同一个行政区划中,身份证号不能存在相同的)
        checkIdCard(farmerVO.getIdCard());
        //农户编号唯一
        if(StringUtils.isEmpty(farmerVO.getUserRegistrationNo())){
            checkUserRegistrationNo(farmerVO.getUserRegistrationNo());
        }
        //校验卡号,同一个行政区划中只能存在一个
        if (!StringUtils.isEmpty(farmerVO.getIcCode())) {
            checkCodeAndIcCode(farmerVO.getIcCode(), farmerVO.getCode());
        }
        farmerVO.setCreateTime(new Date());
        Farmer farmer = Conversion.changeOne(farmerVO, Farmer.class);
        baseMapper.insert(farmer);
        return true;
    }

    /**
     * 添加时该农户编号需唯一
     * 
     * @param userRegistrationNo 农户编号
     * @throws MyException 已存在抛出异常
     */
    private void checkUserRegistrationNo(String userRegistrationNo) throws MyException {
        int count = lambdaQuery().eq(Farmer::getUserRegistrationNo, userRegistrationNo).count();
        if (count != 0){
            throw new MyException(ResultEnum.GETUSERREGISTRATIONNO_EXIST);
        }
    }

    /**
     * 添加数据时校验该ic卡号是否存在该行政区划中
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

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(FarmerVO farmerVO) {
        //校验身份证号更新
        checkIdCardDivideId(farmerVO.getIdCard(), farmerVO.getId());
        //农户编号需唯一
        if (StringUtils.isEmpty(farmerVO.getUserRegistrationNo())){
            checkUserRegistrationNoDivideId(farmerVO.getUserRegistrationNo(),farmerVO.getId());
        }
        //校验行政区划和校验ic卡号-不为空的时候同区域规划内的ic卡号必须唯一
        Farmer farmerData = baseMapper.selectById(farmerVO.getId());
        if (!StringUtils.isEmpty(farmerVO.getIcCode()) && !StringUtils.isEmpty(farmerVO.getCode())) {
            checkIcCodeAndCode(farmerData.getId(), farmerVO.getIcCode(), farmerVO.getCode());
        } else if (!StringUtils.isEmpty(farmerVO.getIcCode())) {
            checkIcCodeAndCode(farmerData.getId(), farmerVO.getIcCode(), farmerData.getCode());
        } else if (!StringUtils.isEmpty(farmerVO.getCode())) {
            checkIcCodeAndCode(farmerData.getId(), farmerData.getIcCode(), farmerVO.getCode());
        }
        farmerVO.setUpdateTime(new Date());
        Farmer farmer = Conversion.changeOne(farmerVO, Farmer.class);
        baseMapper.updateById(farmer);
        return true;
    }

    /**
     * 修改时排除自身,校验农户编号是否唯一
     *
     * @param userRegistrationNo 农户编号
     * @param id id
     * @throws MyException 存在则抛出异常
     */
    private void checkUserRegistrationNoDivideId(String userRegistrationNo, Long id) throws MyException {
        int count = lambdaQuery().eq(Farmer::getUserRegistrationNo, userRegistrationNo)
                .ne(Farmer::getId, id).count();
        if (count != 0){
            throw new MyException(ResultEnum.GETUSERREGISTRATIONNO_EXIST);
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
     * 修改时排除自身,校验身份证是否唯一
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
