
package com.cts.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;
import com.cts.demo.model.Policy1;
import com.cts.demo.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;

	@Override
	public String saveCustomer(Customer customer) {
		Customer cust = repository.save(customer);
		if (cust != null) {
			return "Customer saved succussfully";
		} else {
			return "Something went wrong";
		}

	}

	@Override
	public Customer updateCustomer(Customer customer) {

		return repository.save(customer);
	}

	@Override
	public String deleteCustomer(long customerId) {
		repository.deleteById(customerId);
		return "Customer deleted successfully";
	}

	@Override
	public Customer searchCustomerById(long customerId) throws CustomerNotFoundException {
		Optional<Customer> optional = repository.findById(customerId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomerNotFoundException("There is no customer in the given Id....");
		}

	}

	@Override
	public Customer searchCustomerByName(String customerName) {
		return repository.findByCustomerName(customerName);
	}

	@Override
	public List<Customer> getAllCustomer() {

		return repository.findAll();
	}

	@Override
	public Customer assignPoliciesToCustomer(long policyId, long customerId, String policyType)
			throws CustomerNotFoundException {
		Customer customer = repository.findById(customerId).get();
		if (customer == null) {
			throw new CustomerNotFoundException("Try a valid customer Id...");
		}

		else {
			Policy1 policy = new Policy1();
			List<Policy1> list = new ArrayList<>();
			for (Policy1 pol : customer.getPolicies()) {
				list.add(pol);
			}
			policy.setPolicyId(policyId);
			policy.setPolicyType(policyType);
			list.add(policy);
			customer.setPolicies(list);
			repository.save(customer);
			return customer;
		}

	}

}
