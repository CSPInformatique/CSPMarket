package com.cspinformatique.cspMarket.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class QuestionOption {
	public enum OptionType{
		FIXED,
		OPEN
	}
	
	private int id;
	
	@JsonBackReference
	private Question question;
	private OptionType type;
	private String label;
	private int index;
	
	public QuestionOption() {
		
	}

	public QuestionOption(int id, Question question, OptionType type, String label, int index) {
		this.id = id;
		this.question = question;
		this.type = type;
		this.label = label;
		this.index = index;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="questionId")
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Enumerated(EnumType.STRING)
	public OptionType getType() {
		return type;
	}

	public void setType(OptionType type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
