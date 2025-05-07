
package com.cts.demo.service;

import java.util.List;

import com.cts.demo.dto.Agent;
import com.cts.demo.dto.Customer;
import com.cts.demo.dto.PolicyAgentResponseDto;
import com.cts.demo.dto.PolicyCustomerResponseDto;
import com.cts.demo.exception.ResourceNotFoundException;
import com.cts.demo.model.Policy;

public interface PolicyService {
	public abstract String savePolicy(Policy policy) throws ResourceNotFoundException;

	public abstract Policy updatePolicy(Policy policy);

	public abstract Policy retrievePolicy(long policyId);

	public abstract String archivePolicy(long policyId);

	public abstract List<Policy> retrieveAll();

	public abstract Customer assignPolicyToCustomer(long policyId, long customerId, String policyType);

	public abstract Agent assignPolicyToAgent(long policyId, long agentId, String policyType);

}
