package com.hnly.provincial.service.log.impl;

import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.comm.utils.Conversion;
import com.hnly.provincial.entity.log.UserOperateLog;
import com.hnly.provincial.entity.log.UserOperateLogVO;
import com.hnly.provincial.dao.log.UserOperateLogMapper;
import com.hnly.provincial.service.log.IUserOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
* <p>
* 用户操作记录 服务实现类
* </p>
*
* @author czy
* @since 2021-09-22
*/
@Service
public class UserOperateLogServiceImpl extends ServiceImpl<UserOperateLogMapper, UserOperateLog> implements IUserOperateLogService {

    @Override
    public TableDataUtils<List<UserOperateLogVO>> findListByPage(UserOperateLogVO userOperateLogVO){
        Page<UserOperateLog> page = lambdaQuery().page(userOperateLogVO.page());
        List<UserOperateLogVO> userOperateLogVOs = Conversion.changeList(page.getRecords(), UserOperateLogVO.class);
        return TableDataUtils.success(page.getTotal(), userOperateLogVOs);
    }

    @Override
    public boolean add(UserOperateLogVO userOperateLogVO){
        UserOperateLog userOperateLog = Conversion.changeOne(userOperateLogVO, UserOperateLog.class);
        baseMapper.insert(userOperateLog);
        return true;
    }

    @Override
    public boolean delete(Long id){
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean updateData(UserOperateLogVO userOperateLogVO){
        UserOperateLog userOperateLog = Conversion.changeOne(userOperateLogVO, UserOperateLog.class);
        baseMapper.updateById(userOperateLog);
        return true;
    }

    @Override
    public UserOperateLogVO findById(Long id){
        UserOperateLog userOperateLog = baseMapper.selectById(id);
        return Conversion.changeOne(userOperateLog, UserOperateLogVO.class);
    }
}
