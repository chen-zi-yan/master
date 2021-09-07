package com.hnly.provincial.service.userCard;

import com.hnly.provincial.entity.userCard.UserCardInformation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 农户卡信息表 服务类
 * </p>
 *
 * @author czy
 * @since 2021-09-07
 */
public interface IUserCardInformationService extends IService<UserCardInformation> {

    /**
     * 插入ic卡信息数据
     *
     * @param userCardInformation
     * @return boolean值 <br/>true 添入数据成功 <br/>false 添加数据失败
     */
    boolean add(UserCardInformation userCardInformation);

}
