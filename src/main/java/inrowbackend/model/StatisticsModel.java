package inrowbackend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("statistics")
public class StatisticsModel {
	
    @Id
    private String id;
    
	private LocalDate date;
	private List<String> publicGamesStarted;
	private List<String> publicGamesFinished;
	private List<String> privateGamesStarted;
	private List<String> privateGamesFinished;
	private List<String> playersCountries;
	
	
	public StatisticsModel() {
		this.date = LocalDate.now();
		publicGamesStarted = new ArrayList<String>();
		publicGamesFinished = new ArrayList<String>();
		privateGamesStarted = new ArrayList<String>();
		privateGamesFinished = new ArrayList<String>();
		playersCountries = new ArrayList<String>();
	}
	
	public StatisticsModel(LocalDate today) {
		this.date = today;
		publicGamesStarted = new ArrayList<String>();
		publicGamesFinished = new ArrayList<String>();
		privateGamesStarted = new ArrayList<String>();
		privateGamesFinished = new ArrayList<String>();
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
	
	public List<String> getPublicGamesStarted() {
		return publicGamesStarted;
	}
	
	public void setPublicGamesStarted(List<String> publicGamesStarted) {
		this.publicGamesStarted = publicGamesStarted;
	}
	
	public List<String> getPublicGamesFinished() {
		return publicGamesFinished;
	}
	
	public void setPublicGamesFinished(List<String> publicGamesFinished) {
		this.publicGamesFinished = publicGamesFinished;
	}
	
	public List<String> getPrivateGamesStarted() {
		return privateGamesStarted;
	}
	
	public void setPrivateGamesStarted(List<String> privateGamesStarted) {
		this.privateGamesStarted = privateGamesStarted;
	}
	
	public List<String> getPrivateGamesFinished() {
		return this.privateGamesFinished;
	}
	
	public void setPrivateGamesFinished(List<String> privateGamesFinished) {
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

}
