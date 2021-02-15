package name.cdd.product.fitlog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FitController1 {
    @RequestMapping("/index")
    public String sayHello(){
        throw new RuntimeException("test only when sayHello");
    }

    @RequestMapping("/testEcharts")
    public String test(){
        throw new RuntimeException("test only when test");
//        return "test";
    }

    @RequestMapping("/tabDemo")
    public String tabDemo(){
        return "tab_demo";
    }
}
