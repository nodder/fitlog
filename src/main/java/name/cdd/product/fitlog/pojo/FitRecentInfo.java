package name.cdd.product.fitlog.pojo;

import lombok.Data;

@Data
public class FitRecentInfo {
    private String text;
    private String diff;
    private String startDate;

    public static FitRecentInfo of(String text, String diff, String startDate) {
        FitRecentInfo info = new FitRecentInfo();
        info.setText(text);
        info.setDiff(diff);
        info.setStartDate(startDate);
        return info;
    }
}
