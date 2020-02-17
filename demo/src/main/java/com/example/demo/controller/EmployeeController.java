package com.example.demo.controller;

import com.example.demo.bean.Department;
import com.example.demo.bean.Employee;
import com.example.demo.dao.DepartmentMapper;
import com.example.demo.dao.EmployeeMapper;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    RedisTemplate<Object,Employee> empRedisTemplate;

    @GetMapping("/emp")
    public String selectallemp(Model model){
        Gson gson = new Gson();
        List<Employee> list = employeeMapper.getall();
        String str = gson.toJson(list);
        model.addAttribute("emps",list);
        stringRedisTemplate.opsForList().leftPushAll("list",str);
        return "employee";
    }

    @GetMapping("/emp/idselect")
    public String selectbyid(Model model,@RequestParam("id") Integer id){
        Employee employee = employeeMapper.selectbyId(id);
        model.addAttribute("emps",employee);
        return "employee";
    }

    @GetMapping("/emp/edit/{userID}")
    public String editByid(@PathVariable("userID") Integer userId, Model model){
//        List<Department> list = departmentMapper.getDepartment();
        Employee employee = employeeMapper.selectbyId(userId);
        model.addAttribute("emps",employee);
//        model.addAttribute("deps",list);
        return "edit";
    }
    @GetMapping("/emp/insert")
    public String addhtml(Model model){
        Employee employee = employeeMapper.selectlast();
        model.addAttribute("emps",employee);
        return "insert";
    }
//    @RequestParam("id") Integer id,@RequestParam("lastName") String lastName,
//    @RequestParam("email") String email, @RequestParam("gender") Integer gender,
//    @RequestParam("userId") Integer userId
//    @PostMapping("/emp/update")
//    public String update(Employee employee){
//        int i = employeeMapper.updateEmp(employee);
//        return "redirect:/emp";
//    }
    @PutMapping("/emp/update")
    public String update2(Employee employee){
        int i = employeeMapper.updateEmp(employee);
        return "redirect:/emp";
    }


    @PostMapping("/emp/insert")
    public String insert(Employee employee){
        int i = employeeMapper.insertEmp(employee);
        return "redirect:/emp";
    }

    @PutMapping("/emp/insert")
    public String insert1(Employee employee){
        int i = employeeMapper.insertEmp(employee);
        return "redirect:/emp";
    }
    @DeleteMapping("/emp/delete/{userId}")
    public String delete(@PathVariable("userId") Integer userId){
        int delete = employeeMapper.delete(userId);
        return "redirect:/emp";
    }
}
