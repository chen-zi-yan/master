package com.hnly.provincial.service.ladderwaterprice.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.ladderwaterprice.LadderWaterPriceMapper;
import com.hnly.provincial.entity.ladderwaterprice.LadderWaterPrice;
import com.hnly.provincial.entity.ladderwaterprice.LadderWaterPriceVO;
import com.hnly.provincial.service.ladderwaterprice.ILadderWaterPriceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 阶梯水价表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Service
public class LadderWaterPriceServiceImpl extends ServiceImpl<LadderWaterPriceMapper, LadderWaterPrice> implements ILadderWaterPriceService {

    @Override
    public TableDataUtils<List<LadderWaterPriceVO>> findListByPage(LadderWaterPriceVO ladderWaterPriceVO) {
        Page<LadderWaterPrice> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(ladderWaterPriceVO.getArea()), LadderWaterPrice::getArea, ladderWaterPriceVO.getArea())
                .page(ladderWaterPriceVO.page());
        List<LadderWaterPriceVO> ladderWaterPriceVOs = Conversion.changeList(page.getRecords(), LadderWaterPriceVO.class);
        return TableDataUtils.success(page.getTotal(), ladderWaterPriceVOs);
    }

    @Override
    public boolean add(LadderWaterPriceVO ladderWaterPriceVO) {
        checkArea(ladderWaterPriceVO.getId(), ladderWaterPriceVO.getArea());
        ladderWaterPriceVO.setCreateTime(new Date());
        LadderWaterPrice ladderWaterPrice = Conversion.changeOne(ladderWaterPriceVO, LadderWaterPrice.class);
        baseMapper.insert(ladderWaterPrice);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(LadderWaterPriceVO ladderWaterPriceVO) {
        checkArea(ladderWaterPriceVO.getId(), ladderWaterPriceVO.getArea());
        ladderWaterPriceVO.setUpdateTime(new Date());
        LadderWaterPrice ladderWaterPrice = Conversion.changeOne(ladderWaterPriceVO, LadderWaterPrice.class);
        baseMapper.updateById(ladderWaterPrice);
        return true;
    }


    /**
     * 校验地区是否已经存在</br>
     * 添加数据时id不作为条件进行校验,修改数据时将需要校验id排除本身
     *
     * @param id   id
     * @param area 地区
     * @throws MyException 抛出该地区已经存在
     */
    private void checkArea(Long id, String area) throws MyException {
        int count = lambdaQuery().eq(LadderWaterPrice::getArea, area)
                .ne(id != null, LadderWaterPrice::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.CODE_EXIST);
        }
    }

    @Override
    public LadderWaterPriceVO findById(Long id) {
        LadderWaterPrice ladderWaterPrice = baseMapper.selectById(id);
        return Conversion.changeOne(ladderWaterPrice, LadderWaterPriceVO.class);
    }


}
