package com.cspinformatique.cspMarket.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class QuestionAnswer {
	private int id;
	
	@JsonBackReference
	private Question question;
	
	private Participation participation;
	private QuestionOption questionOption;
	private String content;
	
	public QuestionAnswer() {
		
	}

	public QuestionAnswer(
		int id, 
		Question question,
		Participation participation,
		QuestionOption questionOption,
		String content
	){
		this.id = id;
		this.question = question;
		this.participation = participation;
		this.questionOption = questionOption;
		this.content = content;
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

	@ManyToOne
	@JoinColumn(name="participationId")
	public Participation getParticipation() {
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}

	@ManyToOne
	@JoinColumn(name="questionOptionId")
	public QuestionOption getQuestionOption() {
		return questionOption;
	}

	public void setQuestionOption(QuestionOption questionOption) {
		this.questionOption = questionOption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
