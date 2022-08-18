package inrowbackend.handlerWebSocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import inrowbackend.model.Game;

@Service
public class HandlerWebSocketGame extends TextWebSocketHandler {
	
	private Collection<Game> games = Collections.synchronizedCollection(new ArrayList<Game>());
	private Object flag = new Object();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	String messageData = message.toString();
        if(messageData == "ADD ME TO GAME") {
        	this.addSessionToGame(messageData, session);
        }
        else {
        	this.sendMessageToGame(messageData);
        }
    }

	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    }
	
    private void addSessionToGame(String messageData, WebSocketSession session) {
		synchronized (flag) {
			Game game = this.getGameById(messageData);
			if(game != null) {
			}
		}
	}

	private void sendMessageToGame(String messageData) {
		
	}
	
	private Game getGameById(String gameId) {
		//return this.games.stream().findAny(g -> g.getGameId() == gameId).get().orElse(null);
		return null;
	}
    
}
