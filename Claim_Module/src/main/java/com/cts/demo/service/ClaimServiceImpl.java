package com.cts.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Policy;
import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.feign.PolicyClient;
import com.cts.demo.model.Claim;
import com.cts.demo.repository.ClaimRepository;

import jakarta.transaction.Transactional;

@Service("claimService")
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepository repository;

	@Autowired
	PolicyClient policyClient;

	@Override
	public String fileClaim(Claim claim) {
		Claim c = repository.save(claim);
		if (c != null)
			return "New claim request has been filed successfully";
		else
			return "Something went wrong";
	}
	
	@Override
	public Claim reviewClaimByIdAndAmount(long claimId) throws ClaimNotFoundException {
		Optional<Claim> optional = repository.findById(claimId);

		if (!optional.isPresent()) {
			throw new ClaimNotFoundException("There is no claim in the given Claim ID...");
		} else {
			long policyId = optional.get().getPolicyId();
			Policy policy = policyClient.retrievePolicy(policyId);
			double maxAmt = policy.getPremiumAmount();
			double claimAmt = optional.get().getClaimAmount();
			if (claimAmt <= maxAmt) {
				repository.updateClaimStatus("APPROVED", claimId);
//				Claim claim = repository.findById(claimId).get();
//				return claim;
			} else {
				repository.updateClaimStatus("REJECTED", claimId);
//				Claim claim = repository.findById(claimId).get();
//				return claim;
			}
			Optional<Claim> claim = repository.findById(claimId);
			
			return claim.get();

		}

	}

	@Override
	public String claimStatus(long claimId) {
		Optional<Claim> optional = repository.findById(claimId);
		return optional.get().getClaimStatus();
	}

	@Override
	public Claim reviewClaimByIdAndName(long claimId) throws ClaimNotFoundException {
		Optional<Claim> optional = repository.findById(claimId);
		if (!optional.isPresent()) {
			throw new ClaimNotFoundException("There is no claim in the given Claim ID...");
		} else {
			long policyId = optional.get().getPolicyId();
			Policy policy = policyClient.retrievePolicy(policyId);
			long customerId = policy.getCustomerId();
			long claimCustomerId = optional.get().getCustomerId();
			if (customerId == claimCustomerId) {
				repository.updateClaimStatus("IN-REVIEW", claimId);
				return repository.findById(claimId).get();
			} else {
				repository.updateClaimStatus("REJECTED", claimId);
				return repository.findById(claimId).get();
			}
		}
	}

	@Override
	public Claim getClaimById(long claimId) throws ClaimNotFoundException {

		Claim claim = repository.findById(claimId).get();
		if (claim != null) {
			return claim;
		} else {
			throw new ClaimNotFoundException("Enter a Valid Claim Id...");
		}

	}

	@Override
	public List<Claim> getAllClaims() {
		return repository.findAll();
	}

	@Override
	public String deleteClaimById(long claimId) {
		repository.deleteById(claimId);
		return "Claim deleted Successfully";
	}

}