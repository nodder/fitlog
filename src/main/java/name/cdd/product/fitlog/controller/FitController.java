package name.cdd.product.fitlog.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import name.cdd.product.fitlog.config.Cache;
import name.cdd.product.fitlog.pojo.FitDailyLog;
import name.cdd.product.fitlog.pojo.FitType;
import name.cdd.product.fitlog.service.FitService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

@RestController
public class FitController {
    @Autowired
    private FitService fitServer;

    @Autowired
    private Cache cache;

    @Value("${fitlog.version}")
    String version;

    @GetMapping("/index")
    public String sayHello(){
        return "index";
    }

    @PostMapping("/get/all")
    public Map<String, Object> queryAllInfo() {
        FitDailyLog summary = fitServer.queryFitSummary();
        List<FitDailyLog> typeAndScore = fitServer.queryScoresByType();
        List<FitDailyLog> statsBySubtype = fitServer.queryFitSummaryBySubtype();

        Map<String, Object> map = Maps.newHashMap();
        map.put("allSummary", summary);
        map.put("typeAndScore", typeAndScore);
        map.put("statsBySubtype", statsBySubtype);

        return map;
    }

    @PostMapping("/get/daily")
    public Map<String, Object> queryDailyInfo() {
        Map<String, Object> result = Maps.newHashMap();

        List<FitDailyLog> stats = fitServer.queryStatsDailyLogs();

        List<FitDailyLog> ori = fitServer.queryOriginalDailyLogs();
        Map<String, List<FitDailyLog>> day_to_ori =  Maps.newHashMap();
        for (FitDailyLog log : ori) {
            if(!day_to_ori.containsKey(log.getFitDate().toString())) {
                day_to_ori.put(log.getFitDate().toString(), Lists.newArrayList());
            }
            day_to_ori.get(log.getFitDate().toString()).add(log);
        }

        List<FitDailyLog> statsBySubtype = fitServer.queryStatsDailyLogsBySubtype();
        Map<String, List<FitDailyLog>> day_to_statsBySubtype = Maps.newHashMap();
        for (FitDailyLog log : statsBySubtype) {
            if(!day_to_statsBySubtype.containsKey(log.getFitDate().toString())) {
                day_to_statsBySubtype.put(log.getFitDate().toString(), Lists.newArrayList());
            }
            day_to_statsBySubtype.get(log.getFitDate().toString()).add(log);
        }

        result.put("stats", stats);
        result.put("day_to_ori", day_to_ori);
        result.put("day_to_statsBySubtype", day_to_statsBySubtype);

        return result;
    }

    @PostMapping("/get/weekly")
    public Map<String, Object> queryWeekInfo() {
        Map<String, Object> result = Maps.newHashMap();

        List<FitDailyLog> stats = fitServer.queryStatsWeeklyLogs();

        List<FitDailyLog> ori = fitServer.queryOriginalDailyLogs();
        Map<String, List<FitDailyLog>> weekStart_to_ori =  Maps.newHashMap();
        for (FitDailyLog log : ori) {
            if(!weekStart_to_ori.containsKey(log.getWeekStart())) {
                weekStart_to_ori.put(log.getWeekStart(), Lists.newArrayList());
            }
            weekStart_to_ori.get(log.getWeekStart()).add(log);
        }

        List<FitDailyLog> statsBySubtype = fitServer.queryStatsWeeklyLogsBySubtype();
        Map<String, List<FitDailyLog>> weekStart_to_statsBySubtype =  Maps.newHashMap();
        for (FitDailyLog log : statsBySubtype) {
            if(!weekStart_to_statsBySubtype.containsKey(log.getWeekStart())) {
                weekStart_to_statsBySubtype.put(log.getWeekStart(), Lists.newArrayList());
            }
            weekStart_to_statsBySubtype.get(log.getWeekStart()).add(log);
        }

        result.put("stats", stats);
        result.put("weekStart_to_ori", weekStart_to_ori);
        result.put("weekStart_to_statsBySubtype", weekStart_to_statsBySubtype);

        return result;
    }

