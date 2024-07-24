package com.leucine.collegedirectory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "StudentProfile")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper=false)
public class StudentProfile extends Users {

//    @Id
//	private Long studentId;
	
    private String photo;
    
    public StudentProfile(Long id, String username, String password, Role role, String name, String email, String phone,
		String photo, @NotBlank(message = "Year cannot be blank") String year) {
	super();
	this.photo = photo;
	this.year = year;
}

//	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @NotBlank(message = "Year cannot be blank")
    private String year;
}

