package com.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.model.User;
import com.api.repository.UserDetailsRepository;
@Service
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User findByUsername = userDetailsRepository.findByUsername(username);
	       if (findByUsername == null) {
	           throw new UsernameNotFoundException("User not found");
	       }
	       return new org.springframework.security.core.userdetails.User(
	    		   findByUsername.getUsername(),
	    		   findByUsername.getPassword(),
	               AuthorityUtils.commaSeparatedStringToAuthorityList(findByUsername.getRole())
	       );
	}

}
