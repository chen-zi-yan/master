package ${package.ServiceImpl};

import com.hnly.provincial.comm.utils.TableDataUtils;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

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
    public TableDataUtils<List<${entity}>> findListByPage(Integer page, Integer pageCount){
        IPage<${entity}> wherePage = new Page<>(page, pageCount);
        ${entity} where = new ${entity}();

        IPage<${entity}> iPage = baseMapper.selectPage(wherePage, Wrappers.query(where));

        return TableDataUtils.success(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public boolean add(${entity} ${entity?uncap_first}){
        baseMapper.insert(${entity?uncap_first});
        return true;
    }

    @Override
    public boolean delete(Long id){
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(${entity} ${entity?uncap_first}){
        baseMapper.updateById(${entity?uncap_first});
        return true;
    }

    @Override
    public ${entity} findById(Long id){
        return  baseMapper.selectById(id);
    }
}
