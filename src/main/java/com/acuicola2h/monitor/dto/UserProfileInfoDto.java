package com.acuicola2h.monitor.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserProfileInfoDto {
	
	private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isAdmin;
    private LocalDateTime createdDate;
    private String createdBy;
    private String notes;

}
