package name.cdd.product.fitlog.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import name.cdd.product.fitlog.pojo.FitDailyLog;
import name.cdd.product.fitlog.pojo.FitType;
import name.cdd.product.fitlog.service.FitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class FitController {
    @Autowired
    private FitService fitServer;

    @GetMapping("/index")
    public String sayHello(){
        return "index";
    }

    @PostMapping("/get/all")
    public Map<String, Object> queryAllInfo() {
        FitDailyLog summary = fitServer.queryFitSummary();
        List<FitDailyLog> typeAndScore = fitServer.queryScoresByType();
        List<FitDailyLog> subTypeAndScore = fitServer.queryScoresByType();

        Map<String, Object> map = Maps.newHashMap();
        map.put("allSummary", summary);
        map.put("typeAndScore", typeAndScore);
        map.put("subTypeAndScore", subTypeAndScore);

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

        result.put("day_to_ori", day_to_ori);
        result.put("stats", stats);

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

        result.put("stats", stats);
        result.put("weekStart_to_ori", weekStart_to_ori);

        return result;
    }

    @PostMapping("/get/monthly")
    public Map<String, Object> queryMonthInfo() {
        Map<String, Object> result = Maps.newHashMap();

        List<FitDailyLog> stats = fitServer.queryStatsMonthlyLogs();

        List<FitDailyLog> ori = fitServer.queryOriginalDailyLogs();
        Map<String, List<FitDailyLog>> yearMonth_to_ori =  Maps.newHashMap();
        for (FitDailyLog log : ori) {
            if(!yearMonth_to_ori.containsKey(log.getYearMonth())) {
                yearMonth_to_ori.put(log.getYearMonth(), Lists.newArrayList());
            }
            yearMonth_to_ori.get(log.getYearMonth()).add(log);
        }

        result.put("stats", stats);
        result.put("yearMonth_to_ori", yearMonth_to_ori);

        return result;
    }

    @PostMapping("/get/recently")
    public Map<String, Object> queryRecentInfo() {
        Map<String, Object> result = Maps.newHashMap();

        List<FitDailyLog> ori = fitServer.queryRecentlyLogs();
        result.put("ori", ori);

        return result;
    }

    @PostMapping("/update/daily")
    public void updateDailyInfo(String fitDate, List<FitDailyLog> logs) {
        fitServer.updateDailyLogs(fitDate, logs);
    }

    @PostMapping("/get/types")
    public Map<String, Object> queryFitTypes() {
        Map<String, Object> result = Maps.newHashMap();

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

        return result;
    }
}
