
package com.cts.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Agent;
import com.cts.demo.dto.Customer;
import com.cts.demo.dto.PolicyAgentResponseDto;
import com.cts.demo.dto.PolicyCustomerResponseDto;
import com.cts.demo.exception.ResourceNotFoundException;
import com.cts.demo.feign.AgentClient;
import com.cts.demo.feign.CustomerClient;
import com.cts.demo.model.Policy;
import com.cts.demo.repository.PolicyRepository;

import feign.FeignException;

@Service("policyService")
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository repository;

	@Autowired
	CustomerClient customerClient;

	@Autowired
	AgentClient agentClient;

	@Override
	public String savePolicy(Policy policy) throws ResourceNotFoundException{
		try {
			customerClient.getCustomer(policy.getCustomerId());
		}catch(FeignException.NotFound e) {
			throw new ResourceNotFoundException("Customer with ID" + policy.getCustomerId()+"not found");
		}
		try {
			agentClient.getAgent(policy.getAgentId());
		}catch(FeignException.NotFound e) {
			throw new ResourceNotFoundException("Agent with ID"+policy.getAgentId()+"not found");
		}
		Policy policy1 = repository.save(policy);
		if (policy1 != null) {
			return "Policy saved Successfully";
		} else {
			return "Policy not saved ....";
		}
	}

	@Override
	public Policy updatePolicy(Policy policy) {
		return repository.save(policy);
	}

	@Override
	public Policy retrievePolicy(long policyId) {
		Optional<Policy> optional = repository.findById(policyId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	@Override
	public String archivePolicy(long policyId) {
		repository.deleteById(policyId);
		return "Policy deleted Successfully";
	}

	@Override
	public List<Policy> retrieveAll() {
		return repository.findAll();
	}

	@Override
	public Customer assignPolicyToCustomer(long policyId, long customerId, String policyType) {

		return customerClient.assignPoliciesToCustomer(policyId, customerId, policyType);
	}

	@Override
	public Agent assignPolicyToAgent(long policyId, long agentId, String policyType) {

		return agentClient.assignPoliciesToAgent(policyId, agentId, policyType);
	}

}
