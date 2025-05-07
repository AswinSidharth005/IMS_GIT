
package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.demo.model.Policy;

//@Transactional
public interface PolicyRepository extends JpaRepository<Policy, Long> {
	
	
//	@Modifying
//	@Query("update Customer c set c.Policy.policyId = ?1 where c.customerId = ?2")
//	public abstract Customer assignPoliciesToCustomer(long policyId, long custoemrId);

}
