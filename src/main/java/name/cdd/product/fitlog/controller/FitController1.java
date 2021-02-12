package name.cdd.product.fitlog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FitController1 {
//    @RequestMapping("/index")
//    public String sayHello(){
//        return "index";
//    }

    @RequestMapping("/testEcharts")
    public String test(){
        return "test";
    }

    @RequestMapping("/tabDemo")
    public String tabDemo(){
        return "tab_demo";
    }
}
