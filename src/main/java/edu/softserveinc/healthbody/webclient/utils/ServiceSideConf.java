package edu.softserveinc.healthbody.webclient.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = { "edu.softserveinc.healthbody.webclient.utils" })
@PropertySource("classpath:serviceside.properties")
public class ServiceSideConf {

	@Autowired
	private Environment env;

	@Bean
	public DataBaseTemplate getProps() {

		String openShiftLogin = env.getProperty("postgres.open_shift_login");
		String openShiftUrl = env.getProperty("postgres.open_shift_url");
		String openShiftPasword = env.getProperty("postgres.open_shift_password");
		String openShiftDb = env.getProperty("postgres.db");

		DataBaseTemplate dataBaseTemplate =
				new DataBaseTemplate(openShiftUrl, openShiftDb, openShiftLogin,openShiftPasword);

		return dataBaseTemplate;
	}
}