package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.AgentNotFoundException;
import com.cts.demo.model.Agent;

public interface AgentService {

	public abstract String saveAgent(Agent agent);

	public abstract Agent updateAgent(Agent agent);

	public abstract String deleteAgent(long agentId);

	public abstract Agent searchAgentById(long agentId) throws AgentNotFoundException;

	public abstract Agent searchAgentByName(String agentName) throws AgentNotFoundException;

	public abstract List<Agent> getAllAgent();

	public abstract Agent assignPoliciesToAgent(long policyId, long agentId, String policyType)
			throws AgentNotFoundException;
}