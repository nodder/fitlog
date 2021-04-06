package name.cdd.product.fitlog.pojo;

import lombok.Data;

@Data
public class FitType {
    private int id;
    private String subtype;
    private String type;
    private int weight;
    private int loadP;//表示每10千克负重增加loadP倍计分，或者跑步速度。默认为1。如果为0，则表示该项无负载/速度概念。
    private int loadBase;//用于调整loadP计分规则，例如跑步。


    private int groupsS1;
    private int groupsS2;
    private int groupsS3;

    private int timesS1;
    private int timesS2;
    private int timesS3;
}
