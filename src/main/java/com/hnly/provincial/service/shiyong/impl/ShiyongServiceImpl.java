package com.hnly.provincial.service.shiyong.impl;

import com.alibaba.druid.util.StringUtils;
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
                .likeRight(!StringUtils.isEmpty(shiyongVO.getNumber()), Shiyong::getNumber, shiyongVO.getNumber())
                .likeRight(!StringUtils.isEmpty(shiyongVO.getArea()), Shiyong::getArea, shiyongVO.getArea())
                .likeRight(!StringUtils.isEmpty(shiyongVO.getIdNumber()), Shiyong::getIdNumber, shiyongVO.getIdNumber())
                .page(shiyongVO.page());
        List<ShiyongVO> shiyongVOs = Conversion.changeList(page.getRecords(), ShiyongVO.class);
        return TableDataUtils.success(page.getTotal(), shiyongVOs);
    }

    @Override
    public boolean add(ShiyongVO shiyongVO) {
        checkNumber(shiyongVO.getId(), shiyongVO.getNumber());
        checkArea(shiyongVO.getId(), shiyongVO.getArea());
        checkIdNumber(shiyongVO.getId(), shiyongVO.getIdNumber());
        Shiyong shiyong = Conversion.changeOne(shiyongVO, Shiyong.class);
        baseMapper.insert(shiyong);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(ShiyongVO shiyongVO) {
        checkNumber(shiyongVO.getId(), shiyongVO.getNumber());
        checkArea(shiyongVO.getId(), shiyongVO.getArea());
        checkIdNumber(shiyongVO.getId(), shiyongVO.getIdNumber());
        Shiyong shiyong = Conversion.changeOne(shiyongVO, Shiyong.class);
        baseMapper.updateById(shiyong);
        return true;
    }

    /**
     * 校验使用权人身份证或法人证书号码是否存在<br/>
     * 添加数据时id不作为条件进行校验,修改数据时将校验id是不是本身
     *
     * @param id       id
     * @param idNumber 使用权人身份证或法人证书号码
     * @throws MyException 自定义异常
     */
    private void checkIdNumber(Long id, String idNumber) throws MyException {
        int count = lambdaQuery().eq(Shiyong::getIdNumber, idNumber)
                .ne(id != null,Shiyong::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.CARID_EXIST);
        }
    }

    /**
     * 校验设施所在行政区域是否存在<br/>
     * 添加数据时id不作为条件进行校验,修改数据时将校验id是不是本身
     *
     * @param id   id
     * @param area 设施所在行政区域
     * @throws MyException 自定义异常
     */
    private void checkArea(Long id, String area) throws MyException {
        int count = lambdaQuery().eq(Shiyong::getArea, area)
                .ne(id != null,Shiyong::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.AREA_EXIST);
        }
    }

    /**
     * 校验号码是否存在<br/>
     * 添加数据时id不作为条件进行校验,修改数据时将校验id是不是本身
     *
     * @param id     id
     * @param number 号
     * @throws MyException 自定义异常
     */
    private void checkNumber(Long id, String number) throws MyException {
        int count = lambdaQuery().eq(Shiyong::getNumber, number)
                .ne(id != null,Shiyong::getId, id).count();
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
