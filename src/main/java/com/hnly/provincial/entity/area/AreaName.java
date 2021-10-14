package com.hnly.provincial.entity.area;

import lombok.Data;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-10-08
 */
@Data
public class AreaName {
    /**
     * 市名称
     */
    private String shiName;

    /**
     * 县名称
     */
    private String xianName;

    /**
     * 乡名称
     */
    private String xiangName;

    /**
     * 村名称
     */
    private String cunName;
}
