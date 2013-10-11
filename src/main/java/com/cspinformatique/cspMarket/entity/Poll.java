package com.cspinformatique.cspMarket.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Poll {
	private int id;
	
	private String name;
	private String description;
	private Date creationDate;
	
	@JsonManagedReference
	private List<Question> question;
	
	public Poll() {
		
	}

	public Poll(int id, String name, String description, Date creationDate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@OneToMany
	@JoinColumn(name="pollId")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
}
