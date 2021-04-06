package name.cdd.product.fitlog.dao;

import name.cdd.product.fitlog.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FitDao {
    List<FitDailyLog> queryDailyLogs(@Param(value="fitDate") String fitDate, @Param(value="limitNum") Integer limitNum);

    //全部-总计 天数 组数 次数 分数
    List<FitDailyLog> queryFitSummary(@Param(value="sinceDate") String sinceDate);

    //全部-饼图
    List<FitDailyLog> queryScoresByType(@Param(value="sinceDate") String sinceDate, @Param(value="endDate") String endDate);

    //每天总组数、次数、分数、天数
    List<FitDailyLog> queryStatsDailyLogs();

    //每周总组数、次数、分数、周数、周起止日期
    List<FitDailyLog> queryStatsWeeklyLogs();

    //每月总组数、次数、分数、月数、月份
    List<FitDailyLog> queryStatsMonthlyLogs();

    void deleteByDate(String fitDate);

    void insert(FitDailyLog log);

    List<FitType> queryFitTypes();

    void updateById(FitDailyLog log);

    void deleteById(int id);

    List<FitDailyLog> queryFitSummaryBySubtype(@Param(value="sinceDate") String sinceDate);
    List<FitDailyLog> queryStatsDailyLogsBySubtype();
    List<FitDailyLog> queryStatsWeeklyLogsBySubtype();
    List<FitDailyLog> queryStatsMonthlyLogsBySutype();

    List<FitStar> queryAllStar1();
    List<FitStar> queryAllStar2();
    List<FitStar> queryAllStar3();

    List<Version> queryVersions();

    List<FitPhase> queryFitPhases();

    List<FitPhase> queryNotEndedFitPhases();

    List<FitDailyLog> queryFirstFitDate();

    List<FitDailyLog> queryLastByTypes(@Param(value="beforeDate")  String beforeDate, @Param(value="type")  String type);
}
