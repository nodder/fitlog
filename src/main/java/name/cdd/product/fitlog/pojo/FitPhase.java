package name.cdd.product.fitlog.pojo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class FitPhase {
    private int id;
    private Date startDate;
    private Date endDate;
    private String shortDesc;

    private Boolean isShown;

    private List<FitDailyLog> typeAndScores;
}
