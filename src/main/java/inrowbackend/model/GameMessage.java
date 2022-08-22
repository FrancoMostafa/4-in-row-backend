package inrowbackend.model;

import java.util.List;

public class GameMessage {
	
	private String gameId;
	private List<Integer> gameBoard;
	private List<String> chat;
	private String detail;
	
	@SuppressWarnings("unchecked")
	public GameMessage(Object gameId, Object gameBoard, Object chat, Object detail) {
		super();
		this.gameId = (String) gameId;
		this.gameBoard = (List<Integer>) gameBoard;
		this.chat = (List<String>) chat;
		this.detail = (String) detail;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public List<Integer> getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(List<Integer> gameBoard) {
		this.gameBoard = gameBoard;
	}

	public List<String> getChat() {
		return chat;
	}

	public void setChat(List<String> chat) {
		this.chat = chat;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
