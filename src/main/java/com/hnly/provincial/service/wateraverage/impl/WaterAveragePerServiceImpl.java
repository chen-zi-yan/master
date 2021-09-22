package com.hnly.provincial.service.wateraverage.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.wateraverage.WaterAveragePerMapper;
import com.hnly.provincial.entity.wateraverage.WaterAveragePer;
import com.hnly.provincial.entity.wateraverage.WaterAveragePerVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.wateraverage.IWaterAveragePerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 亩均定额 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-22
 */
@Service
public class WaterAveragePerServiceImpl extends ServiceImpl<WaterAveragePerMapper, WaterAveragePer> implements IWaterAveragePerService {

    @Resource
    private IAreaService iAreaService;

    @Override
    public TableDataUtils<List<WaterAveragePerVO>> findListByPage(WaterAveragePerVO waterAveragePerVO) {
        Page<WaterAveragePer> page = lambdaQuery()
                .likeRight(StringUtils.isEmpty(waterAveragePerVO.getYear()), WaterAveragePer::getYear, waterAveragePerVO.getYear())
                .likeRight(StringUtils.isEmpty(waterAveragePerVO.getAreaCode()), WaterAveragePer::getAreaCode, waterAveragePerVO.getAreaCode())
                .page(waterAveragePerVO.page());
        List<WaterAveragePerVO> waterAveragePerVOs = Conversion.changeList(page.getRecords(), WaterAveragePerVO.class);
        for (WaterAveragePerVO vo : waterAveragePerVOs) {
            Map<String, String> allAreaName = iAreaService.getAllAreaName(vo.getAreaCode());
            vo.setCityName(allAreaName.get("shi"));
            vo.setCountyName(allAreaName.get("xian"));
        }
        return TableDataUtils.success(page.getTotal(), waterAveragePerVOs);
    }

    @Override
    public boolean add(WaterAveragePerVO waterAveragePerVO) {
        checkAreaCode(waterAveragePerVO.getId(), waterAveragePerVO.getAreaCode());
        WaterAveragePer waterAveragePer = Conversion.changeOne(waterAveragePerVO, WaterAveragePer.class);
        baseMapper.insert(waterAveragePer);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(WaterAveragePerVO waterAveragePerVO) {
        checkAreaCode(waterAveragePerVO.getId(), waterAveragePerVO.getAreaCode());
        WaterAveragePer waterAveragePer = Conversion.changeOne(waterAveragePerVO, WaterAveragePer.class);
        baseMapper.updateById(waterAveragePer);
        return true;
    }

    /**
     * 校验县是否已经存在
     *
     * @param id       id
     * @param areaCode 县
     * @throws MyException 自定义异常抛出该县已经存在
     */
    private void checkAreaCode(Long id, String areaCode) throws MyException {
        int count = lambdaQuery().eq(WaterAveragePer::getAreaCode, areaCode)
                .ne(id != null, WaterAveragePer::getId, id)
                .count();
        if (count != 0) {
            throw new MyException(ResultEnum.COUNTY_EXIST);
        }
    }

    @Override
    public WaterAveragePerVO findById(Long id) {
        WaterAveragePer waterAveragePer = baseMapper.selectById(id);
        return Conversion.changeOne(waterAveragePer, WaterAveragePerVO.class);
    }
}
