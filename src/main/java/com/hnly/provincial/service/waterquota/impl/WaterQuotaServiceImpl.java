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
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.waterquota.IWaterQuotaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Resource
    private IAreaService iAreaService;

    @Override
    public TableDataUtils<List<WaterQuotaVO>> findListByPage(WaterQuotaVO waterQuotaVO) {
        Page<WaterQuota> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(waterQuotaVO.getCode()), WaterQuota::getCode, waterQuotaVO.getCode())
                .eq(waterQuotaVO.getYear() != 0, WaterQuota::getYear, waterQuotaVO.getYear())
                .page(waterQuotaVO.page());
        List<WaterQuotaVO> waterQuota = Conversion.changeList(page.getRecords(), WaterQuotaVO.class);
        for (WaterQuotaVO vo : waterQuota) {
            Map<String, String> allAreaName = iAreaService.getAllAreaName(vo.getCode());
            vo.setVillageName(allAreaName.get("cun"));
            vo.setTownshipName(allAreaName.get("xiang"));
            vo.setCountyName(allAreaName.get("xian"));
            vo.setCityName(allAreaName.get("shi"));
        }
        return TableDataUtils.success(page.getTotal(), waterQuota);
    }

    @Override
    public boolean add(WaterQuotaVO waterQuotaVO) {
        checkCode(waterQuotaVO.getId(), waterQuotaVO.getCode(), waterQuotaVO.getYear());
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
        checkCode(waterQuotaVO.getId(), waterQuotaVO.getCode(), waterQuotaVO.getYear());
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
    private void checkCode(Long id, String code, int year) throws MyException {
        int count = lambdaQuery().eq(WaterQuota::getCode, code)
                .eq(WaterQuota::getYear, year)
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
