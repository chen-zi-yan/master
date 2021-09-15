package com.hnly.provincial.controller.treatybook;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.treatybook.TreatyBookVO;
import com.hnly.provincial.service.treatybook.ITreatyBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* <p>
* 协议书 前端控制器
* </p>
*
* @author czy
* @since 2021-09-15
*/
@Slf4j
@Tag(name = "协议书")
@RestController
@RequestMapping("treaty-book")
public class TreatyBookController {

    @Resource
    private ITreatyBookService treatyBookService;

    @Operation(summary = "新增协议书")
    @PostMapping()
    public JsonBean<String> add(@RequestBody TreatyBookVO treatyBookVO){
        treatyBookService.add(treatyBookVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除协议书")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id){
        treatyBookService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新协议书")
    @PutMapping()
    public JsonBean<String> update(@RequestBody TreatyBookVO treatyBookVO){
        treatyBookService.updateData(treatyBookVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询协议书分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<TreatyBookVO>>> findListByPage(TreatyBookVO treatyBookVO){
        return JsonBean.success(treatyBookService.findListByPage(treatyBookVO));
    }

    @Operation(summary = "id查询协议书")
    @GetMapping("{id}")
    public JsonBean<TreatyBookVO> findById(@PathVariable Long id){
        return JsonBean.success(treatyBookService.findById(id));
    }
}
