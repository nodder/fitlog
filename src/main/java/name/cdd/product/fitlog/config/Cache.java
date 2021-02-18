package name.cdd.product.fitlog.config;

import com.google.common.collect.Maps;
import name.cdd.product.fitlog.pojo.FitType;
import name.cdd.product.fitlog.service.FitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
//TODO在攔截中緩存
public class Cache {
    private static final Logger logger = LoggerFactory.getLogger(Cache.class);

    @Autowired
    private FitService fitServer;

    private Map<String, FitType> subtype2FitType = Maps.newHashMap();

    @PostConstruct
    public void init() {
        logger.info("Cache init");

        List<FitType> allTypes = fitServer.queryFitTypes();

        this.subtype2FitType.clear();
        for(FitType t : allTypes){
            this.subtype2FitType.put(t.getSubtype(), t);
        }
    }

    public int getSubtypeId(String subtype) {
        return subtype2FitType.get(subtype).getId();
    }

    public List<FitType> getAllFitTypes(){
        return new ArrayList<>(subtype2FitType.values());
    }

    public void refresh() {
        init();
    }
}
