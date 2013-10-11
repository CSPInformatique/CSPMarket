package com.cspinformatique.cspMarket.service;

import com.cspinformatique.cspMarket.entity.Participation;

public interface ParticipationService {
	public Participation findBySessionIdAndPollId(String sessionId, int pollId);
	
	public Participation saveParticipation(Participation participation); 
}
