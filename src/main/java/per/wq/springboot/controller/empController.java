package per.wq.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import per.wq.springboot.dao.EmployeeDao;
import per.wq.springboot.entities.Employee;

import java.util.Collection;

/**
 * @Auther: Anthony
 * @Date: 2018/9/28 01:27
 * @Description:
 */
@Controller
public class empController {
    @Autowired
    private EmployeeDao employeeDao;
    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String  list(Model model){


        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps",employees);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "emp/list";
    }
}
