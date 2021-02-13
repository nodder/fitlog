package name.cdd.product.fitlog.service;

import name.cdd.product.fitlog.dao.FitDao;
import name.cdd.product.fitlog.pojo.FitDailyLog;
import name.cdd.product.fitlog.pojo.FitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitService {

    @Autowired
    FitDao dao;

    public FitDailyLog queryFitSummary() {
        return dao.queryFitSummary().get(0);
    }

    public List<FitDailyLog> queryScoresByType() {
        return dao.queryScoresByType();
    }

    public List<FitDailyLog> queryScoresBySubType() {
        return dao.queryScoresBySubType();
    }

    public List<FitDailyLog> queryOriginalDailyLogs() {
        return dao.queryOriginalDailyLogs();
    }

    public List<FitDailyLog> queryRecentlyLogs() {
        return dao.queryRecentlyLogs();
    }

    public List<FitDailyLog> queryStatsWeeklyLogs() {
        return dao.queryStatsWeeklyLogs();
    }

    public List<FitDailyLog> queryStatsMonthlyLogs() {
        return dao.queryStatsMonthlyLogs();
    }

    public List<FitDailyLog> queryStatsDailyLogs() {
        return dao.queryStatsDailyLogs();
    }

    public void updateDailyLogs(String fitDate, List<FitDailyLog> logs) {
        dao.deleteLogsByDate(fitDate);
        logs.forEach(log -> dao.insertDailyLog(log));
    }

    public List<FitType> queryFitTypes() {
        return dao.queryFitTypes();
    }
}
