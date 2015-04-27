package com.excilys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Role;
import com.excilys.persistence.dao.UserDAO;

@Service("userDetailsService")
public class CdbUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
 
		com.excilys.model.User user = userDAO.getByUsername(username);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> roles = userDAO.getRoles(username);
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return new User(user.getUsername(), user.getPassword(), 
				user.isEnabled(), true, true, true, authorities);	 
	}	 
}
