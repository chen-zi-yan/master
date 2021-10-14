package com.hnly.provincial.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.log.UserOperateLog;
import com.hnly.provincial.entity.log.UserOperateLogVO;

import java.util.List;

/**
* <p>
* 用户操作记录 服务类
* </p>
*
* @author czy
* @since 2021-09-22
*/
public interface IUserOperateLogService extends IService<UserOperateLog> {

    /**
    * 查询用户操作记录分页数据
    *
    * @param userOperateLogVO     条件
    * @return 分页结果
    */
    TableDataUtils<List<UserOperateLogVO>> findListByPage(UserOperateLogVO userOperateLogVO);

    /**
    * 添加用户操作记录
    * @param userOperateLogVO
    * @return false 失败   true 成功
    */
    boolean add(UserOperateLogVO userOperateLogVO);

    /**
    * 删除用户操作记录
    *
    * @param id 主键
    * @return false 失败   true 成功
    */
    boolean delete(Long id);

    /**
    * 修改用户操作记录
    *
    * @param userOperateLogVO
    * @return false 失败   true 成功
    */
    boolean updateData(UserOperateLogVO userOperateLogVO);

    /**
    * id查询数据
    *
    * @param id id
    * @return UserOperateLogVO
    */
    UserOperateLogVO findById(Long id);
}
