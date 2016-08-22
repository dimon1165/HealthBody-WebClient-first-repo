//package edu.softserveinc.healthbody.webclient.utils;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class PropertiesApp {
//	private static ApplicationContext applicationContext;
//
//	public static void main(String[] args) {
//		applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//		ServiceSideConf ssconf = applicationContext.getBean(ServiceSideConf.class);
//		log.info(ssconf.getProps().getDb());
//
//	}
//
//}
