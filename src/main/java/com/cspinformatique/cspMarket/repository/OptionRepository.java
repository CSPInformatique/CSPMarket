package com.cspinformatique.cspMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.cspMarket.entity.QuestionOption;

public interface OptionRepository extends JpaRepository<QuestionOption, Integer> {

}
