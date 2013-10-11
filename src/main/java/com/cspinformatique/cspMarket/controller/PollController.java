package com.cspinformatique.cspMarket.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.cspMarket.entity.Participation;
import com.cspinformatique.cspMarket.entity.Poll;
import com.cspinformatique.cspMarket.entity.Question;
import com.cspinformatique.cspMarket.entity.QuestionAnswer;
import com.cspinformatique.cspMarket.service.ParticipationService;
import com.cspinformatique.cspMarket.service.PollService;
import com.cspinformatique.cspMarket.service.QuestionAnswerService;

@Controller
@RequestMapping({"/", "/poll"})
public class PollController {
	@Autowired private ParticipationService participationService;
	@Autowired private PollService pollService;
	@Autowired private QuestionAnswerService questionAnswerService;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value="/{pollId}/question/{question}/answer/{questionAnswer}", method=RequestMethod.DELETE)
	public void deleteQuestionAnswer(
		HttpSession httpSession,
		@PathVariable Integer pollId, 
		@PathVariable Question question,
		@PathVariable QuestionAnswer questionAnswer
	){
		if(	questionAnswer.getQuestion().getId() == question.getId() &&
			questionAnswer.getParticipation().getPoll().getId() == pollId
		){
			this.questionAnswerService.deleteQuestionAnswer(questionAnswer);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{pollId}/participation", method=RequestMethod.GET)
	public @ResponseBody Participation getParticipation(HttpSession httpSession, @PathVariable Integer pollId){
		return this.participationService.findBySessionIdAndPollId(httpSession.getId(), pollId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{pollId}")
	public String getPollPage(Model model, @PathVariable int pollId){
		model.addAttribute("pollId", pollId);
		
		return "poll";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping("/{poll}/question")
	public @ResponseBody List<Question> getPollQuestions(@PathVariable Poll poll){
		return this.pollService.getPollQuestions(poll);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{pollId}/question/{question}/answer", method=RequestMethod.GET)
	public @ResponseBody List<QuestionAnswer> getQuestionAnswers(
		HttpSession httpSession,
		@PathVariable Integer pollId, 
		@PathVariable Question question
	){
		return this.questionAnswerService.findByQuestionAndParticipation(
			question, 
			this.participationService.findBySessionIdAndPollId(httpSession.getId(), pollId)
		);
	}
	
	@RequestMapping
	public String getPollsPage(){
		return "redirect:/poll/1";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{poll}/participation", method={RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Participation saveParticipation(
		@RequestBody Participation participation, 
		@PathVariable Poll poll, 
		HttpSession httpSession
	){
		participation.setPoll(poll);
		if(participation.getSessionId() == null){
			participation.setSessionId(httpSession.getId());
			participation.setTimestamp(new Date());
		}
		return this.participationService.saveParticipation(participation);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{pollId}/question/{question}/answer", method={RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody QuestionAnswer saveQuestionAnswer(
		@RequestBody QuestionAnswer questionAnswer,
		@PathVariable Question question,
		@PathVariable Integer pollId,
		HttpSession httpSession
	){
		Participation participation = this.participationService.findBySessionIdAndPollId(httpSession.getId(), pollId);
		
		questionAnswer.setParticipation(participation);
		questionAnswer.setQuestion(question);
		
		return this.questionAnswerService.saveQuestionAnswer(questionAnswer);
	}
}
