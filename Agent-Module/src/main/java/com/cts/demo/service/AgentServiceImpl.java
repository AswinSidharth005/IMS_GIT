
package com.cts.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.exception.AgentNotFoundException;
import com.cts.demo.model.Agent;
import com.cts.demo.model.Policy;
import com.cts.demo.repository.AgentRepository;

@Service("agentService")
public class AgentServiceImpl implements AgentService {

	@Autowired
	AgentRepository repository;

	@Override
	public String saveAgent(Agent agent) {
		Agent agent1 = repository.save(agent);
		if (agent1 != null) {
			return "Agent Saved Successfully";
		} else {
			return "Problem while saving the project";
		}

	}

	@Override
	public Agent updateAgent(Agent agent) {
		return repository.save(agent);
	}

	@Override
	public String deleteAgent(long agentId) {
		repository.deleteById(agentId);
		return "Agent deleted successfully";
	}

	@Override
	public Agent searchAgentById(long agentId) throws AgentNotFoundException {
		Agent agent = repository.findById(agentId).get();
		if (agent != null) {
			return agent;
		} else {
			throw new AgentNotFoundException("Enter correct AgentId");
		}

	}

	@Override
	public Agent searchAgentByName(String agentName) throws AgentNotFoundException {
		Agent agent = repository.findByAgentName(agentName);
		if (agent != null) {
			return agent;
		} else {
			throw new AgentNotFoundException("Enter a valid AgentName");
		}

	}

	@Override
	public List<Agent> getAllAgent() {

		return repository.findAll();
	}

	@Override
	public Agent assignPoliciesToAgent(long policyId, long agentId, String policyType) throws AgentNotFoundException {
		Agent agent = repository.findById(agentId).get();
		if (agent == null) {
			throw new AgentNotFoundException("Try a valid agent Id...");
		}
		else {
			Policy policy = new Policy();
			List<Policy> list = new ArrayList<>();
			for(Policy pol : agent.getPolicies()) {
				list.add(pol);
			}
			policy.setPolicyId(policyId);
			policy.setAssignedPolicies(policyType);
			list.add(policy);
			agent.setPolicies(list);
			repository.save(agent);
			return agent;
		}
	}

}
