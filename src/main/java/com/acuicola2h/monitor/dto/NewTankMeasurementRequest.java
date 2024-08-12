package com.acuicola2h.monitor.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewTankMeasurementRequest {
	private Long batchId;
	private Long fishTankId;
    private double oxygen;
    private double pH;
    private double temperature;
    private double salinity;
    private double nitrate;
    private double nitrite;
    private double ammonia;
    private double turbine;
    private double alkalinity;
    private int deaths;
    private LocalDateTime date;
}
