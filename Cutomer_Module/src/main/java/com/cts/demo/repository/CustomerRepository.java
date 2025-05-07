package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.demo.model.Customer;

//@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public abstract Customer findByCustomerName(String customerName);

//	@Modifying
//	@Query("UPDATE Customer c SET c.policyId = ?1 WHERE c.customerId = ?2")
//	public abstract int assignPoliciesToCustomer(long policyId, long customerId);

}