package inrowbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<StatisticsModel> getStatistics() {
		return null;
	}
	
	@RequestMapping(value = "/{day}/{month}/{year}", method = RequestMethod.GET)
	public ResponseEntity<StatisticsModel> getStatisticsByDate(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year) {
		return null;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<StatisticsModel> addStatisticsData(@RequestBody String gameId, @RequestBody String player1County, @RequestBody String player2Country) {
		return null;
	}

}
