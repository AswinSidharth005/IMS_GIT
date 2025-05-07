package com.cts.demo.dto;

import com.cts.demo.model.Claim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	Claim claim;
	PolicyDTO policy;
}