package ${package.Controller};

import com.hnly.provincial.comm.JsonBean;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.ResultEnum;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${package.Entity}.${entity}VO;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "${table.comment!}")
@RestController
<#else>
@Controller
</#if>@RequestMapping("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if><#else><#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{<#else>public class ${table.controllerName} {</#if>

    @Resource
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};

    @Operation(summary = "新增${table.comment!}")
    @PostMapping()
    public JsonBean<String> add(@RequestBody ${entity}VO ${entity?uncap_first}VO){
        ${(table.serviceName?substring(1))?uncap_first}.add(${entity?uncap_first}VO);
        return JsonBean.success();
    }

    @Operation(summary = "删除${table.comment!}")
    @DeleteMapping("{id}")
    public JsonBean<String> delete(@PathVariable("id") Long id){
        ${(table.serviceName?substring(1))?uncap_first}.delete(id);
        return JsonBean.success();
        }

        @Operation(summary = "更新${table.comment!}")
        @PutMapping()
        public JsonBean
        <String> update(@RequestBody ${entity}VO ${entity?uncap_first}VO){
            ${(table.serviceName?substring(1))?uncap_first}.updateData(${entity?uncap_first}VO);
            return JsonBean.success();
            }

            @Operation(summary = "查询${table.comment!}分页数据")
            @GetMapping()
            public JsonBean
            <TableDataUtils
            <List
            <${entity}VO>>> findListByPage(@RequestBody ${entity}VO ${entity?uncap_first}VO){
                return JsonBean.success(${(table.serviceName?substring(1))?uncap_first}
                .findListByPage(${entity?uncap_first}VO));
                }

                @Operation(summary = "id查询${table.comment!}")
                @GetMapping("{id}")
                public JsonBean
                <${entity}VO> findById(@PathVariable Long id){
                    return JsonBean.success(${(table.serviceName?substring(1))?uncap_first}.findById(id));
                    }
                    }
</#if>
