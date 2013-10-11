package com.cspinformatique.cspMarket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.cspMarket.entity.Participation;
import com.cspinformatique.cspMarket.repository.ParticipationRepository;
import com.cspinformatique.cspMarket.service.ParticipationService;

@Service
public class ParticipationServiceImpl implements ParticipationService {
	@Autowired private ParticipationRepository participationRepository;
	
	@Override
	public Participation findBySessionIdAndPollId(String sessionId, int pollId){
		return this.participationRepository.findBySessionIdAndPollId(sessionId, pollId);
	}
	
	@Override
	public Participation saveParticipation(Participation participation) {
		return this.participationRepository.save(participation);
	}
}
