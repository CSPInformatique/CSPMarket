package com.cspinformatique.cspMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cspinformatique.cspMarket.entity.QuestionAnswer;
import com.cspinformatique.cspMarket.service.QuestionAnswerService;

@Controller
@RequestMapping("/questionAnswer")
public class QuestionAnswerController {
	@Autowired private QuestionAnswerService questionAnswerService;
	
	@RequestMapping
	public QuestionAnswer saveQuestionAnswer(@RequestBody QuestionAnswer questionAnswer){
		return this.questionAnswerService.saveQuestionAnswer(questionAnswer);
	}
}
