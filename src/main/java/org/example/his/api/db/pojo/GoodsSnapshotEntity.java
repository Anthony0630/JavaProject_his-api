package org.example.his.api.db.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "goods_snapshot")
public class GoodsSnapshotEntity implements Serializable {
    @Id
    private String _id;

    @Indexed
    private Integer id;

    private String code;

    private String title;

    private String description;

    private List<Map> checkup_1;

    private List<Map> checkup_2;

    private List<Map> checkup_3;

    private List<Map> checkup_4;

    private String image;

    private BigDecimal initialPrice;

    private BigDecimal currentPrice;

    private String type;

    private List<String> tag;

    private String ruleName;

    private String rule;

    private List<Map> checkup;

    @Indexed
    private String md5;
}

