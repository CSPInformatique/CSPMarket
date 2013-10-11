package com.cspinformatique.cspMarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.cspMarket.entity.Poll;
import com.cspinformatique.cspMarket.entity.Question;
import com.cspinformatique.cspMarket.repository.QuestionRepository;
import com.cspinformatique.cspMarket.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired private QuestionRepository questionRepository;
	
	@Override
	public List<Question> getPollQuestions(Poll poll) {
		return this.questionRepository.findByPoll(poll);
	}
}
