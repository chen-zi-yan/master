package com.hnly.provincial.service.certificate;

import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.certificate.Certificate;
import com.hnly.provincial.entity.certificate.CertificateVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.JsonBean;
import java.util.List;

/**
* <p>
* 产权证 服务类
* </p>
*
* @author czy
* @since 2021-09-14
*/
public interface ICertificateService extends IService<Certificate> {

    /**
    * 查询产权证分页数据
    *
    * @param certificateVO     条件
    * @return 分页结果
    */
    TableDataUtils<List<CertificateVO>> findListByPage(CertificateVO certificateVO);

    /**
    * 添加产权证
    * @param certificateVO
    * @return false 失败   true 成功
    */
    boolean add(CertificateVO certificateVO);

    /**
    * 删除产权证
    *
    * @param id 主键
    * @return false 失败   true 成功
    */
    boolean delete(Long id);

    /**
    * 修改产权证
    *
    * @param certificateVO
    * @return false 失败   true 成功
    */
    boolean updateData(CertificateVO certificateVO);

    /**
    * id查询数据
    *
    * @param id id
    * @return CertificateVO
    */
    CertificateVO findById(Long id);
}
