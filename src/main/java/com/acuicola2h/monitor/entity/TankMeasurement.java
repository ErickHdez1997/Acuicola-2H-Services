package com.acuicola2h.monitor.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class TankMeasurement {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    @JsonBackReference
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "fish_tank_id", nullable = false)
    @JsonBackReference
    private FishTank fishTank;

    @Column(name = "oxygen")
    private double oxygen;

    @Column(name = "ph")
    private double pH;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "salinity")
    private double salinity;

    @Column(name = "nitrate")
    private double nitrate;

    @Column(name = "nitrite")
    private double nitrite;

    @Column(name = "ammonia")
    private double ammonia;

    @Column(name = "turbine")
    private double turbine;

    @Column(name = "alkalinity")
    private double alkalinity;

    @Column(name = "deaths")
    private int deaths;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    
}