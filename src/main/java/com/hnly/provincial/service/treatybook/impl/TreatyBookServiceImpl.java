package com.hnly.provincial.service.treatybook.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.treatybook.TreatyBookMapper;
import com.hnly.provincial.entity.treatybook.TreatyBook;
import com.hnly.provincial.entity.treatybook.TreatyBookVO;
import com.hnly.provincial.service.treatybook.ITreatyBookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 协议书 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-15
 */
@Service
public class TreatyBookServiceImpl extends ServiceImpl<TreatyBookMapper, TreatyBook> implements ITreatyBookService {

    @Override
    public TableDataUtils<List<TreatyBookVO>> findListByPage(TreatyBookVO treatyBookVO) {
        Page<TreatyBook> page = lambdaQuery()
                .like(!StringUtils.isEmpty(treatyBookVO.getPartya()), TreatyBook::getPartya, treatyBookVO.getPartya())
                .like(!StringUtils.isEmpty(treatyBookVO.getPartyb()), TreatyBook::getPartyb, treatyBookVO.getPartyb())
                .like(!StringUtils.isEmpty(treatyBookVO.getName()), TreatyBook::getName, treatyBookVO.getName())
                .orderByDesc(TreatyBook::getId)
                .page(treatyBookVO.page());
        List<TreatyBookVO> treatyBookVOList = Conversion.changeList(page.getRecords(), TreatyBookVO.class);
        return TableDataUtils.success(page.getTotal(), treatyBookVOList);
    }

    @Override
    public boolean add(TreatyBookVO treatyBookVO) {
        TreatyBook treatyBook = Conversion.changeOne(treatyBookVO, TreatyBook.class);
        baseMapper.insert(treatyBook);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(TreatyBookVO treatyBookVO) {
        TreatyBook treatyBook = Conversion.changeOne(treatyBookVO, TreatyBook.class);
        baseMapper.updateById(treatyBook);
        return true;
    }

    @Override
    public TreatyBookVO findById(Long id) {
        TreatyBook treatyBook = baseMapper.selectById(id);
        return Conversion.changeOne(treatyBook, TreatyBookVO.class);
    }
}
