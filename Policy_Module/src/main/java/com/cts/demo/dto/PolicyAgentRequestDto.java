
package com.cts.demo.dto;

import com.cts.demo.model.Policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyAgentRequestDto {
	Policy policy;
	Agent agent;
}
