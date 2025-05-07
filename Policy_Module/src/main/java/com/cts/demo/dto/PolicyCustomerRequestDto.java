
package com.cts.demo.dto;

import com.cts.demo.model.Policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyCustomerRequestDto {
	private Customer customer;
	private Policy policy;
}
