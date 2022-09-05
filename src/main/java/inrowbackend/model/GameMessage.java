package inrowbackend.model;

import org.json.JSONObject;

public class GameMessage {
	
	private String gameId;
	private String player1Name;
	private String player2Name;
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

	public String getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
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
	
	public void setPlayers(String player1Name,String player2Name) {
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}

	@Override
	public String toString() {
		JSONObject jsonGameMessage = new JSONObject();
		jsonGameMessage.put("gameId", gameId);
		jsonGameMessage.put("player1Name", player1Name);
		jsonGameMessage.put("player2Name", player2Name);
		jsonGameMessage.put("gameBoard", gameBoard);
		jsonGameMessage.put("chat", chat);
		jsonGameMessage.put("detail", detail);
		return jsonGameMessage.toString();
	}
	
}
