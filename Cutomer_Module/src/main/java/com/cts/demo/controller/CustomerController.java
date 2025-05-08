package com.cts.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;
import com.cts.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping("/create")
	public String saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}

	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}

	@DeleteMapping("/delete/{custId}")
	public String deleteCustomer(@PathVariable("custId") long customerId) {
		return service.deleteCustomer(customerId);
	}

	@GetMapping("/searchById/{custId}")
	public Customer searchCustomerById(@PathVariable("custId") long customerId) throws CustomerNotFoundException {
		return service.searchCustomerById(customerId);
	}

	@GetMapping("/searchByName/{custName}")
	public Customer searchCustomerByName(@PathVariable("custName") String customerName) {
		return service.searchCustomerByName(customerName);
	}
	@GetMapping("/retrieveById/{cid}")
	public Customer getCustomerById(@PathVariable("cid") long cid) {
	   return service.getCustomerById(cid);
	}

	@PutMapping("/assignPoliciesToCustomer/{pid}/{cid}/{pType}")
	public Customer assignPoliciesToCustomer(@PathVariable("pid") long policyId, @PathVariable("cid") long customerId,
			@PathVariable("pType") String policyType) throws CustomerNotFoundException {
		return service.assignPoliciesToCustomer(policyId, customerId, policyType);
	}

}