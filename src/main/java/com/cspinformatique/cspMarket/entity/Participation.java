package com.cspinformatique.cspMarket.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Participation {
	private int id;
	private String sessionId;
	private Poll poll;
	private Date timestamp;
	private String gender;
	private int age;
	private String activity;
	private String zipCode;
	
	public Participation() {
		
	}

	public Participation(
		int id, 
		String sessionId,
		Poll poll, 
		Date timestamp, 
		String gender,
		int age, 
		String activity,
		String zipCode
	){
		this.id = id;
		this.sessionId = sessionId;
		this.poll = poll;
		this.timestamp = timestamp;
		this.gender = gender;
		this.age = age;
		this.activity = activity;
		this.zipCode = zipCode;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@ManyToOne
	@JoinColumn(name="pollId")
	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
