package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cts.demo.model.Claim;

import jakarta.transaction.Transactional;

@Transactional
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	@Query("UPDATE Claim c SET c.claimStatus= ?1 WHERE c.claimId =?2 ")
	@Modifying
	public abstract int updateClaimStatus(String status, long claimId);

}