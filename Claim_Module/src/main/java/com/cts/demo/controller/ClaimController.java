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

import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.feign.PolicyClient;
import com.cts.demo.model.Claim;
import com.cts.demo.service.ClaimService;

@RestController
@RequestMapping("/claim")
public class ClaimController {

	@Autowired
	ClaimService service;

	@PostMapping("/file")
	public String save(@RequestBody Claim claim) {
		return service.fileClaim(claim);
	}

	@PutMapping("/reviewClaimByName/{cid}")
	public Claim reviewClaim(@PathVariable("cid") long claimId) throws ClaimNotFoundException {
		return service.reviewClaimByIdAndName(claimId);
	}

	@PutMapping("/reviewClaimByAmount/{cid}")
	public Claim reviewClaimByAmount(@PathVariable("cid") long claimId) throws ClaimNotFoundException {
		return service.reviewClaimByIdAndAmount(claimId);
	}

	@GetMapping("/claimStatus/{cid}")
	public String claimStatus(@PathVariable("cid") long claimId) {
		return service.claimStatus(claimId);
	}

	@GetMapping("/retrieveClaimById/{cid}")
	public Claim getClaimById(@PathVariable("cid") long claimId) throws ClaimNotFoundException {
		return service.getClaimById(claimId);
	}

	@GetMapping("/retrieveAll")
	public List<Claim> getAllClaims() {
		return service.getAllClaims();
	}

	@DeleteMapping("/deleteById/{cid}")
	public String deleteClaimById(@PathVariable("cid") long claimId) {
		return service.deleteClaimById(claimId);
	}
	@PutMapping("/updateById/{cid}")
	public Claim updateClaimById(@PathVariable("cid") long claimId,@RequestBody Claim claimDetails ) throws ClaimNotFoundException {
		return service.updateClaim(claimId,claimDetails);
	}

}