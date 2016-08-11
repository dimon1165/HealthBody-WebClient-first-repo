package edu.softserveinc.healthbody.webclient.models;

import java.sql.Date;

public class CompetitionModel {

	private String idCompetition;
	private String name;
	private int count;
	private Date startDate;
	private Date finishDate;
	private String description;

	// getters
	public String getIdCompetition() {
		return idCompetition;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public String getDescription() {
		return description;
	}

	// setters
	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
