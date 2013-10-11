package com.cspinformatique.cspMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.cspMarket.entity.Participation;
import com.cspinformatique.cspMarket.entity.Question;
import com.cspinformatique.cspMarket.entity.QuestionAnswer;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {	
	public List<QuestionAnswer> findByQuestionAndParticipation(Question question, Participation participation);
}
