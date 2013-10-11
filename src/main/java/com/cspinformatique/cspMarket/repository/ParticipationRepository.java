package com.cspinformatique.cspMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.cspMarket.entity.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
	public Participation findBySessionIdAndPollId(String sessionId, int pollId);
}
