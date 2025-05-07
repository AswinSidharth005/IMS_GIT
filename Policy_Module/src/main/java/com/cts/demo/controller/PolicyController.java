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

import com.cts.demo.dto.Agent;
import com.cts.demo.dto.Customer;
import com.cts.demo.dto.PolicyAgentResponseDto;
import com.cts.demo.dto.PolicyCustomerResponseDto;
import com.cts.demo.exception.ResourceNotFoundException;
import com.cts.demo.model.Policy;
import com.cts.demo.repository.PolicyRepository;
import com.cts.demo.service.PolicyService;

@RestController
@RequestMapping("/policy")
public class PolicyController {

	@Autowired
	PolicyService service;

	@PostMapping("/save")
	public String savePolicy(@RequestBody Policy policy) throws ResourceNotFoundException {
		return service.savePolicy(policy);
	}

	@PutMapping("/update")
	public Policy updatePolicy(@RequestBody Policy policy) {
		return service.updatePolicy(policy);
	}

	@GetMapping("/retrieveById/{pid}")
	public Policy retrievePolicy(@PathVariable("pid") long policyId) {
		return service.retrievePolicy(policyId);
	}

	@DeleteMapping("/deletePolicy/{pid}")
	public String archivePolicy(@PathVariable("pid") long policyId) {
		return service.archivePolicy(policyId);
	}

	@GetMapping("/retrieveAll")
	public List<Policy> retrieveAll() {
		return service.retrieveAll();
	}


	@PutMapping("/assignPoliciesToCustomer/{pid}/{cid}/{pType}")
	public Customer assignPoliciesToCustomer(@PathVariable("pid") long policyId, @PathVariable("cid") long customerId,
			@PathVariable("pType") String policyType) {
		return service.assignPolicyToCustomer(policyId, customerId, policyType);
	}

	@PutMapping("/assignPoliciesToAgent/{pid}/{aid}/{pType}")
	public Agent assignPoliciesToAgent(@PathVariable("pid") long policyId, @PathVariable("aid") long agentId,
			@PathVariable("pType") String policyType) {
		return service.assignPolicyToAgent(policyId, agentId, policyType);
	}

}