package com.acuicola2h.monitor.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class FishTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 
    
    private String tankNotes;

    @OneToMany(mappedBy = "fishTank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Batch> batches;
    
    @Transient
    private Long activeBatchId;

    public Long getActiveBatchId() {
        if (batches != null) {
            for (Batch batch : batches) {
                if (batch.isInProgress()) {
                    return batch.getId();
                }
            }
        }
        return null;
    }

}