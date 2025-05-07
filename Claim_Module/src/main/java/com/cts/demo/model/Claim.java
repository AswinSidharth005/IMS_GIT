
package com.cts.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "claim_info")
public class Claim {
	@Id
	@GeneratedValue
	private long claimId;
	private long customerId;
	private long policyId;
	private double claimAmount;
	private String claimStatus;

	public Claim(long customerId, long policyId, double claimAmount, String claimStatus) {
		super();
		this.customerId = customerId;
		this.policyId = policyId;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
	}

}
