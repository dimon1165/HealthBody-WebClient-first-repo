package edu.softserveinc.healthbody.webclient.wrapperD;

public class URLEnumForConnectionToService { 
	
	public enum URLEnumForConnection{
		GET_USER_BY_LOGIN("%s/HealthBody-WebService/listener/%s?login=%s"),
		GET_GROUP_BY_NAME("%s/HealthBody-WebService/listener/%s?groupName=%s"),
		PART_NUMBER_PART_SIZE("%s/HealthBody-WebService/listener/%s?partNumber=%d&partSize=%d"),
		PART_NUMBER_PART_SIZE_LOGIN("%s/HealthBody-WebService/listener/%s?partNumber=%d&partSize=%d&login=%s");
		
		String urlForConnetionToListener;
		
		private URLEnumForConnection(String urlForConnetionToListener) {
			this.urlForConnetionToListener = urlForConnetionToListener;
		}

		public String getURLForConnectionToListener(){
			return urlForConnetionToListener;
		}
	}  
}