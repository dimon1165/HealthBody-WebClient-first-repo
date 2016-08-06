package edu.softserveinc.healthbody.webclient.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.api.UserDTO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDTO userDTO = new HealthBodyServiceImplService().getHealthBodyServiceImplPort().getUserByLogin(name);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (userDTO.getRoleName().equals("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (userDTO.getPassword().equals(password)) {
			return new UsernamePasswordAuthenticationToken(name, password, authorities);
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}