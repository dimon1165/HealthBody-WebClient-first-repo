package edu.softserveinc.healthbody.webclient.config;

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