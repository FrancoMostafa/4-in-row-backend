package inrowbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import inrowbackend.model.StatisticsModel;
import inrowbackend.service.StatisticsService;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<StatisticsModel>> getStatistics() {
		List<StatisticsModel> response = statisticsService.getStatistics();
		return new ResponseEntity<List<StatisticsModel>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{day}/{month}/{year}", method = RequestMethod.GET)
	public ResponseEntity<StatisticsModel> getStatisticsByDate(@PathVariable Integer day,@PathVariable  Integer month, @PathVariable Integer year) {
		StatisticsModel response = statisticsService.getStatisticsByDate(day, month, year);
		return new ResponseEntity<StatisticsModel>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{gameId}/{type}/{state}/{player1Country}/{player2Country}", method = RequestMethod.POST)
	public ResponseEntity<StatisticsModel> addStatisticsData(@PathVariable String gameId, @PathVariable String type, @PathVariable String state , @PathVariable String player1Country,@PathVariable  String player2Country) {
		StatisticsModel response = statisticsService.addStatisticsData(gameId, type, state, player1Country, player2Country);
		return new ResponseEntity<StatisticsModel>(response, HttpStatus.CREATED);
	}

}
