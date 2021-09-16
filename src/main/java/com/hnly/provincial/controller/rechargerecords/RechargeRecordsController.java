package com.hnly.provincial.controller.rechargerecords;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.rechargerecords.RechargeRecordsVO;
import com.hnly.provincial.service.rechargerecords.IRechargeRecordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 充值记录 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-16
 */
@Slf4j
@Tag(name = "充值记录")
@RestController
@RequestMapping("recharge-records")
public class RechargeRecordsController {

    @Resource
    private IRechargeRecordsService rechargeRecordsService;

    @Operation(summary = "查询充值记录分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<RechargeRecordsVO>>> findListByPage(RechargeRecordsVO rechargeRecordsVO) {
        return JsonBean.success(rechargeRecordsService.findListByPage(rechargeRecordsVO));
    }

}
