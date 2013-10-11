package com.cspinformatique.cspMarket.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	public enum QuestionType{
		MULTIPLE_OPTIONS,
		OPTIONS
	}
	
	private int id;
	
	@JsonBackReference
	private Poll poll;
	
	private QuestionType type;
	private String label;
	private int index;
	
	@JsonManagedReference
	private List<QuestionOption> questionOptions;
	
	public Question() {
		
	}

	public Question(
		int id, 
		Poll poll, 
		QuestionType type, 
		String label,
		int index,
		List<QuestionOption> questionOptions
	){
		this.id = id;
		this.poll = poll;
		this.type = type;
		this.label = label;
		this.index = index;
		this.questionOptions = questionOptions;
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
	@JoinColumn(name="pollId")
	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	@Enumerated(EnumType.STRING)
	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
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

	@OneToMany
	@JoinColumn(name="questionId")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<QuestionOption> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(List<QuestionOption> questionOptions) {
		this.questionOptions = questionOptions;
	}
	
}
