package com.example.demo.dao;

import com.example.demo.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee")
    public List<Employee> getall();

    @Select("select * from employee where userId=#{userId}")
    public Employee selectbyId(Integer userId);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},userId=#{userId},department=#{department} where id=#{id}")
    public int updateEmp(Employee employee);



    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into employee(lastName,email,gender,userId,department) values(#{lastName},#{email},#{gender},#{userId},#department)")
    public int insertEmp(Employee employee);

    @Delete("delete from employee where userId=#{userId}")
    public int delete(Integer userId);

    @Select("select * from employee order by id desc limit 1")
    public Employee selectlast();

}
