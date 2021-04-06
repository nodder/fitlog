package name.cdd.product.fitlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("name.cdd.product.fitlog.dao")
public class FitLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitLogApplication.class, args);
    }
}
