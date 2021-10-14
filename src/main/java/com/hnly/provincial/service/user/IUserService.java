package com.hnly.provincial.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnly.provincial.comm.utils.TableDataUtils;
import com.hnly.provincial.entity.user.User;
import com.hnly.provincial.entity.user.UserVO;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author maqh
 * @since 2021-08-31
 */
public interface IUserService extends IService<User> {

    User login(String userName, String password);

    /**
     * 添加系统用户
     * <p>
     * 1.验证账号是否重复<br/>
     * 2.密码变更为MD5加密
     *
     * @param user 用户信息
     * @return true 已添加<br/>
     * false 添加失败
     */
    boolean add(UserVO user);

    /**
     * 修改用户信息,不能修改账号和密码
     *
     * @param userVO 用户信息
     * @return true 修改成功<br>
     * false 修改失败
     */
    boolean updateUser(UserVO userVO);

    /**
     * 启用禁用用户
     *
     * @param id   用户id
     * @param type 启用禁用状态
     * @return true 修改成功<br>
     * false 修改失败
     */
    boolean disableUser(Long id, String type);

    /**
     * 分页获取用户信息
     *
     * @param vo 查询条件
     * @return 分页信息
     */
    TableDataUtils<List<UserVO>> getPage(UserVO vo);
}
