package com.leucine.collegedirectory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FacultyProfile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyProfile extends Users {
//    @Id
//    private Long facultytId;
	
    private String photo;
    
    
    public FacultyProfile(Long id, String username, String password, Role role, String name, String email, String phone,
		String photo, @NotBlank(message = "Office hours cannot be blank") String officeHours) {
        super(id, username, password, role, name, email, phone);
	this.photo = photo;
	this.officeHours = officeHours;
}

//	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @NotBlank(message = "Office hours cannot be blank")
    private String officeHours;
}

