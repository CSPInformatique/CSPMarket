package com.cspinformatique.cspMarket.service;

import java.util.List;

import com.cspinformatique.cspMarket.entity.Participation;
import com.cspinformatique.cspMarket.entity.Question;
import com.cspinformatique.cspMarket.entity.QuestionAnswer;

public interface QuestionAnswerService {
	public void deleteQuestionAnswer(QuestionAnswer questionAnswer);
	
	public void deleteQuestionAnswers(Question question, Participation participation);
	
	public List<QuestionAnswer> findByQuestionAndParticipation(Question question, Participation participation);
	
	public QuestionAnswer saveQuestionAnswer(QuestionAnswer questionAnswer);
	
	public List<QuestionAnswer> saveQuestionAnswers(Iterable<QuestionAnswer> questionAnswers);
}
