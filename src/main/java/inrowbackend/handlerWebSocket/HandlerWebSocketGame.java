package inrowbackend.handlerWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.javatuples.Pair;
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
		synchronized (flag) {
			var gameData = this.messageToGameMessageObject(message);
			if(gameData.getDetail().contains("ADD ME TO GAME;")) {
				this.addSessionToGame(gameData, session);
			}
        	else {
        		this.sendMessageToGame(gameData);
        	}
		}
    }

	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.clearGames(session.getId());
    }

	private void addSessionToGame(GameMessage gameData, WebSocketSession session) throws IOException {
		synchronized (flag) {
			Game game = this.getGameById(gameData.getGameId());
			if(game == null) {
				this.createGame(gameData.getGameId());
				game = this.getGameById(gameData.getGameId());
			}
			String name = gameData.getDetail().split(";")[1];
			game.addUser(new Pair<String, WebSocketSession>(name,session));
			if(!game.isReady()) {
				session.sendMessage(new TextMessage("WAITING"));
			}
			else {
				game.getWs1().sendMessage(new TextMessage("READY"));
				game.getWs2().sendMessage(new TextMessage("READY"));
			}
		}
	}

	private void createGame(String gameId) {
		this.games.add(new Game(gameId));
	}
	
	private void sendMessageToGame(GameMessage data) throws IOException {
		var game = this.getGameById(data.getGameId());
		data.setPlayersNames(game.getName1(), game.getName2());
		((Game) game).getWs1().sendMessage(new TextMessage(data.toString()));
		((Game) game).getWs2().sendMessage(new TextMessage(data.toString()));
	}
	
	private GameMessage messageToGameMessageObject(TextMessage message) {
		var messageData = message.getPayload();
		JSONObject jsonData = new JSONObject(messageData);  
		return new GameMessage(jsonData.get("gameId"),jsonData.get("gameBoard"),jsonData.get("chat"),jsonData.get("detail"));
	}
	
	private Game getGameById(String data) {
		synchronized (flag) {
			var game =  this.games.stream().filter(g -> g.getGameId().equals(data)).collect(Collectors.toList());
			if (game.size() == 0) {
				return null;
			}
			return game.get(0);
		}
	}
	
    
    private void clearGames(String session) {
        Iterator<Game> iterator = this.games.iterator();
        while (iterator.hasNext()) {
        	if(!iterator.next().isReady() || iterator.next().getWs1().getId().equals(session)
        			|| iterator.next().getWs2().getId().equals(session)) {
        		this.games.remove(iterator.next());
        	}
        }

    }

    
}