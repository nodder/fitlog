package name.cdd.product.fitlog.pojo;


import java.sql.Date;

public class FitDailyLog {
    private int id;
    private Date fitDate;

    private String type;
    private String subType;
    private int subtypeId;
    private int groups;
    private int times;
    private int weight;
    private int scores;
    private int dates;//天数

    private String weekStart;
    private String weekEnd;
    private String week;//周数 如202001为2020年第一周
    private String yearMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFitDate() {
        return fitDate;
    }

    public void setFitDate(Date fitDate) {
        this.fitDate = fitDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getSubtypeId() {
        return subtypeId;
    }

    public void setSubtypeId(int subtypeId) {
        this.subtypeId = subtypeId;
    }

    public int getGroups() {
        return groups;
    }

    public void setGroups(int groups) {
        this.groups = groups;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getDates() {
        return dates;
    }

    public void setDates(int dates) {
        this.dates = dates;
    }

    public String getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    @Override
    public String toString() {
        return "FitDailyLog{" +
                "id=" + id +
                ", fitDate=" + fitDate +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", subtypeId=" + subtypeId +
                ", groups=" + groups +
                ", times=" + times +
                ", weight=" + weight +
                ", scores=" + scores +
                ", dates=" + dates +
                ", weekStart='" + weekStart + '\'' +
                ", weekEnd='" + weekEnd + '\'' +
                ", week='" + week + '\'' +
                ", yearMonth='" + yearMonth + '\'' +
                '}';
    }
}
