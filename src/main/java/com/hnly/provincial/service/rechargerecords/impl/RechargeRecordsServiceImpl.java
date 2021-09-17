package com.hnly.provincial.service.rechargerecords.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.dao.rechargerecords.RechargeRecordsMapper;
import com.hnly.provincial.entity.rechargerecords.RechargeRecords;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsDTO;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;
import com.hnly.provincial.service.area.IAreaService;
import com.hnly.provincial.service.rechargerecords.IRechargeRecordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 充值记录 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Service
public class RechargeRecordsServiceImpl extends ServiceImpl<RechargeRecordsMapper, RechargeRecords> implements IRechargeRecordsService {

    @Resource
    private RechargeRecordsMapper rechargeRecordsMapper;

    @Resource
    private IAreaService iAreaService;

    @Override
    public TableDataUtils<List<RechargeRecordsVO>> findListByPage(RechargeRecordsDTO rechargeRecordsDTO) {

        IPage<RechargeRecordsDTO> rechargeRecordsDTOIPage =
                rechargeRecordsMapper.selectData();

        List<RechargeRecordsDTO> RechargeRecords = Conversion.changeList(rechargeRecordsDTOIPage.getRecords(), RechargeRecordsDTO.class);
        for (RechargeRecordsDTO dto : RechargeRecords) {
            Map<String, String> allName = iAreaService.getAllAreaName(dto.getCode());
            dto.setName(allName.get("cun"));
            dto.setTownshipName(allName.get("xiang"));
            dto.setCountyName(allName.get("xian"));
            dto.setCityName(allName.get("shi"));
        }

//        return TableDataUtils.success(RechargeRecords.getTotal(), RechargeRecords);
        return null;
    }


}
