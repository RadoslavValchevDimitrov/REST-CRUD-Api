package com.love2code.springboot.cruddemo.dao.service;

import com.love2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeServiceInterface {

    List<Employee> findAll();

    Employee saveEmployee(Employee employee);
    Employee findEmployeeById(int theId);
    void deleteEmployeeById(int theId);
}
