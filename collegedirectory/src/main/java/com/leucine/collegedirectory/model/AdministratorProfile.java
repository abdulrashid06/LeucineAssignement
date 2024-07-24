package com.leucine.collegedirectory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AdministratorProfile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper=false)
public class AdministratorProfile extends  Users {

//	@Id
//	private Long adminId;
	
private String photo;
    
    public AdministratorProfile(Long id, String username, String password, Role role, String name, String email,
		String phone, String photo) {
		super(id, username, password, role, name, email, phone);
	this.photo = photo;
}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

}
