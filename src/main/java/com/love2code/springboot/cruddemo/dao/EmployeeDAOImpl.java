package com.love2code.springboot.cruddemo.dao;

import com.love2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    // define field for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        //create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee", Employee.class);

        //execute query and get result list
        List<Employee> employeeList = theQuery.getResultList();
        //return the results

        return employeeList;
    }

    @Override
    public Employee findEmployeeById(int theId) {

        return entityManager.find(Employee.class, theId);
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Employee dbEmployee = entityManager.merge(employee);
        // with merge if the entity is null will insert new entry, else save/update the entity
        return dbEmployee;
    }

    @Override
    public void deleteEmployeeById(int theId) {

        Employee employee = entityManager.find(Employee.class, theId);
        entityManager.remove(employee);

    }
}
