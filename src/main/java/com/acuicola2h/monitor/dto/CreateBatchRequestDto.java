package com.acuicola2h.monitor.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBatchRequestDto {

	private long fishTankId;
	
	private LocalDate startDate;
	
}
