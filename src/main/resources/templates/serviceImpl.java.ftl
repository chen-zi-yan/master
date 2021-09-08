package ${package.ServiceImpl};

import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import ${package.Entity}.${entity};
import ${package.Entity}.${entity}VO;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
* <p>
* ${table.comment!} 服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

@Override
public TableDataUtils
<List
<${entity}VO>> findListByPage(${entity}VO ${entity?uncap_first}VO){
             Page<${entity}> page = lambdaQuery().page(areaVO.page());
             List
    <${entity}VO> ${entity?uncap_first}VOs = Conversion.changeList(page.getRecords(), ${entity}VO.class);
                                       return TableDataUtils.success(page.getTotal(), ${entity?uncap_first}VOs);
                                       }

                                       @Override
                                       public boolean add(${entity}VO ${entity?uncap_first}VO){
        ${entity} ${entity?uncap_first} = Conversion.changeOne(${entity?uncap_first}VO, ${entity}.class);
                                       baseMapper.insert(${entity?uncap_first});
                                       return true;
                                       }

                                       @Override
    public boolean delete(Long id){
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(${entity}VO ${entity?uncap_first}VO){
        ${entity} ${entity?uncap_first} = Conversion.changeOne(${entity?uncap_first}VO, ${entity}.class);
        baseMapper.updateById(${entity?uncap_first});
        return true;
    }

    @Override
    public ${entity}VO findById(Long id){
        ${entity} ${entity?uncap_first} = baseMapper.selectById(id);
        return Conversion.changeOne(${entity?uncap_first}, ${entity}VO.class);
    }
}
