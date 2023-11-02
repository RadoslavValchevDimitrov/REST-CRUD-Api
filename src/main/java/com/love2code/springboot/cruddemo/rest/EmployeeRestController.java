package com.love2code.springboot.cruddemo.rest;

import com.love2code.springboot.cruddemo.dao.service.EmployeeServiceInterface;
import com.love2code.springboot.cruddemo.entity.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeServiceInterface employeeService;

    private List<Employee> employeeList;

    @PostConstruct
    public void loadData(){

        employeeList = new ArrayList<>();
        employeeList = employeeService.findAll();
    }


    
@Autowired
    public EmployeeRestController(EmployeeServiceInterface employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getSingleEmployee(@PathVariable int employeeId){
   // List<Employee> employeeList = employeeService.findAll();
    //Employee theEmployee = employeeService.findEmployeeById(employeeId);
       if(employeeId>=employeeList.size()||employeeId<0 ){
           throw new EmployeeNotFoundException("Employee Id not found! " + employeeId);
       }
       return employeeList.get(employeeId);
    }

    // add mapping for POST /employees - add new employee

    @PostMapping("/employees")
     public Employee addEmployee(@RequestBody Employee employee){

    // just in case they pass an id in JSON... set id to 0
        //this is to force a save of new item ... instead of update

        employee.setId(0);
        Employee dbEmployee = employeeService.saveEmployee(employee);
        return dbEmployee;

        }

        //add mapping for PUT /employees - update an existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
    Employee dbEmployee = employeeService.saveEmployee(employee);
    return dbEmployee;
    }

    //add mapping for DELETE /employees/{employeeId} - delete an existing employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

    Employee tempEmployee = employeeService.findEmployeeById(employeeId);
    //throw exception if null
        if(tempEmployee == null){
            throw new RuntimeException("Employee not found " + employeeId);
        }
        employeeService.deleteEmployeeById(employeeId);
        return "Deleted employee id -  " + employeeId;
    }

    }
