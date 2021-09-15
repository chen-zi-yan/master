package com.hnly.provincial.service.shiyong.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.shiyong.ShiyongMapper;
import com.hnly.provincial.entity.shiyong.Shiyong;
import com.hnly.provincial.entity.shiyong.ShiyongVO;
import com.hnly.provincial.service.shiyong.IShiyongService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 使用权证 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-14
 */
@Service
public class ShiyongServiceImpl extends ServiceImpl<ShiyongMapper, Shiyong> implements IShiyongService {

    @Override
    public TableDataUtils<List<ShiyongVO>> findListByPage(ShiyongVO shiyongVO) {
        Page<Shiyong> page = lambdaQuery()
                .likeRight(Shiyong::getNumber, shiyongVO.getNumber())
                .likeRight(Shiyong::getArea, shiyongVO.getArea())
                .likeRight(Shiyong::getIdNumber, shiyongVO.getIdNumber())
                .page(shiyongVO.page());
        List<ShiyongVO> shiyongVOs = Conversion.changeList(page.getRecords(), ShiyongVO.class);
        return TableDataUtils.success(page.getTotal(), shiyongVOs);
    }

    @Override
    public boolean add(ShiyongVO shiyongVO) {
        checkNumber(shiyongVO.getNumber());
        checkArea(shiyongVO.getArea());
        checkIdNumber(shiyongVO.getIdNumber());
        Shiyong shiyong = Conversion.changeOne(shiyongVO, Shiyong.class);
        baseMapper.insert(shiyong);
        return true;
    }

    /**
     * 校验使用权人身份证或法人证书号码是否存在,不存在则通过
     *
     * @param idNumber 使用权人身份证或法人证书号码
     * @throws MyException 自定义异常
     */
    private void checkIdNumber(String idNumber) throws MyException {
        int count1 = lambdaQuery().eq(Shiyong::getIdNumber, idNumber).count();
        if (count1 != 0) {
            throw new MyException(ResultEnum.CARID_EXIST);
        }
    }

    /**
     * 校验设施所在行政区域是否存在,不存在则通过
     *
     * @param area 设施所在行政区域
     * @throws MyException 自定义异常
     */
    private void checkArea(String area) throws MyException {
        int count = lambdaQuery().eq(Shiyong::getArea, area).count();
        if (count != 0) {
            throw new MyException(ResultEnum.AREA_EXIST);
        }
    }

    /**
     * 校验号码是否存在,不存在则通过
     *
     * @param number 号
     * @throws MyException 自定义异常
     */
    private void checkNumber(String number) throws MyException {
        int count = lambdaQuery().eq(Shiyong::getNumber, number).count();
        if (count != 0) {
            throw new MyException(ResultEnum.NUMBER_EXIST);
        }
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(ShiyongVO shiyongVO) {
        checkNumberDivideId(shiyongVO.getId(), shiyongVO.getNumber());
        checkAreaDivideId(shiyongVO.getId(), shiyongVO.getArea());
        checkIDNumberDivideId(shiyongVO.getId(), shiyongVO.getIdNumber());
        Shiyong shiyong = Conversion.changeOne(shiyongVO, Shiyong.class);
        baseMapper.updateById(shiyong);
        return true;
    }

    /**
     * 校验使用权人身份证或法人证书号码是否存在,不存在则通过
     *
     * @param id       id
     * @param idNumber 使用权人身份证或法人证书号码
     * @throws MyException 自定义异常
     */
    private void checkIDNumberDivideId(Long id, String idNumber) throws MyException {
        int count = lambdaQuery().eq(Shiyong::getIdNumber, idNumber)
                .ne(Shiyong::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.CARID_EXIST);
        }
    }

    /**
     * 校验设施所在行政区域是否存在,不存在则通过
     *
     * @param id   id
     * @param area 设施所在行政区域
     * @throws MyException 自定义异常
     */
    private void checkAreaDivideId(Long id, String area) throws MyException {
        int count = lambdaQuery().eq(Shiyong::getArea, area)
                .ne(Shiyong::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.AREA_EXIST);
        }
    }

    /**
     * 校验号码是否存在,不存在则通过
     *
     * @param id     id
     * @param number 号
     * @throws MyException 自定义异常
     */
    private void checkNumberDivideId(Long id, String number) throws MyException {
        Integer count = lambdaQuery().eq(Shiyong::getNumber, number)
                .ne(Shiyong::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.NUMBER_EXIST);
        }
    }

    @Override
    public ShiyongVO findById(Long id) {
        Shiyong shiyong = baseMapper.selectById(id);
        return Conversion.changeOne(shiyong, ShiyongVO.class);
    }
}
