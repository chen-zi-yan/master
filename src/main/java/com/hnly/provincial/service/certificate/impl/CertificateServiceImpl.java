package com.hnly.provincial.service.certificate.impl;

import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.config.interceptor.exception.MyException;
import com.hnly.provincial.entity.certificate.Certificate;
import com.hnly.provincial.entity.certificate.CertificateVO;
import com.hnly.provincial.dao.certificate.CertificateMapper;
import com.hnly.provincial.service.certificate.ICertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public TableDataUtils<List<CertificateVO>> findListByPage(CertificateVO certificateVO){
        Page<Certificate> page = lambdaQuery().page(certificateVO.page());
        List<CertificateVO> certificateVOs = Conversion.changeList(page.getRecords(), CertificateVO.class);
        return TableDataUtils.success(page.getTotal(), certificateVOs);
    }

    @Override
    public boolean add(CertificateVO certificateVO){
        checkCarId(certificateVO.getCarId());
        Certificate certificate = Conversion.changeOne(certificateVO, Certificate.class);
        baseMapper.insert(certificate);
        return true;
    }

    /**
     * 校验该身份证号码或组织机构代码是否存在，不存在则通过
     *
     * @param carId 身份证号码或组织机构代码
     * @throws MyException 自定义异常
     */
    private void checkCarId(String carId) throws MyException {
        int count = lambdaQuery().eq(Certificate::getCarId, carId).count();
        if (count != 0){
            throw new MyException(ResultEnum.carId_EXIST);
        }
    }

    @Override
    public boolean delete(Long id){
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(CertificateVO certificateVO){
        checkCarIdDivideId(certificateVO.getId(), certificateVO.getCarId());
        Certificate certificate = Conversion.changeOne(certificateVO, Certificate.class);
        baseMapper.updateById(certificate);
        return true;
    }

    private void checkCarIdDivideId(Long id, String carId) {
        int count = lambdaQuery().eq(Certificate::getCarId, carId)
                .ne(Certificate::getId, id).count();
        if (count != 0){
            throw new MyException(ResultEnum.carId_EXIST);
        }
    }

    @Override
    public CertificateVO findById(Long id){
        Certificate certificate = baseMapper.selectById(id);
        return Conversion.changeOne(certificate, CertificateVO.class);
    }
}
