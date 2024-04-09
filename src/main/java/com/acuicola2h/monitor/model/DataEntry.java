package com.acuicola2h.monitor.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DataEntry {

	private int tankNumber; //Estanque
	private double oxygen; //Oxigeno Ideal >= 5
	private double temperature; //Temperatura 22C < Ideal < 34C
	private double pH; //PH 6 <= Ideal <= 8
	private double nitrite; //Nitrito Ideal <= 0.1
	private double nitrate; //Nitrato 1.5 <= Ideal <= 2.0 
	private double ammonium; //Amonio 
	private double ammonia; //Amoniaco Ideal <= 0.1
	private double alkalinide; // 50 <= Ideal <= 150
	private double salinity; //Salinidad
	private double turbinez; //Turbinez
	private int deaths; //Bajas
	private Date date; //Fecha
	
}
