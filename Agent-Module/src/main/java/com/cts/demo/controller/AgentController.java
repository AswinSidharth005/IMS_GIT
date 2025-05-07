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

import com.cts.demo.exception.AgentNotFoundException;
import com.cts.demo.model.Agent;
import com.cts.demo.service.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	AgentService service;

	@PostMapping("/save")
	public String saveAgent(@RequestBody Agent Agent) {
		return service.saveAgent(Agent);
	}

	@PutMapping("/update")
	public Agent updateAgent(@RequestBody Agent Agent) {
		return service.updateAgent(Agent);
	}

	@DeleteMapping("/delete/{aid}")
	public String deleteAgent(@PathVariable("aid") long AgentId) {
		return service.deleteAgent(AgentId);
	}

	@GetMapping("/searchById/{aid}")
	public Agent searchAgentById(@PathVariable("aid") long AgentId) throws AgentNotFoundException {
		return service.searchAgentById(AgentId);
	}

	@GetMapping("/searchByName/{aname}")
	public Agent searchAgentByName(@PathVariable("aname") String AgentName) throws AgentNotFoundException {
		return service.searchAgentByName(AgentName);
	}

	@GetMapping("/searchAll")
	public List<Agent> searchAll() {
		return service.getAllAgent();
	}

	@PutMapping("/assignPoliciesToAgent/{pid}/{aid}/{ptype}")
	public Agent assignPoliciesToAgent(@PathVariable("pid") long policyId, @PathVariable("aid") long agentId,
			@PathVariable("ptype") String policyType) throws AgentNotFoundException {
		return service.assignPoliciesToAgent(policyId, agentId, policyType);
	}

}