    @PostMapping("/get/monthly")
    public Map<String, Object> queryMonthInfo() {
        Map<String, Object> result = Maps.newHashMap();

        List<FitDailyLog> stats = fitServer.queryStatsMonthlyLogs();

        List<FitDailyLog> ori = fitServer.queryOriginalDailyLogs();
        Map<String, List<FitDailyLog>> yearMonth_to_ori = Maps.newHashMap();
        for (FitDailyLog log : ori) {
            if(!yearMonth_to_ori.containsKey(log.getYearMonth())) {
                yearMonth_to_ori.put(log.getYearMonth(), Lists.newArrayList());
            }
            yearMonth_to_ori.get(log.getYearMonth()).add(log);
        }

        List<FitDailyLog> statsBySubtype = fitServer.queryStatsMonthlyLogsBySutype();
        Map<String, List<FitDailyLog>> yearMonth_to_statsBySubtype = Maps.newHashMap();
        for (FitDailyLog log : statsBySubtype) {
            if(!yearMonth_to_statsBySubtype.containsKey(log.getYearMonth())) {
                yearMonth_to_statsBySubtype.put(log.getYearMonth(), Lists.newArrayList());
            }
            yearMonth_to_statsBySubtype.get(log.getYearMonth()).add(log);
        }

        result.put("stats", stats);
        result.put("yearMonth_to_ori", yearMonth_to_ori);
        result.put("yearMonth_to_statsBySubtype", yearMonth_to_statsBySubtype);

        return result;
    }

    @PostMapping("/get/recently")
    public Map<String, Object> queryRecentInfo() {
        Map<String, Object> result = Maps.newHashMap();

        List<FitDailyLog> ori = fitServer.queryRecentlyLogs();
        result.put("ori", ori);

        return result;
    }

    @PostMapping("/delete/single")
    public List<FitDailyLog> deleteSingleInfo(FitDailyLog log) {
        fitServer.deleteById(log.getId());
        return fitServer.queryLogsByDate(log.getFitDate().toString());
    }

    @PostMapping("/delete/daily")
    public void deleteDailyInfo(@Param("fitDate") String fitDate) {
        fitServer.deleteByDate(fitDate);
    }

    @PostMapping("/insert/single")
    public List<FitDailyLog> insertSingleInfo(FitDailyLog log) {
        fillSubtypeId(log);
        fitServer.insert(log);
        return fitServer.queryLogsByDate(log.getFitDate().toString());
    }

//    @PostMapping("/insert/batch")
//    public List<FitDailyLog> insertSingleInfo(@Param("fitDate") String fitDate, @Param("fitDailyLogs") List<FitDailyLog> logs) {
//        logs.forEach(log -> insertSingleInfo(log));
//        return fitServer.queryLogsByDate(fitDate);
//    }

    @PostMapping("/update/single")
    public List<FitDailyLog> updateSingleInfo(FitDailyLog log) {
        fillSubtypeId(log);
        fitServer.updateById(log);

        return fitServer.queryLogsByDate(log.getFitDate().toString());
    }

//    @PostMapping("/update/daily")
//    public List<FitDailyLog> updateDailyInfo(@Param("fitDate") String fitDate, @Param("fitDailyLogs[]") List<FitDailyLog> logs) {
//    public List<FitDailyLog> updateDailyInfo(String json) {
//        System.out.println("ok");
//        return Lists.newArrayList();
//        logs.forEach(log -> fillSubtypeId(log));
//        fitServer.updateDailyLogs(fitDate, logs);
//        return fitServer.queryLogsByDate(fitDate);
//    }

    @PostMapping("/get/base")
    public Map<String, Object> queryBase() {
        Map<String, Object> result = Maps.newHashMap();
       /////////////////////
        List<FitType> allTypes = fitServer.queryFitTypes();
        Set<String> types = Sets.newHashSet();
        Map<String, List<String>> type_to_subTypes =  Maps.newHashMap();
        for(FitType t : allTypes){
            if(!type_to_subTypes.containsKey(t.getType())) {
                type_to_subTypes.put(t.getType(), Lists.newArrayList());
            }
            type_to_subTypes.get(t.getType()).add(t.getSubtype());
            types.add(t.getType());
        }

        result.put("types", types);
        result.put("type_to_subtypes", type_to_subTypes);
        result.put("version", version);
        return result;
    }

    private void fillSubtypeId(FitDailyLog log) {
        log.setSubtypeId(cache.getSubtypeId(log.getSubType()));
    }
}
