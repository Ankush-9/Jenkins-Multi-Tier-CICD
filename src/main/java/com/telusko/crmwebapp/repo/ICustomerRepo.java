package com.telusko.crmwebapp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.telusko.crmwebapp.entity.Customers;

@Repository
public interface ICustomerRepo extends CrudRepository<Customers, Integer> 
{

}
