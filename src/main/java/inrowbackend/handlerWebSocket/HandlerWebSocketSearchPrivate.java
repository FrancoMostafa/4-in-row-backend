package inrowbackend.handlerWebSocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class HandlerWebSocketSearchPrivate extends TextWebSocketHandler {
	
	private Collection<WebSocketSession> webSocketSessions = Collections.synchronizedCollection(new ArrayList<>());
    private Map<String, WebSocketSession> users = Collections.synchronizedMap(new HashMap<String, WebSocketSession>());
	private Object flag = new Object();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		webSocketSessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		synchronized (flag) {
			String gameNumber = message.getPayload();
			if(users.get(gameNumber) == null) {
				users.put(gameNumber, session);
			}
			else {
				TextMessage gameId = new TextMessage(UUID.randomUUID().toString());
				session.sendMessage(gameId);
				users.get(gameNumber).sendMessage(gameId);
				users.remove(gameNumber);
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for (Entry<String, WebSocketSession> entry : users.entrySet()) {
		    if(entry.getValue().getId() == session.getId()) {
		    	users.remove(entry);
		    	break;
		    }
		}
		webSocketSessions.remove(session);
	}

}
