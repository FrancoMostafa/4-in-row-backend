package inrowbackend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("statistics")
public class StatisticsModel {
	
    @Id
    private String id;
    
	private LocalDate date;
	private Set<String> publicGamesStarted;
	private Set<String> publicGamesFinished;
	private Set<String> privateGamesStarted;
	private Set<String> privateGamesFinished;
	private List<String> playersCountries;
	
	
	public StatisticsModel() {
		this.date = LocalDate.now();
		publicGamesStarted = new HashSet<String>();
		publicGamesFinished = new HashSet<String>();
		privateGamesStarted = new HashSet<String>();
		privateGamesFinished = new HashSet<String>();
		playersCountries = new ArrayList<String>();
	}
	
	public StatisticsModel(LocalDate today) {
		this.date = today;
		publicGamesStarted = new HashSet<String>();
		publicGamesFinished = new HashSet<String>();
		privateGamesStarted = new HashSet<String>();
		privateGamesFinished = new HashSet<String>();
		playersCountries = new ArrayList<String>();
	}
	
	public String getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public Set<String> getPublicGamesStarted() {
		return publicGamesStarted;
	}
	
	public void setPublicGamesStarted(Set<String> publicGamesStarted) {
		this.publicGamesStarted = publicGamesStarted;
	}
	
	public Set<String> getPublicGamesFinished() {
		return publicGamesFinished;
	}
	
	public void setPublicGamesFinished(Set<String> publicGamesFinished) {
		this.publicGamesFinished = publicGamesFinished;
	}
	
	public Set<String> getPrivateGamesStarted() {
		return privateGamesStarted;
	}
	
	public void setPrivateGamesStarted(Set<String> privateGamesStarted) {
		this.privateGamesStarted = privateGamesStarted;
	}
	
	public Set<String> getPrivateGamesFinished() {
		return this.privateGamesFinished;
	}
	
	public void setPrivateGamesFinished(Set<String> privateGamesFinished) {
		this.privateGamesFinished = privateGamesFinished;
	}
	
	public List<String> getPlayersCountries() {
		return playersCountries;
	}

	public void setPlayersCountries(List<String> playersCountries) {
		this.playersCountries = playersCountries;
	}

	public boolean isOfDate(Integer day, Integer month, Integer year) {
		return this.getDate().getDayOfMonth() == day && this.getDate().getMonth().getValue() == month && this.getDate().getYear() == year;
	}
	
	public boolean isOlderThan(StatisticsModel otherStatistics) {
		return otherStatistics.getDate().isAfter(this.getDate());
				
	}

}
