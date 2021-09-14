package com.hnly.provincial.controller.certificate;

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.certificate.CertificateVO;
import com.hnly.provincial.service.certificate.ICertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* <p>
* 产权证 前端控制器
* </p>
*
* @author czy
* @since 2021-09-14
*/
@Slf4j
@Tag(name = "产权证")
@RestController
@RequestMapping("certificate")
public class CertificateController {

    @Resource
    private ICertificateService certificateService;

    @Operation(summary = "新增产权证")
    @PostMapping()
    public JsonBean<String> add(@RequestBody @Validated CertificateVO certificateVO){
        certificateService.add(certificateVO);
        return JsonBean.success();
    }

    @Operation(summary = "删除产权证")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id){
        certificateService.delete(id);
        return JsonBean.success();
    }

    @Operation(summary = "更新产权证")
    @PutMapping()
    public JsonBean<String> update(@RequestBody CertificateVO certificateVO){
        certificateService.updateData(certificateVO);
        return JsonBean.success();
    }

    @Operation(summary = "查询产权证分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<CertificateVO>>> findListByPage(CertificateVO certificateVO){
        return JsonBean.success(certificateService.findListByPage(certificateVO));
    }

    @Operation(summary = "id查询产权证")
    @GetMapping("{id}")
    public JsonBean<CertificateVO> findById(@PathVariable Long id){
        return JsonBean.success(certificateService.findById(id));
    }
}
