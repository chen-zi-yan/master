package com.hnly.provincial.service.ladderwaterprice.impl;

import com.alibaba.druid.util.StringUtils;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.entity.ladderwaterprice.LadderWaterPrice;
import com.hnly.provincial.entity.ladderwaterprice.LadderWaterPriceVO;
import com.hnly.provincial.dao.ladderwaterprice.LadderWaterPriceMapper;
import com.hnly.provincial.service.ladderwaterprice.ILadderWaterPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
        checkBigDecimal(ladderWaterPriceVO);
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
        checkBigDecimal(ladderWaterPriceVO);
        checkArea(ladderWaterPriceVO.getId(), ladderWaterPriceVO.getArea());
        ladderWaterPriceVO.setUpdateTime(new Date());
        LadderWaterPrice ladderWaterPrice = Conversion.changeOne(ladderWaterPriceVO, LadderWaterPrice.class);
        baseMapper.updateById(ladderWaterPrice);
        return true;
    }

    /**
     * 校验输入的小数格式是否符合要求
     *
     * @param ladderWaterPriceVO 阶梯水价表对象
     * @throws MyException 抛出输入的数据个数不正确
     */
    private void checkBigDecimal(LadderWaterPriceVO ladderWaterPriceVO) throws MyException {
        String reg = "^(\\d{1,4}\\.\\d)$";
        String reg1 = "^(\\d\\.\\d{1,2})$";
        String reg2 = "^(\\d{1,2}\\.\\d{1,2})$";
        boolean waterPrice = String.valueOf(ladderWaterPriceVO.getWaterPrice()).matches(reg2);
        boolean firstMultiple = String.valueOf(ladderWaterPriceVO.getFirstMultiple()).matches(reg1);
        boolean secondMultiple = String.valueOf(ladderWaterPriceVO.getSecondMultiple()).matches(reg1);
        boolean thirdMultiple = String.valueOf(ladderWaterPriceVO.getThirdMultiple()).matches(reg1);
        boolean firstOrderWarterPrice = String.valueOf(ladderWaterPriceVO.getFirstOrderWarterPrice()).matches(reg2);
        boolean secondOrderWarterPrice = String.valueOf(ladderWaterPriceVO.getSecondOrderWarterPrice()).matches(reg2);
        boolean thirdOrderWarterPrice = String.valueOf(ladderWaterPriceVO.getThirdOrderWarterPrice()).matches(reg2);
        boolean electrovalence = String.valueOf(ladderWaterPriceVO.getElectrovalence()).matches(reg);
        if (!firstMultiple || !secondMultiple || !thirdMultiple) {
            throw new MyException(ResultEnum.BIGDECIMAL1_EXIST);
        }
        if (!waterPrice || !firstOrderWarterPrice || !secondOrderWarterPrice || !thirdOrderWarterPrice) {
            throw new MyException(ResultEnum.BIGDECIMAL2_EXIST);
        }
        if (!electrovalence) {
            throw new MyException(ResultEnum.BIGDECIMAL_EXIST);
        }
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
