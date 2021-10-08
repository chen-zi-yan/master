package com.hnly.provincial.service.shiyong.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.shiyong.ShiYongMapper;
import com.hnly.provincial.entity.area.AreaName;
import com.hnly.provincial.entity.shiyong.ShiYong;
import com.hnly.provincial.entity.shiyong.ShiYongVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.shiyong.IShiYongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ShiYongServiceImpl extends ServiceImpl<ShiYongMapper, ShiYong> implements IShiYongService {

    @Resource
    private IAreaService iAreaService;

    @Override
    public TableDataUtils<List<ShiYongVO>> findListByPage(ShiYongVO shiYongVO) {
        Page<ShiYong> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(shiYongVO.getNumber()), ShiYong::getNumber, shiYongVO.getNumber())
                .likeRight(!StringUtils.isEmpty(shiYongVO.getArea()), ShiYong::getArea, shiYongVO.getArea())
                .like(!StringUtils.isEmpty(shiYongVO.getPropertyOwner()), ShiYong::getPropertyOwner, shiYongVO.getPropertyOwner())
                .like(!StringUtils.isEmpty(shiYongVO.getRightHolder()), ShiYong::getRightHolder, shiYongVO.getRightHolder())
                .likeRight(!StringUtils.isEmpty(shiYongVO.getIdNumber()), ShiYong::getIdNumber, shiYongVO.getIdNumber())
                .page(shiYongVO.page());
        List<ShiYongVO> shiYongVOList = Conversion.changeList(page.getRecords(), ShiYongVO.class);
        for (ShiYongVO vo : shiYongVOList) {
            AreaName allAreaName = iAreaService.getAllAreaName(vo.getArea());
            vo.setCityName(allAreaName.getShiName());
            vo.setCountyName(allAreaName.getXianName());
            vo.setTownshipName(allAreaName.getXiangName());
            vo.setVillageName(allAreaName.getCunName());
        }
        return TableDataUtils.success(page.getTotal(), shiYongVOList);
    }

    @Override
    public boolean add(ShiYongVO shiYongVO) {
        checkNumber(shiYongVO.getId(), shiYongVO.getNumber());
        checkArea(shiYongVO.getId(), shiYongVO.getArea());
        checkIdNumber(shiYongVO.getId(), shiYongVO.getIdNumber());
        ShiYong shiYong = Conversion.changeOne(shiYongVO, ShiYong.class);
        baseMapper.insert(shiYong);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(ShiYongVO shiYongVO) {
        checkNumber(shiYongVO.getId(), shiYongVO.getNumber());
        checkArea(shiYongVO.getId(), shiYongVO.getArea());
        checkIdNumber(shiYongVO.getId(), shiYongVO.getIdNumber());
        ShiYong shiYong = Conversion.changeOne(shiYongVO, ShiYong.class);
        baseMapper.updateById(shiYong);
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
        int count = lambdaQuery().eq(ShiYong::getIdNumber, idNumber)
                .ne(id != null, ShiYong::getId, id).count();
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
        int count = lambdaQuery().eq(ShiYong::getArea, area)
                .ne(id != null, ShiYong::getId, id).count();
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
        int count = lambdaQuery().eq(ShiYong::getNumber, number)
                .ne(id != null, ShiYong::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.NUMBER_EXIST);
        }
    }

    @Override
    public ShiYongVO findById(Long id) {
        ShiYong shiyong = baseMapper.selectById(id);
        return Conversion.changeOne(shiyong, ShiYongVO.class);
    }
}
