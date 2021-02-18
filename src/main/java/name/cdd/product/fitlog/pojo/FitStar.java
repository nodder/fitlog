package name.cdd.product.fitlog.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class FitStar {
    private Date fitDate;
    private String subtype;
    private int stars;

    private int subtypeId;
    private String type;
}
