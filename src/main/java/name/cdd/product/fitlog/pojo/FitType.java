package name.cdd.product.fitlog.pojo;

import lombok.Data;

@Data
public class FitType {
    private int id;
    private String subtype;
    private String type;
    private int weight;

    private int groupsS1;
    private int groupsS2;
    private int groupsS3;

    private int timesS1;
    private int timesS2;
    private int timesS3;
}
