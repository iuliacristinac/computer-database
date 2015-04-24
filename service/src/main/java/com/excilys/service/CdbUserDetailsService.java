package com.excilys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			Set<Role> roles = user.getRoles();
			
			for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
			return new User(user.getUsername(), user.getPassword(), 
					true, true, true, true, authorities);	 
		}
		
	 
//		@Transactional(readOnly=true)
//		@Override
//		public UserDetails loadUserByUsername(final String username) 
//			throws UsernameNotFoundException {
//	 
//			com.excilys.model.User user = userDAO.getByUsername(username);
//			
//			List<GrantedAuthority> authorities = 
//	                                      buildUserAuthority(user.getRoles());
//	 
//			return buildUserForAuthentication(user, authorities);
//	 
//		}
//	 
//		// Converts com.excilys.model.User user to
//		// org.springframework.security.core.userdetails.User
//		private User buildUserForAuthentication(com.excilys.model.User user, 
//			List<GrantedAuthority> authorities) {
//			
//			return new User(user.getUsername(), user.getPassword(), 
//				true, true, true, true, authorities);
//		}
//	 
//		private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
//	 
//			Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//			
////			 Build user's authorities
//			for (Role userRole : userRoles) {
//				setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//			}
//	 
//			List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//	 
//			return Result;
//		}
	 
}
