package edu.softserveinc.healthbody.webclient.wrapper;

import java.io.IOException;
import java.net.URL;

public class CompettionREST implements ControllerRESTStrategy {

	private int constructor;
	private String nameMethod;
	private int partNumber;
	private int partSize;
	private String login;

	public CompettionREST(String nameMethod, int partNumber, int partSize) {
		this.constructor = 1;
		this.nameMethod = nameMethod;
		this.partNumber = partNumber;
		this.partSize = partSize;
	}

	public CompettionREST(String nameMethod, int partNumber, int partSize, String login) {
		this.constructor = 2;
		this.nameMethod = nameMethod;
		this.partNumber = partNumber;
		this.partSize = partSize;
		this.login = login;
	}

	// Getters
	public String getNameMethod() {
		return nameMethod;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public int getPartSize() {
		return partSize;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public URL createRESTRequest() throws IOException {
		URL url;
		if (constructor == 1) {
			url = new URL(BASE_URL + nameMethod + "?partNumber=" + partNumber + "&partSize=" + partSize);
			System.out.println(url);
			return url;
		} 
		if (constructor == 2) {
			url = new URL(BASE_URL + nameMethod + "?partNumber=" + partNumber + "&partSize=" + partSize 
					+ "&login=" + login);
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
