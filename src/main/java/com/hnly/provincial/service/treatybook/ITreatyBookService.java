package com.hnly.provincial.service.treatybook;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.treatybook.TreatyBook;
import com.hnly.provincial.entity.treatybook.TreatyBookVO;

import java.util.List;

/**
* <p>
* 协议书 服务类
* </p>
*
* @author czy
* @since 2021-09-15
*/
public interface ITreatyBookService extends IService<TreatyBook> {

    /**
    * 查询协议书分页数据
    *
    * @param treatyBookVO     条件
    * @return 分页结果
    */
    TableDataUtils<List<TreatyBookVO>> findListByPage(TreatyBookVO treatyBookVO);

    /**
    * 添加协议书
    * @param treatyBookVO
    * @return false 失败   true 成功
    */
    boolean add(TreatyBookVO treatyBookVO);

    /**
    * 删除协议书
    *
    * @param id 主键
    * @return false 失败   true 成功
    */
    boolean delete(Long id);

    /**
    * 修改协议书
    *
    * @param treatyBookVO
    * @return false 失败   true 成功
    */
    boolean updateData(TreatyBookVO treatyBookVO);

    /**
    * id查询数据
    *
    * @param id id
    * @return TreatyBookVO
    */
    TreatyBookVO findById(Long id);
}
