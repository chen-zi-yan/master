package com.hnly.provincial.dao.rechargerecords;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;

/**
 * <p>
 * 充值记录 Mapper 接口
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
public interface RechargeRecordsMapper extends BaseMapper<RechargeRecords> {

    /**
     * 充值记录,分页查询数据,模糊查询
     *
     * @param page 包含了当前页和每页显示条数
     * @param code 区域号
     * @param name 农户名字
     * @return 返回RechargeRecordsDTO
     */
    IPage<RechargeRecordsVO> selectData(Page<RechargeRecords> page, String code, String name);

}
