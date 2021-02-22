package name.cdd.product.fitlog.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Version {
    private String version;
    private Date versionDate;
    private String content;
}
