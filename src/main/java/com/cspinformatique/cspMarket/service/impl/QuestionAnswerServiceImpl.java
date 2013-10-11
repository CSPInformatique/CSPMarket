package com.cspinformatique.cspMarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.cspMarket.entity.Participation;
import com.cspinformatique.cspMarket.entity.Question;
import com.cspinformatique.cspMarket.entity.QuestionAnswer;
import com.cspinformatique.cspMarket.repository.QuestionAnswerRepository;
import com.cspinformatique.cspMarket.service.QuestionAnswerService;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
	@Autowired QuestionAnswerRepository questionAnswerRepository;
	
	@Override
	public void deleteQuestionAnswer(QuestionAnswer questionAnswer){
		this.questionAnswerRepository.delete(questionAnswer);
	}
	
	@Override
	public void deleteQuestionAnswers(Question question, Participation participation){
		for(QuestionAnswer questionAnswer : this.findByQuestionAndParticipation(question, participation)){
			this.questionAnswerRepository.delete(questionAnswer.getId());
		}
	}
	
	@Override
	public List<QuestionAnswer> findByQuestionAndParticipation(Question question, Participation participation){
		return this.questionAnswerRepository.findByQuestionAndParticipation(question, participation);
	}
	
	@Override
	public QuestionAnswer saveQuestionAnswer(QuestionAnswer questionAnswer) {
		return this.questionAnswerRepository.save(questionAnswer);
	}
	
	@Override
	public List<QuestionAnswer> saveQuestionAnswers(Iterable<QuestionAnswer> questionAnswers){
		return this.questionAnswerRepository.save(questionAnswers);
	}
}
