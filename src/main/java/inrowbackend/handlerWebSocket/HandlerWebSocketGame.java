package inrowbackend.handlerWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import inrowbackend.model.Game;
import inrowbackend.model.GameMessage;

@Service
public class HandlerWebSocketGame extends TextWebSocketHandler {
	
	private Collection<Game> games = Collections.synchronizedCollection(new ArrayList<Game>());
	private Object flag = new Object();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	var gameData = this.messageToGameMessageObject(message);
        if(gameData.getDetail() == "ADD ME TO GAME") {
        	this.addSessionToGame(gameData, session);
        }
        else {
        	this.sendMessageToGame(gameData);
        }
    }

	@SuppressWarnings("null")
	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		var gameOfSession = this.getGameOfSession(session.getId());
		if(gameOfSession == null && !(gameOfSession.getUser1().isOpen() && gameOfSession.getUser2().isOpen())) {
			this.games.remove(gameOfSession);
		}
    }

	private void addSessionToGame(GameMessage gameData, WebSocketSession session) {
		synchronized (flag) {
			Game game = this.getGameById(gameData.getGameId());
			if(game != null) {
				game.addUser(session);
			}
			else {
				this.createGame(gameData);
			}
		}
	}

	private void createGame(GameMessage gameId) {
		this.games.add(new Game(gameId.toString()));
	}

	private Game getGameById(String data) {
		var game =  this.games.stream().filter(g -> g.getGameId() == data.toString()).collect(Collectors.toList());
		if (game.size() == 0) {
			return null;
		}
		return game.get(0);
	}
	
	private void sendMessageToGame(GameMessage data) throws IOException {
		var game = this.getGameById(data.getGameId());
		((Game) game).getUser1().sendMessage(new TextMessage(data.toString()));
		((Game) game).getUser2().sendMessage(new TextMessage(data.toString()));
	}
	
	private GameMessage messageToGameMessageObject(TextMessage message) {
		var messageData = message.toString();
		JSONObject jsonData = new JSONObject(messageData);  
		return new GameMessage(jsonData.get("gameId"),jsonData.get("gameBoard"),jsonData.get("chat"),jsonData.get("detail"));
	}
	
    private Game getGameOfSession(String session) {
        Iterator<Game> iterator = this.games.iterator();
        while (iterator.hasNext()) {
        	if(iterator.next().getUser1().getId() == session || iterator.next().getUser2().getId() == session) {
        		return iterator.next();
        	}
        }
        return null;
	}

    
}