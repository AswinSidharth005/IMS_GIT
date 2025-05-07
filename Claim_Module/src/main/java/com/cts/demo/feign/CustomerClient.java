package com.cts.demo.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.demo.dto.CustomerDTO;
@FeignClient(name = "CUSTOMER", path = "/customer")
public interface CustomerClient {
   @GetMapping("/retrieveById/{cid}")
   public CustomerDTO getCustomerById(@PathVariable("cid") long customerId);
}