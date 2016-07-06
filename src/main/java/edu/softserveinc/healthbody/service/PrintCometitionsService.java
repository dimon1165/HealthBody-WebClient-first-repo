package edu.softserveinc.healthbody.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
@WebService
public interface PrintCometitionsService {
	
	@WebMethod
	public List<CompetitionDTO> getAll(int partNumber, int partSize);
	
	@WebMethod
	public List<CompetitionDTO> getAllActive(int partNumber, int partSize);
	
	@WebMethod
	public List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login);
	
	@WebMethod
	public List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login);

}
