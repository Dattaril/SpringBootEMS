package com.example.EMS_UST;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
// This tells the JPA that this class contains all the details of Tables

@Table (name="INDUST")

public class EMS 
{
	@Id
	@Column(name="id")
	private String eID;
	
	@Column (name="ename")
	private String ename;
	
	@Column (name="esal")
	private String esal;

	public String geteID() {
		return eID;
	}

	public void seteID(String eID) {
		this.eID = eID;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEsal() {
		return esal;
	}

	public void setEsal(String esal) {
		this.esal = esal;
	}
	
	

	
	

}
