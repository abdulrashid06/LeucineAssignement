package com.leucine.collegedirectory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leucine.collegedirectory.model.Users;
import com.leucine.collegedirectory.repository.UsersRepository;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UsersRepository uRepo;

	@Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = uRepo.findByUsername(username);
        
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Users us = user.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_" + us.getRole());
        authorities.add(autho);

//        // Print all authorities
//        System.out.println("Authorities for user " + us.getUsername() + ":");
//        for (GrantedAuthority authority : authorities) {
//            System.out.println(authority.getAuthority());
//        }

        org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(
                us.getUsername(),
                us.getPassword(),
                authorities
        );

        return secUser;
    }

	}
