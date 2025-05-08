package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;

public interface CustomerService {
	
	public abstract Customer getCustomerById(long customerId);
	
	public abstract String saveCustomer(Customer customer);

	public abstract Customer updateCustomer(Customer customer);

	public abstract String deleteCustomer(long customerId);

	public abstract Customer searchCustomerById(long customerId) throws CustomerNotFoundException;

	public abstract Customer searchCustomerByName(String customerName);

	public abstract List<Customer> getAllCustomer();

	public abstract Customer assignPoliciesToCustomer(long policyId, long customerId, String policyType)
			throws CustomerNotFoundException;
}