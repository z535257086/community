package com.example.demo.dao;


import com.example.demo.bean.Department;
import com.example.demo.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper  {

    @Select("select departmentName from dapartment")
    public List<Department> getDepartment();
}
