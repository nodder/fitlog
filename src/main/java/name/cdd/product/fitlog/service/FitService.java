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

    public List<FitDailyLog> queryOriginalDailyLogs() {
        return dao.queryDailyLogs(null,null);
    }

    public List<FitDailyLog> queryRecentlyLogs() {
        return dao.queryDailyLogs(null, 2000);
    }

    public List<FitDailyLog> queryLogsByDate(String fitDate) {
        return dao.queryDailyLogs(fitDate, null);
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
        dao.deleteByDate(fitDate);
        logs.forEach(log -> dao.insert(log));
    }

    public void deleteByDate(String fitDate) {
        dao.deleteByDate(fitDate);
    }

    public List<FitType> queryFitTypes() {
        return dao.queryFitTypes();
    }

    public void insert(FitDailyLog log) {
        dao.insert(log);
    }

    public void updateById(FitDailyLog log) {
        dao.updateById(log);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public List<FitDailyLog> queryFitSummaryBySubtype()  {
        return dao.queryFitSummaryBySubtype();
    };
    public List<FitDailyLog> queryStatsDailyLogsBySubtype()  {
        return dao.queryStatsDailyLogsBySubtype();
    };
    public List<FitDailyLog> queryStatsWeeklyLogsBySubtype()  {
        return dao.queryStatsWeeklyLogsBySubtype();
    };
    public List<FitDailyLog> queryStatsMonthlyLogsBySutype()  {
        return dao.queryStatsMonthlyLogsBySutype();
    };
}
