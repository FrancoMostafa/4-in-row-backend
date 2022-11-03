package inrowbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inrowbackend.repository.StatisticsRepository;

@Service
public class StatisticsService {
	
	@Autowired
	private StatisticsRepository statisticsRepository;


}
