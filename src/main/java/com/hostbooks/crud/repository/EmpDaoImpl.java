package com.hostbooks.crud.repository;

import com.hostbooks.crud.models.Employee;
import com.hostbooks.crud.models.PaginationDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class EmpDaoImpl implements EmpDao{

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setPassword(getEncodedPassword(employee.getPassword()));
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employee.setPassword(getEncodedPassword(employee.getPassword()));
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
    public List<Employee> listEmployee(PaginationDTO paginationDTO) {
        paginationDTO.setFromValue(((paginationDTO.getFromValue()-1)* paginationDTO.getPsize()));
        CriteriaBuilder criteriaBuilder = entityManager .getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        List<Predicate> criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(paginationDTO.getSearch())) {
            criteriaList.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + paginationDTO.getSearch() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("emailId")), "%" + paginationDTO.getSearch() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("department")),"%"+paginationDTO.getSearch()+"%"),
                    criteriaBuilder.like(root.get("mobile"),"%"+paginationDTO.getSearch()+"%")));
        }
        if (!StringUtils.isEmpty(paginationDTO.getFilter()))
            criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(paginationDTO.getFilterField())), "%"+paginationDTO.getFilter()+"%"));

        criteriaList.add(criteriaBuilder.isFalse(root.get("deleteFlag")));
        criteriaQuery.where(criteriaList.toArray(new Predicate[0]));

        if (!StringUtils.isEmpty(paginationDTO.getSort())) {
            if (!StringUtils.isEmpty(paginationDTO.getOrder()) && paginationDTO.getOrder().equalsIgnoreCase("DESC"))
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(paginationDTO.getSort())));
            else
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(paginationDTO.getSort())));
        }
        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(paginationDTO.getFromValue());
        query.setMaxResults(paginationDTO.getPsize());
        List<Employee>list=query.getResultList();
        return list;
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
    public Employee findByEmail(String email) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery= criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root=criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("emailId"),email));
        TypedQuery<Employee> query=entityManager.createQuery(criteriaQuery);
        List<Employee> list = query.setMaxResults(1).getResultList();
        return list != null && list.size() > 0 ? list.get(0) : null;
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
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
