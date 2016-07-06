package edu.softserveinc.healthbody.service.impl;

import java.util.List;

import javax.jws.WebService;

import edu.softserveinc.healthbody.service.CompetitionDTO;
import edu.softserveinc.healthbody.service.PrintCometitionsService;

@WebService(endpointInterface = "edu.softserveinc.healthbody.service.PrintCometitionsService")
public class PrintCompetitionsImpl implements PrintCometitionsService{

	@Override
	public List<CompetitionDTO> getAll(int partNumber, int partSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllActive(int partNumber, int partSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
