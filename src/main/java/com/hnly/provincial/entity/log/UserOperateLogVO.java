package com.hnly.provincial.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hnly.provincial.comm.utils.PageWhere;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 用户操作记录
* </p>
*
* @author czy
* @since 2021-09-22
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(name="UserOperateLogVO", description="用户操作记录")
public class UserOperateLogVO extends PageWhere<UserOperateLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "操作内容")
    private String content;

    @Schema(description = "请求路径")
    private String url;

    @Schema(description = "请求方法")
    private String method;

    @Schema(description = "请求参数")
    private String param;

    @Schema(description = "请求时间")
    private String useTime;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "客户端ip")
    private String clientIp;

    @Schema(description = "请求方式")
    private String requestMethod;
}
