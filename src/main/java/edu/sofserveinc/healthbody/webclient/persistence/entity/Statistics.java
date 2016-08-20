package edu.sofserveinc.healthbody.webclient.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Statistics {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "USER_LOGIN", nullable = false)
	private String userLogin;
	
	@Column(name = "LOGIN_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;
	
	@Column(name = "LOGOUT_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutDate;
	
	public Statistics() {
		
	}
	
	public Statistics(String userLogin, Date loginDate, Date logoutDate) {
		this.userLogin = userLogin;
		this.loginDate = loginDate;
		this.logoutDate = logoutDate;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserLogin() {
		return userLogin;
	}
	
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}
	
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	public Date getLogoutDate() {
		return logoutDate;
	}
		
	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}
}
