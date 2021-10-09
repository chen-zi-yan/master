package com.hnly.provincial.service.certificate.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.dao.certificate.CertificateMapper;
import com.hnly.provincial.entity.certificate.Certificate;
import com.hnly.provincial.entity.certificate.CertificateVO;
import com.hnly.provincial.service.certificate.ICertificateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产权证 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-14
 */
@Service
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    @Override
    public TableDataUtils<List<CertificateVO>> findListByPage(CertificateVO certificateVO) {
        Page<Certificate> page = lambdaQuery()
                .likeRight(!StringUtils.isEmpty(certificateVO.getCode()), Certificate::getCode, certificateVO.getCode())
                .like(!StringUtils.isEmpty(certificateVO.getName()), Certificate::getName, certificateVO.getName())
                .like(!StringUtils.isEmpty(certificateVO.getAddress()), Certificate::getAddress, certificateVO.getAddress())
                .like(!StringUtils.isEmpty(certificateVO.getPropertyOwner()), Certificate::getPropertyOwner, certificateVO.getPropertyOwner())
                .likeRight(!StringUtils.isEmpty(certificateVO.getCarId()), Certificate::getCarId, certificateVO.getCarId())
                .orderByDesc(Certificate::getId)
                .page(certificateVO.page());
        List<CertificateVO> certificateVOList = Conversion.changeList(page.getRecords(), CertificateVO.class);
        return TableDataUtils.success(page.getTotal(), certificateVOList);
    }

    @Override
    public boolean add(CertificateVO certificateVO) {
        checkCode(certificateVO.getId(), certificateVO.getCode());
        checkCarId(certificateVO.getId(), certificateVO.getCarId());
        Certificate certificate = Conversion.changeOne(certificateVO, Certificate.class);
        baseMapper.insert(certificate);
        return true;
    }


    @Override
    public boolean delete(Long id) {
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(CertificateVO certificateVO) {
        checkCode(certificateVO.getId(), certificateVO.getCode());
        checkCarId(certificateVO.getId(), certificateVO.getCarId());
        Certificate certificate = Conversion.changeOne(certificateVO, Certificate.class);
        baseMapper.updateById(certificate);
        return true;
    }

    /**
     * 校验该身份证号码或组织机构代码是否存在<br/>
     * 添加数据时id不作为条件,修改数据时将校验id是不是本身
     *
     * @param id    id
     * @param carId 身份证号码或组织机构代码
     * @throws MyException 自定义异常:该身份证号码或组织机构代码已经存在
     */
    private void checkCarId(Long id, String carId) throws MyException {
        int count = lambdaQuery().eq(Certificate::getCarId, carId)
                .ne(id != null, Certificate::getId, id)
                .count();
        if (count != 0) {
            throw new MyException(ResultEnum.CARID_EXIST);
        }
    }

    /**
     * 校验编号是否存在<br/>
     * 添加数据时id不作为条件,修改数据时将校验id是不是本身
     *
     * @param id   id
     * @param code 编号
     * @throws MyException 自定义异常:已经存在
     */
    private void checkCode(Long id, String code) throws MyException {
        int count = lambdaQuery().eq(Certificate::getCode, code)
                .ne(id != null, Certificate::getId, id).count();
        if (count != 0) {
            throw new MyException(ResultEnum.CODE_EXIST);
        }
    }

    @Override
    public CertificateVO findById(Long id) {
        Certificate certificate = baseMapper.selectById(id);
        return Conversion.changeOne(certificate, CertificateVO.class);
    }
}
