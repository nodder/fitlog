package name.cdd.product.fitlog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/fit2")
public class FitController2 {
    @GetMapping
    public Map<String, Object> get(){
        Map<String, Object> user = new HashMap<>();
        user.put("name", "demo");
        user.put("age", 25);
        return user;
    }
}