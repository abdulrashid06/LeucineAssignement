package com.leucine.collegedirectory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.leucine.collegedirectory.model.Users;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
	
	private Users user;

	public UserDetails(Users user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
//		List<Authority>
//		List<Authority> auths= user.getAuthorities();
//		for(Authority auth:auths) {
//		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(auth.getName());
//		authorities.add(simpleGrantedAuthority);
//		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}