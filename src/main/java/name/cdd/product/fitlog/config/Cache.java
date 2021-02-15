package name.cdd.product.fitlog.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import name.cdd.product.fitlog.pojo.FitType;
import name.cdd.product.fitlog.service.FitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class Cache {
    private static final Logger logger = LoggerFactory.getLogger(Cache.class);

    @Autowired
    private FitService fitServer;

    private Map<String, Integer> subtype_to_id = Maps.newHashMap();

    @PostConstruct
    public void init() {
        logger.info("Cache init");

        List<FitType> allTypes = fitServer.queryFitTypes();

        for(FitType t : allTypes){
            this.subtype_to_id.put(t.getSubtype(), t.getId());
        }
    }

    public int getSubtypeId(String subtype) {
        return subtype_to_id.get(subtype);
    }
}
