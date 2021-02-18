package name.cdd.product.fitlog.service;

import com.google.common.collect.Maps;
import name.cdd.product.fitlog.pojo.FitStar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AchieveProgress {

    public Map<String, Integer> progress(List<FitStar> achievements) {
        Map<String, Integer> topProgressOfType = Maps.newHashMap();

        achievements.forEach(ach -> calcAchievement(ach, topProgressOfType));
        return topProgressOfType;
    }

    private void calcAchievement(FitStar achievement, Map<String, Integer> topProgressOfType) {
        //只有囚徒健身才有進度
        if(achievement.getSubtypeId() > 60) {
            return;
        }

        int topProgress = calcTopProgress(achievement, topProgressOfType);
        topProgressOfType.put(achievement.getType(), topProgress);
    }

    private int calcTopProgress(FitStar achievement, Map<String, Integer> topProgressOfType) {
        int progress = parseProgress(achievement.getSubtypeId(), achievement.getStars());
        int topProgress = Math.max(progress,
                topProgressOfType.containsKey(achievement.getType()) ? topProgressOfType.get(achievement.getType()): 0);
        return topProgress;
    }

    private int parseProgress(int subtypeId, int stars) {
        return (subtypeId - 1) % 10 * 10 + Math.round((10 * stars) / 3f);
    }
}
