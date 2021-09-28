package com.hnly.provincial.service.waterquota.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.waterquota.WaterQuotaMapper;
import com.hnly.provincial.entity.waterquota.WaterQuota;
import com.hnly.provincial.entity.waterquota.WaterQuotaVO;
import com.hnly.provincial.service.waterquota.IWaterQuotaService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用水定额 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-28
 */
@Service
public class WaterQuotaServiceImpl extends ServiceImpl<WaterQuotaMapper, WaterQuota> implements IWaterQuotaService {

    @Override
    public TableDataUtils<List<WaterQuotaVO>> findListByPage(WaterQuotaVO waterQuotaVO) {
        Page<WaterQuota> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(waterQuotaVO.getCode()), WaterQuota::getCode, waterQuotaVO.getCode())
                .page(waterQuotaVO.page());
        List<WaterQuotaVO> waterQuotaVOs = Conversion.changeList(page.getRecords(), WaterQuotaVO.class);
        return TableDataUtils.success(page.getTotal(), waterQuotaVOs);
    }

    @Override
    public boolean add(WaterQuotaVO waterQuotaVO) {
        checkCode(waterQuotaVO.getId(), waterQuotaVO.getCode());
        waterQuotaVO.setCreateTime(new Date());
        WaterQuota waterQuota = Conversion.changeOne(waterQuotaVO, WaterQuota.class);
        baseMapper.insert(waterQuota);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(WaterQuotaVO waterQuotaVO) {
        checkCode(waterQuotaVO.getId(), waterQuotaVO.getCode());
        waterQuotaVO.setUpdateTime(new Date());
        WaterQuota waterQuota = Conversion.changeOne(waterQuotaVO, WaterQuota.class);
        baseMapper.updateById(waterQuota);
        return true;
    }

    /**
     * 校验该区域规划是否已经填写过用水定额
     *
     * @param id   id
     * @param code 行政区划
     * @throws MyException 抛出自定义异常 行政区划已存在
     */
    private void checkCode(Long id, String code) throws MyException {
        int count = lambdaQuery().eq(WaterQuota::getCode, code)
                .ne(id != null, WaterQuota::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.CODELIMIT_EXIST);
        }
    }

    @Override
    public WaterQuotaVO findById(Long id) {
        WaterQuota waterQuota = baseMapper.selectById(id);
        return Conversion.changeOne(waterQuota, WaterQuotaVO.class);
    }
}
