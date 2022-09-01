package inrowbackend.model;

import org.json.JSONObject;

public class GameMessage {
	
	private String gameId;
	private String player1;
	private String player2;
	private Object gameBoard;
	private Object chat;
	private String detail;
	
	public GameMessage(Object gameId, Object gameBoard, Object chat, Object detail) {
		super();
		this.gameId = (String) gameId;
		this.gameBoard = gameBoard;
		this.chat = chat;
		this.detail = (String) detail;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public Object getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Object gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Object getChat() {
		return chat;
	}

	public void setChat(Object chat) {
		this.chat = chat;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public void setPlayersNames(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
	public String toString() {
		JSONObject jsonGameMessage = new JSONObject();
		jsonGameMessage.put("gameId", gameId);
		jsonGameMessage.put("player1", player1);
		jsonGameMessage.put("player2", player2);
		jsonGameMessage.put("gameBoard", gameBoard);
		jsonGameMessage.put("chat", chat);
		jsonGameMessage.put("detail", detail);
		return jsonGameMessage.toString();
	}
	
}
