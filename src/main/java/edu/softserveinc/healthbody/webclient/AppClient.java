package edu.softserveinc.healthbody.webclient;

import edu.softserveinc.healthbody.webclient.soap.SOAPHandler;

public class AppClient{ 
 
	public static void main(String[] args) throws Exception{        
        System.out.println("Started WebClient: "); 
        SOAPHandler soapHandler = new SOAPHandler("http://ws-healthbody.rhcloud.com/HealthBody-WebService/HealthBodyService?wsdl");
		soapHandler.getAllRequestEntityFromWS(1, 2, "getAllGroups", "all_groups.xml");
        soapHandler.convertXmlToHtml("all_groups.xml", "groups.xsl", "group.html");
	}
	
}
