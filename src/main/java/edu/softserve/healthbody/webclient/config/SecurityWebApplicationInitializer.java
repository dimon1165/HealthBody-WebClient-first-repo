package edu.softserve.healthbody.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.context.*;

public class SecurityWebApplicationInitializer
      extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
  /*  @Bean
	public UserDetailService userDetailService() {
	 return new UserDetailService();
	}*/
	
}