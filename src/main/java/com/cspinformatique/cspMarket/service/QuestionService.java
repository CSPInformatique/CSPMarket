package com.cspinformatique.cspMarket.service;

import java.util.List;

import com.cspinformatique.cspMarket.entity.Poll;
import com.cspinformatique.cspMarket.entity.Question;

public interface QuestionService {
	public List<Question> getPollQuestions(Poll poll);
}
