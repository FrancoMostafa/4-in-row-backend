package inrowbackend.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("statistics")
public class StatisticsModel {
	
    @Id
    private String id;
    
	private Date date;
	private List<String> PublicGamesStarted;
	private List<String> PublicGamesFinished;
	private List<String> PrivateGamesStarted;
	private List<String> PrivateGamesFinished;
	private List<String> countriesOfPlayers;
	
	public StatisticsModel(Date date, List<String> publicGamesStarted, List<String> publicGamesFinished, List<String> privateGamesStarted, List<String> privateGamesFinished, List<String> countriesOfPlayers) {
		this.date = date;
		PublicGamesStarted = publicGamesStarted;
		PublicGamesFinished = publicGamesFinished;
		PrivateGamesStarted = privateGamesStarted;
		PrivateGamesFinished = privateGamesFinished;
		this.countriesOfPlayers = countriesOfPlayers;
	}
	
	public String getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<String> getPublicGamesStarted() {
		return PublicGamesStarted;
	}
	
	public void setPublicGamesStarted(List<String> publicGamesStarted) {
		PublicGamesStarted = publicGamesStarted;
	}
	
	public List<String> getPublicGamesFinished() {
		return PublicGamesFinished;
	}
	
	public void setPublicGamesFinished(List<String> publicGamesFinished) {
		PublicGamesFinished = publicGamesFinished;
	}
	
	public List<String> getPrivateGamesStarted() {
		return PrivateGamesStarted;
	}
	
	public void setPrivateGamesStarted(List<String> privateGamesStarted) {
		PrivateGamesStarted = privateGamesStarted;
	}
	
	public List<String> getPrivateGamesFinished() {
		return PrivateGamesFinished;
	}
	
	public void setPrivateGamesFinished(List<String> privateGamesFinished) {
		PrivateGamesFinished = privateGamesFinished;
	}
	
	public List<String> getCountriesOfPlayers() {
		return countriesOfPlayers;
	}
	
	public void setCountriesOfPlayers(List<String> countriesOfPlayers) {
		this.countriesOfPlayers = countriesOfPlayers;
	}

}
