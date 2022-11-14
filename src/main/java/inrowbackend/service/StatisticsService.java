package inrowbackend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inrowbackend.model.StatisticsModel;
import inrowbackend.repository.StatisticsRepository;

@Service
public class StatisticsService {
	
	@Autowired
	private StatisticsRepository statisticsRepository;
	
	public List<StatisticsModel> getStatistics() {
		List<StatisticsModel> response = statisticsRepository.findAll();
		return response;
	}

	public synchronized StatisticsModel getStatisticsByDate(Integer day, Integer month, Integer year) {
		List<StatisticsModel> response = statisticsRepository.findAll();
		StatisticsModel result = null;
		for(int i = 0; i < response.size(); i++) {
			if(response.get(i).isOfDate(day, month, year)) {
				result = response.get(i);
				break;
			}
		}
		return result;
	}

	public synchronized StatisticsModel addStatisticsData(String gameId, String type, String state,
		String playerCountry) {
		LocalDate today = LocalDate.now();
		if(!this.thisDayIsSaveInDB(today)) {
			this.addStatisticsDocumentInDB(today);
		}
		StatisticsModel result =  this.modifyStatisticsDocumentInDB(today, gameId, type, state, playerCountry);
		return result;
	}

	private synchronized StatisticsModel modifyStatisticsDocumentInDB(LocalDate today, String gameId, String type, String state, String playerCountry) {
		StatisticsModel statistics = this.getStatisticsByDate(today.getDayOfMonth(), today.getMonth().getValue(), today.getYear());
		statistics.getPlayersCountries().add(playerCountry);
		if(type.equals("PUBLIC") && state.equals("STARTED")) {
				statistics.getPublicGamesStarted().add(gameId);
				statisticsRepository.save(statistics);
				return statistics;
			
		}
		else if(type.equals("PUBLIC") && state.equals("FINISHED")) {
				statistics.getPublicGamesFinished().add(gameId);
				statisticsRepository.save(statistics);
				return statistics;
			
		}
		else if(type.equals("PRIVATE") && type.equals("STARTED")) {
				statistics.getPrivateGamesStarted().add(gameId);
				statisticsRepository.save(statistics);
				return statistics;
			
		}
		else {
				statistics.getPrivateGamesFinished().add(gameId);
				statisticsRepository.save(statistics);
				return statistics;
			
		}
	}
	
	private synchronized void addStatisticsDocumentInDB(LocalDate today) {
		StatisticsModel statistics = new StatisticsModel(today);
		List<StatisticsModel> response = statisticsRepository.findAll();
		if(response.size() == 30) {
			statisticsRepository.delete(this.getTheOldest(response));
		}
		statisticsRepository.save(statistics);
	}
	
	private synchronized boolean thisDayIsSaveInDB(LocalDate today) {
		List<StatisticsModel> response = statisticsRepository.findAll();
		for(int i = 0; i < response.size(); i++) {
			if(response.get(i).isOfDate(today.getDayOfMonth(), today.getMonth().getValue(), today.getYear())) {
				return true;
			}
		}
		return false;
	}
	
	private synchronized StatisticsModel getTheOldest(List<StatisticsModel> data) {
		StatisticsModel result = data.get(0);
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).isOlderThan(result)) {
				result = data.get(i);
			}
		}
		return result;
	}

}
