package com.zamuraev.repository;

import com.zamuraev.entity.inheritance_strategy.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeRepository {

    private final EntityManager entityManager;

    //insert an employee
    public void insert(Employee employee) {
        entityManager.persist(employee);
    }

    //retrieve all employees
    public List<Employee> retrieveAllEmployees(){
        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
    }




}
