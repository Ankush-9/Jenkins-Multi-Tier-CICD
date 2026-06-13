package com.telusko.crmwebapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.telusko.crmwebapp.entity.Customers;
import com.telusko.crmwebapp.service.ICustomerService;

@Controller
public class CustomerController
{
	
	private ICustomerService service;

	@Autowired
	public void setService(ICustomerService service) {
		this.service = service;
	}
	
	@GetMapping("/cxlist")
	public String getAllCxInfo(Model model)
	{
		List<Customers> cx = service.getCustomerInfo();
		model.addAttribute("customers", cx);
		cx.forEach(c->System.out.println(c));
		return "cxlist";//lvn
	}
	
	@GetMapping("/showform")
	public String showForm(@ModelAttribute("customer")Customers customer)
	{
		System.out.println("redirection --> showform");
		return "showform";
	}
	
	@GetMapping("/updateform")
	public String updateForm(@ModelAttribute("customer")Customers customer)
	{
		return "showform";
	}
	
	@PostMapping("/registerCustomer")
	public String registerCustomer(@ModelAttribute("customer")Customers customer)
	{
		service.registerCustomer(customer);
		return "redirect:/cxlist";
	}
	
	@GetMapping("/deleteData")
	public String delete(@RequestParam Integer id)
	{
		service.deleteRecord(id);
		return "redirect:/cxlist";
	}
	
	

}
