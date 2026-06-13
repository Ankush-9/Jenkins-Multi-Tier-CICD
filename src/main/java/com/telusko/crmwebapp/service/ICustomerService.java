package com.telusko.crmwebapp.service;

import java.util.List;

import com.telusko.crmwebapp.entity.Customers;

public interface ICustomerService 
{
	public List<Customers> getCustomerInfo();
	public void registerCustomer(Customers customer);
	public void deleteRecord(Integer id);

}
