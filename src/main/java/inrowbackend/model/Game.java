package inrowbackend.model;

import org.springframework.web.socket.WebSocketSession;

public class Game {

	private String gameId;
	private WebSocketSession user1;
	private WebSocketSession user2;
	
	public Game(String gameId, WebSocketSession user1, WebSocketSession user2) {
		this.gameId = gameId;
		this.user1 = user1;
		this.user2 = user2;
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

}
