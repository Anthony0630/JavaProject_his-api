package org.example.his.api.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName tb_flow_regulation
 */
@Data
public class FlowRegulationEntity implements Serializable {
    private Integer id;

    private String place;

    private Integer realNum;

    private Integer maxNum;

    private Integer weight;

    private Integer priority;

    private String blueUuid;

    private static final long serialVersionUID = 1L;
}