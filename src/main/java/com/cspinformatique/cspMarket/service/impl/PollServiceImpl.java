package com.cspinformatique.cspMarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.cspMarket.entity.Poll;
import com.cspinformatique.cspMarket.entity.Question;
import com.cspinformatique.cspMarket.repository.PollRepository;
import com.cspinformatique.cspMarket.service.PollService;
import com.cspinformatique.cspMarket.service.QuestionService;

@Service
public class PollServiceImpl implements PollService{
	@Autowired private PollRepository pollRepository;
	
	@Autowired private QuestionService questionService;
	
	@Override
	public Poll getPoll(int id) {
		return this.pollRepository.findOne(id);
	}
	
	@Override
	public List<Question> getPollQuestions(Poll poll){
		return this.questionService.getPollQuestions(poll);
	}

}
