package com.hnly.provincial.service.userCard.impl;

import com.hnly.provincial.entity.userCard.UserCardInformation;
import com.hnly.provincial.dao.userCard.UserCardInformationMapper;
import com.hnly.provincial.service.userCard.IUserCardInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 农户卡信息表 服务实现类
 * </p>
 *
 * @author czy
 * @since 2021-09-07
 */
@Service
public class UserCardInformationServiceImpl extends ServiceImpl<UserCardInformationMapper, UserCardInformation> implements IUserCardInformationService {

    @Override
    public boolean add(UserCardInformation userCardInformation) {
        //条件:联系电话、身份证号、ic卡号需唯一
        Integer count = lambdaQuery().eq(UserCardInformation::getPhone, userCardInformation.getPhone())
                .eq(UserCardInformation::getIdCard, userCardInformation.getIdCard())
                .eq(UserCardInformation::getIcCode, userCardInformation.getIcCode()).count();
        if (count == 0){
            userCardInformation.setStatus("0");
            userCardInformation.setCreateTime(new Date());
            baseMapper.insert(userCardInformation);
            return true;
        }
        return false;
    }
}
