package name.cdd.product.fitlog.dao;

import name.cdd.product.fitlog.pojo.FitDailyLog;
import name.cdd.product.fitlog.pojo.FitType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FitDao {
    //每天原始数据
    @Select("select d.fit_date as fitDate,\n" +
            "adddate(fit_date, 0- date_format(fit_date,'%w')) as weekStart,\n" +
            "adddate(fit_date, 6-date_format(fit_date,'%w')) as weekEnd,\n" +
            "DATE_FORMAT(fit_date, '%Y-%m') as yearMonth, \n" +
            " t.type, t.subtype, d.groups, d.times, \n" +
            " cast(d.groups * d.times * t.weight as DECIMAL(10)) as scores, \n" +
            " d.subtype_id as subtypeId, t.weight \n" +
            "        from fit_daily_log d\n" +
            "        left join fit_type t on d.subtype_id=t.id \n" +
            "        order by d.fit_date")
    List<FitDailyLog> queryOriginalDailyLogs();

    @Select("select d.fit_date as fitDate,\n" +
            "adddate(fit_date, 0- date_format(fit_date,'%w')) as weekStart,\n" +
            "adddate(fit_date, 6-date_format(fit_date,'%w')) as weekEnd,\n" +
            "DATE_FORMAT(fit_date, '%Y-%m') as yearMonth, \n" +
            " t.type, t.subtype, d.groups, d.times, \n" +
            " cast(d.groups * d.times * t.weight as DECIMAL(10)) as scores, \n" +
            " d.subtype_id as subtypeId, t.weight \n" +
            "        from fit_daily_log d\n" +
            "        left join fit_type t on d.subtype_id=t.id \n" +
            "        order by d.fit_date desc limit 50")
    List<FitDailyLog> queryRecentlyLogs();

    //全部-总计 天数 组数 次数 分数
    @Select("select count(distinct(d.fit_date)) as dates,sum(d.groups) as groups, sum(d.times) as times, sum(cast(d.groups * d.times * t.weight as DECIMAL(10))) as scores from fit_daily_log d left join fit_type t on d.subtype_id=t.id")
    List<FitDailyLog> queryFitSummary();

    //全部-饼图
    @Select("select t.type, sum(cast(d.groups * d.times * t.weight as DECIMAL(10))) as scores from fit_daily_log d left join fit_type t on d.subtype_id=t.id group by t.type")
    List<FitDailyLog> queryScoresByType();

    //全部-子种类的组数和次数
    @Select("select t.type, t.subtype, sum(cast(d.groups * d.times * t.weight as DECIMAL(10))) as scores from fit_daily_log d left join fit_type t on d.subtype_id=t.id group by t.type, t.subtype")
    List<FitDailyLog> queryScoresBySubType();

    //每天总组数、次数、分数、天数
    @Select("select d.fit_date as fitDate, \n" +
            "count(distinct(fit_date)) as dates,\n" +
            "sum(d.groups) as groups, \n" +
            "sum(d.times) as times, \n" +
            "sum(cast(d.groups * d.times * t.weight as DECIMAL(10))) as scores \n" +
            "        from fit_daily_log d\n" +
            "        left join fit_type t on d.subtype_id=t.id \n" +
            "        group by d.fit_date order by d.fit_date")
    List<FitDailyLog> queryStatsDailyLogs();

    //每周总组数、次数、分数、周数、周起止日期
    @Select("select DATE_FORMAT(adddate(fit_date,  1), '%Y%u') as week, \n" +
            "    adddate(fit_date, 0- date_format(fit_date,'%w')) as weekStart,\n" +
            "    adddate(fit_date, 6-date_format(fit_date,'%w')) as weekEnd,\n" +
            "    count(distinct(fit_date)) as dates,\n" +
            "    sum(d.groups) as groups, sum(d.times) as times, \n" +
            "    sum(cast(d.groups * d.times * t.weight as DECIMAL(10))) as scores \n" +
            "        from fit_daily_log d \n" +
            "        left join fit_type t on d.subtype_id=t.id \n" +
            "        group by week order by week, weekStart, weekEnd")
    List<FitDailyLog> queryStatsWeeklyLogs();

    //每月总组数、次数、分数、月数、月份
    @Select("select DATE_FORMAT(fit_date, '%Y-%m') as yearMonth, \n" +
            "   count(distinct(fit_date)) as dates,\n" +
            "    sum(d.groups) as groups, \n" +
            "    sum(d.times) as times, \n" +
            "    sum(cast(d.groups * d.times * t.weight as DECIMAL(10))) as scores \n" +
            "        from fit_daily_log d \n" +
            "        left join fit_type t on d.subtype_id=t.id \n" +
            "        group by yearMonth order by yearMonth")
    List<FitDailyLog> queryStatsMonthlyLogs();
}