package com.acuicola2h.monitor.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DataEntry {

	private int tankNumber;
	private double oxygen; // Ideal >= 5
	private double temperature; // 22C < Ideal < 34C
	private double pH; // 6 <= Ideal <= 8
	private double nitrite; // Ideal <= 0.1
	private double nitrate; // 1.5 <= Ideal <= 2.0 
	private double ammonia; // Ideal <= 0.1
	private double alkalinide; // 50 <= Ideal <= 150
	private Date date;
	
}
