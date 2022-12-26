package com.hostbooks.crud.repository;

import com.hostbooks.crud.models.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class EmpDaoImpl implements EmpDao{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Employee addEmployee(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return entityManager.merge(employee);

    }

    @Override
    public List<Employee> getEmployeeList() {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery=criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root=criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("deleteFlag"), false));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Employee findByEmpId(Integer empId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery=criteriaBuilder.createQuery(Employee.class);
        Root<Employee>root=criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("employeeId"), empId));
        TypedQuery<Employee> query=entityManager.createQuery(criteriaQuery);
        return ((Employee) query.getSingleResult());
    }

    @Override
    public Employee findByMobile(String mobile) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery= criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root=criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("mobile"),mobile));
        TypedQuery<Employee> query=entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public void deleteByEmpId(Integer empId) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaUpdate<Employee> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Employee.class);
        Root<Employee> root = criteriaUpdate.from(Employee.class);
        criteriaUpdate.where(root.get("employeeId").in(empId));
        criteriaUpdate.set("deleteFlag", true);
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
}
