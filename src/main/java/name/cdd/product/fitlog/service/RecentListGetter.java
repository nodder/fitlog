package name.cdd.product.fitlog.service;

import com.google.common.collect.Lists;
import name.cdd.product.fitlog.pojo.FitPhase;
import name.cdd.product.fitlog.pojo.FitRecentInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static name.cdd.product.fitlog.service.FitUtils.*;

@Service
public class RecentListGetter {

    public static List<FitRecentInfo> parse(List<FitPhase> notEndedPhases) {
        List<FitRecentInfo> recentsForNotEnded = notEndedPhases.stream().map(phase -> toFitRecentInfo(phase)).collect(Collectors.toList());

        List<FitRecentInfo> result = Lists.newArrayList();
        result.addAll(recentsForNotEnded);
        if(notEndedPhases.size() == 1) {
            final long daysAgo = daysAgo(notEndedPhases.get(0).getStartDate().toLocalDate());

            long months = Math.max(3, (daysAgo / 30 + 1) * 2);
            String diff = months + "m";
            result.add(FitRecentInfo.of(months + "个月前", diff, parseDate(diff)));
        }

        result.add(FitRecentInfo.of("全部", "", "全部"));
        return result;
    }

    private static FitRecentInfo toFitRecentInfo(FitPhase phase) {
        long dateDiff = daysAgo(phase.getStartDate().toLocalDate());
        return FitRecentInfo.of(phase.getShortDesc(), dateDiff + "d", phase.getStartDate().toString());
    }
}
