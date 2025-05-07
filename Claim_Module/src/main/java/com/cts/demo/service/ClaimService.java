
package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.model.Claim;

public interface ClaimService {
	public abstract String fileClaim(Claim claim);

	public abstract Claim reviewClaimByIdAndAmount(long claimId) throws ClaimNotFoundException;

	public abstract String claimStatus(long claimId);

	public abstract Claim reviewClaimByIdAndName(long claimId) throws ClaimNotFoundException;

	public abstract Claim getClaimById(long claimId) throws ClaimNotFoundException;

	public abstract List<Claim> getAllClaims();

	public abstract String deleteClaimById(long claimId);
}
