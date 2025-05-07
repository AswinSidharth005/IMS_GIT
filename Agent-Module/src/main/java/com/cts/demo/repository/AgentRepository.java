
package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cts.demo.model.Agent;

import jakarta.transaction.Transactional;

//@Transactional
public interface AgentRepository extends JpaRepository<Agent, Long> {
	public abstract Agent findByAgentName(String AgentName);

//	@Query("UPDATE Agent a SET a.policyId=?1 WHERE a.agentId=?2")
//	@Modifying
//	public abstract int assignPoliciesToAgent(long policyId, long agentId);
}
