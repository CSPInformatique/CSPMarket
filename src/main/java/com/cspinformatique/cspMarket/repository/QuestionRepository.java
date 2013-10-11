package com.cspinformatique.cspMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.cspMarket.entity.Poll;
import com.cspinformatique.cspMarket.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	public List<Question> findByPoll(Poll poll);
}
