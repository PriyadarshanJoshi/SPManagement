package com.neosoft.SportsClubManagementWeb.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerImpl implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getPrincipal().toString().trim();
		String password = authentication.getCredentials().toString().trim();
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		UsernamePasswordAuthenticationToken token = null;
		if(userName.equals("Sonu") && password.equals("abc123")) {
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			token = new UsernamePasswordAuthenticationToken(userName, password,grantedAuths);
		}else {
			throw new UsernameNotFoundException(
					String.format("Invalid credentials", authentication.getPrincipal()));
		}	
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
