package com.cts.demo.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.demo.dto.PolicyDTO;

@FeignClient(name = "POLICY", path = "/policy")
public interface PolicyClient {

	@GetMapping("/retrieveById/{pid}")
	public PolicyDTO retrievePolicy(@PathVariable("pid") long policyId);
}