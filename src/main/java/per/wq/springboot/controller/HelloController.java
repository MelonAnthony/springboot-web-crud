package per.wq.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Auther: Anthony
 * @Date: 2018/9/18 22:12
 * @Description:
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping({"/","/index.html"})
    public String index(){
        return "login";
    }
    @RequestMapping("/success")
    public String success(HashMap<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }
}
