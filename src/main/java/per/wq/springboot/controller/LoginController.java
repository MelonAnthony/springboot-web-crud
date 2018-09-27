package per.wq.springboot.controller;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Auther: Anthony
 * @Date: 2018/9/25 23:25
 * @Description:
 */
@Controller
public class LoginController {
//    @DeleteMapping
//    @GetMapping
//    @PutMapping
//    @RequestMapping(value ="/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession loginSession) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功，防止表单重复提交，可以重定向到主页
            loginSession.setAttribute("loginUser","username");
            return "redirect:/main.html";
        } else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

    @RequestMapping(value="/user/click")
    public String click(){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("click=================click");
        return "login";
    }
}
