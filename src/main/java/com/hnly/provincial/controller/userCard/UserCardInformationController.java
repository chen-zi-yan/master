package com.hnly.provincial.controller.userCard;


import com.hnly.provincial.entity.userCard.UserCardInformation;
import com.hnly.provincial.service.userCard.IUserCardInformationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 农户卡信息表 前端控制器
 * </p>
 *
 * @author czy
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/user-card-information")
@Api(tags = "ic卡号管理接口")
public class UserCardInformationController {

    @Resource
    private IUserCardInformationService iUserCardInformationService;


    @PostMapping()
    public boolean save(UserCardInformation userCardInformation){
        boolean flag = iUserCardInformationService.add(userCardInformation);
        if (flag){
            return true;
        }
        return false;
    }

}
