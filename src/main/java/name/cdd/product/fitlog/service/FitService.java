package name.cdd.product.fitlog.service;

import com.google.common.collect.Lists;
import name.cdd.product.fitlog.config.Cache;
import name.cdd.product.fitlog.dao.FitDao;
import name.cdd.product.fitlog.pojo.FitDailyLog;
import name.cdd.product.fitlog.pojo.FitStar;
import name.cdd.product.fitlog.pojo.FitType;
import name.cdd.product.fitlog.pojo.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class FitService {

    @Autowired
    FitDao dao;

    @Autowired
    Cache cache;

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

    public List<FitStar> queryAchievements()  {
        List<FitStar> allAchievements = Lists.newArrayList();

        List<FitStar> star3s = dao.queryAllStar3();
        List<FitStar> star2s = dao.queryAllStar2();
        List<FitStar> star1s = dao.queryAllStar1();

        allAchievements.addAll(star3s);
        allAchievements.addAll(star2s);

        //排除掉直接跳過1星的情況
        for (int i = 0; i < star1s.size(); i++) {
            FitStar star1 = star1s.get(i);

            boolean isStar1Skipped = star2s.stream().filter(star2 -> star2.getSubtype().equals(star1.getSubtype()))
                    .filter(star2 -> star2.getFitDate().getTime() <= star1.getFitDate().getTime())
                    .findAny().isPresent();
            
            if(!isStar1Skipped) {
                allAchievements.add(star1);
            }
        }

        allAchievements.sort((star1, star2) -> compareByDate(star1, star2));
        return allAchievements;
    }

    private int compareByDate(FitStar star1, FitStar star2) {
        return (star1.getFitDate().getTime() - star2.getFitDate().getTime()) > 0 ? 1 : -1;
    }

    public List<FitDailyLog> queryStatsDailyLogsBySubtype()  {
        return dao.queryStatsDailyLogsBySubtype();
    };
    public List<FitDailyLog> queryStatsWeeklyLogsBySubtype()  {
        return dao.queryStatsWeeklyLogsBySubtype();
    };
    public List<FitDailyLog> queryStatsMonthlyLogsBySutype()  {
        return dao.queryStatsMonthlyLogsBySutype();
    };

    public List<Version> queryVersions() {
        return dao.queryVersions();
    }
}
