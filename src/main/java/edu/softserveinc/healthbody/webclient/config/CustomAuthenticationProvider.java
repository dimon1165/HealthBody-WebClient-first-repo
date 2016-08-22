package edu.softserveinc.healthbody.webclient.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.UserDTO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		try {
			HealthBodyService service = new HealthBodyServiceImplService().getHealthBodyServiceImplPort();
			UserDTO userDTO = service.getUserByLogin(name);
			if (userDTO != null && userDTO.getPassword().equals(password)) {
				return new UsernamePasswordAuthenticationToken(name, password, getAuthorities(userDTO));
			} else {
				throw new UsernameNotFoundException("Username Not Found");
			}
		} catch (Exception e) {
			throw new BadCredentialsException("Bad Credentials");
		}
	}

	private Collection<GrantedAuthority> getAuthorities(UserDTO userDTO) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (userDTO.getRoleName().equals("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}