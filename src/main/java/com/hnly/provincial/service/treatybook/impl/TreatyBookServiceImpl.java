package com.hnly.provincial.service.treatybook.impl;

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
                .likeRight(TreatyBook::getPartya, treatyBookVO.getPartya())
                .likeRight(TreatyBook::getPartyb, treatyBookVO.getPartyb())
                .likeRight(TreatyBook::getName, treatyBookVO.getName())
                .page(treatyBookVO.page());
        List<TreatyBookVO> treatyBookVOs = Conversion.changeList(page.getRecords(), TreatyBookVO.class);
        return TableDataUtils.success(page.getTotal(), treatyBookVOs);
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
