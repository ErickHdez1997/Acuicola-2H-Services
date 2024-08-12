package com.acuicola2h.monitor.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class TankMeasurementId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long batchId;
    private Long measurementId;

    public TankMeasurementId() {}

    public TankMeasurementId(Long batchId, Long measurementId) {
        this.batchId = batchId;
        this.measurementId = measurementId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchId, measurementId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankMeasurementId that = (TankMeasurementId) o;
        return Objects.equals(batchId, that.batchId) && Objects.equals(measurementId, that.measurementId);
    }
}
