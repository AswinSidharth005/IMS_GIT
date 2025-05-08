package com.cts.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.CustomerDTO;
import com.cts.demo.dto.PolicyDTO;
import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.feign.CustomerClient;
import com.cts.demo.feign.PolicyClient;
import com.cts.demo.model.Claim;
import com.cts.demo.repository.ClaimRepository;

import feign.FeignException;

@Service("claimService")
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	PolicyClient policyClient;
	
	@Autowired
	CustomerClient customerClient;

	 @Override
	   public String fileClaim(Claim claim) {
	       Long customerId = claim.getCustomerId();
	       Long policyId   = claim.getPolicyId();
	       // --- Validate Customer exists ---
	       try {
	           CustomerDTO customer = customerClient.getCustomerById(customerId);
	       } catch (FeignException.NotFound ex) {
	           throw new RuntimeException("Customer with ID " + customerId + " not found.");
	       } catch (FeignException ex) {
	           throw new RuntimeException(
	               "Error fetching customer with ID " + customerId + ": " + ex.getMessage());
	       }
	       // --- Validate Policy exists ---
	       PolicyDTO policy;
	       try {
	           policy = policyClient.retrievePolicy(policyId);
	       } catch (FeignException.NotFound ex) {
	           throw new RuntimeException("Policy with ID " + policyId + " not found.");
	       } catch (FeignException ex) {
	           throw new RuntimeException(
	               "Error fetching policy with ID " + policyId + ": " + ex.getMessage());
	       }
	       // --- Ensure the policy belongs to the customer ---
	       if (!customerId.equals(policy.getCustomerId())) {
	           throw new RuntimeException(
	               "Policy " + policyId + " does not belong to Customer " + customerId);
	       }
	       // --- Save the claim ---
	       Claim saved = claimRepository.save(claim);
	       return "Claim filed successfully. Claim ID: " + saved.getClaimId();
	   }
	

	@Override
	public Claim reviewClaimByIdAndAmount(long claimId) throws ClaimNotFoundException {
		Optional<Claim> optional = claimRepository.findById(claimId);

		if (!optional.isPresent()) {
			throw new ClaimNotFoundException("There is no claim in the given Claim ID...");
		} else {
			long policyId = optional.get().getPolicyId();
			PolicyDTO policy = policyClient.retrievePolicy(policyId);
			double maxAmt = policy.getPremiumAmount();
			double claimAmt = optional.get().getClaimAmount();
			if (claimAmt <= maxAmt) {
				claimRepository.updateClaimStatus("APPROVED", claimId);
//				Claim claim = repository.findById(claimId).get();
//				return claim;
			} else {
				claimRepository.updateClaimStatus("REJECTED", claimId);
//				Claim claim = repository.findById(claimId).get();
//				return claim;
			}
			Optional<Claim> claim = claimRepository.findById(claimId);

			return claim.get();

		}

	}

	@Override
	public String claimStatus(long claimId) {
		Optional<Claim> optional = claimRepository.findById(claimId);
		return optional.get().getClaimStatus();
	}

	@Override
	public Claim reviewClaimByIdAndName(long claimId) throws ClaimNotFoundException {
		Optional<Claim> optional = claimRepository.findById(claimId);
		if (!optional.isPresent()) {
			throw new ClaimNotFoundException("There is no claim in the given Claim ID...");
		} else {
			long policyId = optional.get().getPolicyId();
			PolicyDTO policy = policyClient.retrievePolicy(policyId);
			long customerId = policy.getCustomerId();
			long claimCustomerId = optional.get().getCustomerId();
			if (customerId == claimCustomerId) {
				claimRepository.updateClaimStatus("IN-REVIEW", claimId);
				return claimRepository.findById(claimId).get();
			} else {
				claimRepository.updateClaimStatus("REJECTED", claimId);
				return claimRepository.findById(claimId).get();
			}
		}
	}

	@Override
	public Claim getClaimById(long claimId) throws ClaimNotFoundException {

		Claim claim = claimRepository.findById(claimId).get();
		if (claim != null) {
			return claim;
		} else {
			throw new ClaimNotFoundException("Enter a Valid Claim Id...");
		}

	}

	@Override
	public List<Claim> getAllClaims() {
		return claimRepository.findAll();
	}

	@Override
	public String deleteClaimById(long claimId) {
		claimRepository.deleteById(claimId);
		return "Claim deleted Successfully";
	}


	@Override
	public Claim updateClaim(long claimId, Claim claimDetails) throws ClaimNotFoundException {
	   Optional<Claim> optionalClaim = claimRepository.findById(claimId);
	   if (optionalClaim.isPresent()) {
	       Claim claimToUpdate = optionalClaim.get();
	       // update fields
	       claimToUpdate.setCustomerId(claimDetails.getCustomerId());
	       claimToUpdate.setPolicyId(claimDetails.getPolicyId());
	       claimToUpdate.setClaimAmount(claimDetails.getClaimAmount());
	       claimToUpdate.setClaimStatus(claimDetails.getClaimStatus());
	       // save and return
	       return claimRepository.save(claimToUpdate);
	   } else {
	       throw new ClaimNotFoundException("Enter a Valid Claim Id...");
	   }
	}
	}

