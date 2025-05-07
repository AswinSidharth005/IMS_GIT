package com.cts.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
	@Id
	private long policyId;
	private String assignedPolicies;

//	@ManyToOne
//	@JoinColumn(name = "agent_id")
//	private Agent agent;

}