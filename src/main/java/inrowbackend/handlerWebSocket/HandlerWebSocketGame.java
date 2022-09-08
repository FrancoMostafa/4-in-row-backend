package inrowbackend.handlerWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	
	private List<Game> games = new ArrayList<Game>();
	private Object flag = new Object();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		synchronized (flag) {
			var gameData = this.messageToGameMessageObject(message);
			Game game = this.getGameById(gameData.getGameId());
			if(gameData.getDetail().equals("ADD ME TO GAME")) {
				this.addSessionToGame(gameData, game, session);
			}
        	else {
        		this.sendMessageToGame(gameData, game);
        	}
		}
    }

	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.endGame(session);
    }

	private void addSessionToGame(GameMessage gameData, Game game, WebSocketSession session) throws IOException {
		synchronized (flag) {
			if(game == null) {
				this.createGame(gameData.getGameId());
				game = this.getGameById(gameData.getGameId());
			}
			game.addUser(new Pair<String, WebSocketSession>((String) gameData.getData(),session));
			if(!game.isReady()) {
				this.sendMessageWaiting(gameData, session);
			}
			else {
				this.sendMessageToGame(new GameMessage(gameData.getGameId(),new ArrayList<String>(Arrays.asList(game.getName1(), game.getWs1().getId(), game.getName2(), game.getWs2().getId())), "READY"), game);
			}
		}
	}

	private void createGame(String gameId) {
		this.games.add(new Game(gameId));
	}
	
	private void sendMessageToGame(GameMessage data, Game game) throws IOException {
		((Game) game).getWs1().sendMessage(new TextMessage(data.toString()));
		((Game) game).getWs2().sendMessage(new TextMessage(data.toString()));
	}
	
	private void sendMessageWaiting(GameMessage gameData, WebSocketSession session) throws IOException {
		GameMessage messageWaiting = new GameMessage(gameData.getGameId(), null, "WAITING");
		session.sendMessage(new TextMessage(messageWaiting.toString()));
	}
	
	private GameMessage messageToGameMessageObject(TextMessage message) {
		var messageData = message.getPayload();
		JSONObject jsonData = new JSONObject(messageData);  
		return new GameMessage(jsonData.get("gameId"),jsonData.get("data"),jsonData.get("detail"));
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
	
    
    private void endGame(WebSocketSession session) throws IOException {
		GameMessage messageDisconnect = new GameMessage(null, null, "DISCONNECT");
        for(int i = 0; i < games.size(); i++) {
        	WebSocketSession session1 = games.get(i).getWs1();
        	WebSocketSession session2 = games.get(i).getWs2();
        	if(session1 != null && session1.getId().equals(session.getId())) {
        		session2.sendMessage(new TextMessage(messageDisconnect.toString()));
        	}
        	
        	else if (session2 != null && session2.getId().equals(session.getId())) {
        		session1.sendMessage(new TextMessage(messageDisconnect.toString()));
        	}
        	
        	if (!(session1.isOpen() && session2.isOpen())
        			|| (session1 == null && session2 == null)) {
        		games.remove(i);
        	}
        	
        }
    }
    
}