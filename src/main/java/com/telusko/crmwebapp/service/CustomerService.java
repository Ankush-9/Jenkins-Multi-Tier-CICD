package com.telusko.crmwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.crmwebapp.entity.Customers;
import com.telusko.crmwebapp.repo.ICustomerRepo;

@Service
public class CustomerService implements ICustomerService 
{

	private ICustomerRepo repo;
	
	@Autowired	
	public void setRepo(ICustomerRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<Customers> getCustomerInfo()
	{
		
		return (List<Customers>) repo.findAll();
	}

	@Override
	public void registerCustomer(Customers customer) 
	{
		repo.save(customer);

	}

	@Override
	public void deleteRecord(Integer id) 
	{
		repo.deleteById(id);
	}

}
