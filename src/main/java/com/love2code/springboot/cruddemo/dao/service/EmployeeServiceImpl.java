package com.love2code.springboot.cruddemo.dao.service;

import com.love2code.springboot.cruddemo.dao.EmployeeDAO;
import com.love2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface{

    private EmployeeDAO employeeDAO;

@Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
      return employeeDAO.saveEmployee(employee);
    }

    @Override
    public Employee findEmployeeById(int theId) {
        return employeeDAO.findEmployeeById(theId);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(int theId) {
     employeeDAO.deleteEmployeeById(theId);
    }
}
