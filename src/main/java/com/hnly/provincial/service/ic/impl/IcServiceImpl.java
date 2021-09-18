package com.hnly.provincial.service.ic.impl;

import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.entity.ic.Ic;
import com.hnly.provincial.entity.ic.IcVO;
import com.hnly.provincial.dao.ic.IcMapper;
import com.hnly.provincial.service.ic.IIcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 卡号表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Service
public class IcServiceImpl extends ServiceImpl<IcMapper, Ic> implements IIcService {

    @Override
    public TableDataUtils<List<IcVO>> findListByPage(IcVO icVO) {
        Page<Ic> page = lambdaQuery().page(icVO.page());
        List<IcVO> icVOs = Conversion.changeList(page.getRecords(), IcVO.class);
        return TableDataUtils.success(page.getTotal(), icVOs);
    }

    @Override
    public boolean add(IcVO icVO) {
        checkIcCode(icVO.getId(), icVO.getIcCode());
        icVO.setCreateTime(new Date());
        Ic ic = Conversion.changeOne(icVO, Ic.class);
        baseMapper.insert(ic);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(IcVO icVO) {
        checkIcCode(icVO.getId(), icVO.getIcCode());
        icVO.setUpdateTime(new Date());
        Ic ic = Conversion.changeOne(icVO, Ic.class);
        baseMapper.updateById(ic);
        return true;
    }

    /**
     * 校验ic卡号是否唯一
     *
     * @param id     id
     * @param icCode ic卡号
     * @throws MyException 异常抛出ic卡已存在
     */
    private void checkIcCode(Long id, String icCode) throws MyException {
        int count = lambdaQuery().eq(Ic::getIcCode, icCode)
                .ne(Ic::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.IC_EXIST);
        }
    }

    @Override
    public IcVO findById(Long id) {
        Ic ic = baseMapper.selectById(id);
        return Conversion.changeOne(ic, IcVO.class);
    }
}
