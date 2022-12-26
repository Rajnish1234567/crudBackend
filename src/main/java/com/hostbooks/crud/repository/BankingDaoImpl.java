package com.hostbooks.crud.repository;

import com.hostbooks.crud.models.BankingDetails;
import com.hostbooks.crud.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BankingDaoImpl implements BankingDao{
    @Autowired
    private EntityManager entityManager;
    @Override
    public BankingDetails updateBankDetails(BankingDetails bankingDetails) {
        return entityManager.merge(bankingDetails);
    }

    @Override
    public BankingDetails findByBankId(Integer bankId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankingDetails> criteriaQuery=criteriaBuilder.createQuery(BankingDetails.class);
        Root<BankingDetails> root=criteriaQuery.from(BankingDetails.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("bankId"), bankId));
        TypedQuery<BankingDetails> query=entityManager.createQuery(criteriaQuery);
        return ((BankingDetails) query.getSingleResult());
    }
}
