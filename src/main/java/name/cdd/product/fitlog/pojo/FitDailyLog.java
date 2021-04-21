package name.cdd.product.fitlog.pojo;


import lombok.Data;

import java.sql.Date;

@Data
public class FitDailyLog {
    private int id;
    private Date fitDate;

    private String type;
    private String subType;
    private int subtypeId;
    private int groups;
    private int times;
    private int weight;
    private float load;//负载/强度
    private float loadAvg;//平均负载
    private int scores;
    private int dates;//天数

    private String weekStart;
    private String weekEnd;
    private String week;//周数 如202001为2020年第一周
    private String yearMonth;
}
