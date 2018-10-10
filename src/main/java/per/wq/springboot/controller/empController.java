package per.wq.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import per.wq.springboot.dao.DepartmentDao;
import per.wq.springboot.dao.EmployeeDao;
import per.wq.springboot.entities.Department;
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
    @Autowired
    private DepartmentDao departmentDao;
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

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象一一绑定，要求请求参数的名字和javaBean
    //入参对象的属性名一一对应
    @PostMapping("/emp")
    public String add(Employee emp){
        //来到员工列表
        System.out.println(emp);
        //保存员工
        employeeDao.save(emp);
        //redirect :表示重定向到一个地址 /代表当前地址
        //forward：表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面进行回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id,Model model){
        Employee emp= employeeDao.get(id);
        model.addAttribute("emp",emp);
        //页面要显示部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面,修改和添加是一个二合一的页面
        return "emp/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
