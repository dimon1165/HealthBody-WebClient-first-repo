package edu.softserveinc.healthbody.webclient.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PropertiesApp {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		ServiceSideConf ssconf = applicationContext.getBean(ServiceSideConf.class);
		/* System.out.println("login = " + mongo.getOpenShiftLogin()); */
		System.out.println();

	}

}
