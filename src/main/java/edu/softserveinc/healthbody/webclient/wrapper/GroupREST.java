package edu.softserveinc.healthbody.webclient.wrapper;

import java.io.IOException;
import java.net.URL;

public class GroupREST implements ControllerRESTStrategy{
	
	private int constructor;
	private String nameMethod;
	private int partNumber;
	private int partSize;
	private String groupName;
	
	
	
	public GroupREST(String nameMethod, String groupName) {
		this.constructor = 1;
		this.nameMethod = nameMethod;
		this.groupName = groupName;
	}

	

	public GroupREST(String nameMethod, int partNumber, int partSize) {
		this.constructor = 2;
		this.nameMethod = nameMethod;
		this.partNumber = partNumber;
		this.partSize = partSize;
	}



	@Override
	public URL createRESTRequest() throws IOException {
		URL url;
		if (constructor == 1) {
			url = new URL(BASE_URL + nameMethod + "?groupName=" + groupName);
			System.out.println(url);
			return url;
		} 
		if (constructor == 2) {
			url = new URL(BASE_URL + nameMethod + "?partNumber=" + partNumber + "&partSize=" + partSize);
			System.out.println(url);
			return url;
		} else {
			System.out.println("Created unless constructor of " + getClass().getName());
			url = null;
			System.out.println(url);
			return url;
		}
	}

}
