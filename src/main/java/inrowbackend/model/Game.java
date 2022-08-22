package inrowbackend.model;

import org.springframework.web.socket.WebSocketSession;

public class Game {

	private String gameId;
	private WebSocketSession user1 = null;
	private WebSocketSession user2 = null;
	
	public Game(String gameId) {
		this.gameId = gameId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public WebSocketSession getUser1() {
		return user1;
	}

	public void setUser1(WebSocketSession user1) {
		this.user1 = user1;
	}

	public WebSocketSession getUser2() {
		return user2;
	}

	public void setUser2(WebSocketSession user2) {
		this.user2 = user2;
	}
	
	public void addUser(WebSocketSession user) {
		if (this.getUser1() == null) {
			this.user1 = user;
		}
		else {
			this.user2 = user;
		}
	}

}
