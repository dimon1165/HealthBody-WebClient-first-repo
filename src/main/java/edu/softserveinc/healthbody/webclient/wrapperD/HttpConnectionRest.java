package edu.softserveinc.healthbody.webclient.wrapperD;

import java.io.IOException;
import java.util.Formatter;

public class HttpConnectionRest {
	public static final String BASE_URL_LOCAL_HOST = "http://localhost:8080";

	public void sendGet() throws IOException {
		Formatter formatter = new Formatter();
		String url = formatter.format(URLEnumForConnectionToService
						.URLEnumForConnection
						.PART_NUMBER_PART_SIZE
						.getURLForConnectionToListener(), BASE_URL_LOCAL_HOST, "GroupsParticipants", 1 , 1).toString();
		
		formatter.close(); 
	}
}
