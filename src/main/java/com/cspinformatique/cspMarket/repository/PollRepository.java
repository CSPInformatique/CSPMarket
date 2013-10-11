package com.cspinformatique.cspMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.cspMarket.entity.Poll;

public interface PollRepository extends JpaRepository<Poll, Integer> {

}
