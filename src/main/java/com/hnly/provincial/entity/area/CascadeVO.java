package com.hnly.provincial.entity.area;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 地区表
 * </p>
 *
 * @author czy
 * @since 2021-09-01
 */
@Data
@Schema(name = "行政区划表", description = "地区表")
public class CascadeVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String label;
    private String value;
    private List<CascadeVO> children;

    public List<CascadeVO> getChildren() {
        if (children.size() == 0) {
            return null;
        } else {
            return children;
        }
    }

}
