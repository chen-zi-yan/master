package ${package.Controller};

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.ResultEnum;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${package.Entity}.${entity}VO;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.util.List;

/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@Slf4j
@Api(tags = {"${table.comment!}"})
@RestController
<#else>
@Controller
</#if>@RequestMapping("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if><#else><#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{<#else>public class ${table.controllerName} {</#if>

    @Resource
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};

    @ApiOperation(value = "新增${table.comment!}")
    @PostMapping()
    public JsonBean<?> add(${entity}VO ${entity?uncap_first}VO){
        ${(table.serviceName?substring(1))?uncap_first}.add(${entity?uncap_first}VO);
        return JsonBean.success();
    }

    @ApiOperation(value = "删除${table.comment!}")
    @DeleteMapping("{id}")
    public JsonBean<?> delete(@PathVariable("id") Long id){
        ${(table.serviceName?substring(1))?uncap_first}.delete(id);
        return JsonBean.success();
    }

    @ApiOperation(value = "更新${table.comment!}")
    @PutMapping()
    public JsonBean<?> update(${entity}VO ${entity?uncap_first}VO){
        ${(table.serviceName?substring(1))?uncap_first}.updateData(${entity?uncap_first}VO);
        return JsonBean.success();
    }

    @ApiOperation(value = "查询${table.comment!}分页数据")
    @GetMapping()
    public JsonBean<TableDataUtils<List<${entity}VO>>> findListByPage(${entity}VO ${entity?uncap_first}VO){
        return JsonBean.success(${(table.serviceName?substring(1))?uncap_first}.findListByPage(${entity?uncap_first}VO));
    }

    @ApiOperation(value = "id查询${table.comment!}")
    @GetMapping("{id}")
    public JsonBean<${entity}VO> findById(@PathVariable Long id){
        return JsonBean.success(${(table.serviceName?substring(1))?uncap_first}.findById(id));
    }
}
</#if>
