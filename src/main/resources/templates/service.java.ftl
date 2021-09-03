package ${package.Service};

import com.hnly.provincial.comm.utils.TableDataUtils;
import ${package.Entity}.${entity};
import ${package.Entity}.${entity}VO;
import ${superServiceClassPackage};
import com.hnly.provincial.comm.JsonBean;
import java.util.List;

/**
* <p>
* ${table.comment!} 服务类
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 查询${table.comment!}分页数据
    *
    * @param ${entity?uncap_first}VO     条件
    * @return 分页结果
    */
    TableDataUtils<List<${entity}VO>> findListByPage(${entity}VO ${entity?uncap_first}VO);

    /**
    * 添加${table.comment!}
    * @param ${entity?uncap_first} ${table.comment!}
    * @return false 失败   true 成功
    */
    boolean add(${entity} ${entity?uncap_first});

    /**
    * 删除${table.comment!}
    *
    * @param id 主键
    * @return false 失败   true 成功
    */
    boolean delete(Long id);

    /**
    * 修改${table.comment!}
    *
    * @param ${entity?uncap_first} ${table.comment!}
    * @return false 失败   true 成功
    */
    boolean updateData(${entity} ${entity?uncap_first});

    /**
    * id查询数据
    *
    * @param id id
    * @return ${entity}VO
    */
    ${entity}VO findById(Long id);
}
</#if>
