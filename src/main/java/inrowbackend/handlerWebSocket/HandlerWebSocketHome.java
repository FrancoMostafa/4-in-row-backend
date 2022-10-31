package inrowbackend.handlerWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class HandlerWebSocketHome extends TextWebSocketHandler {

	private Collection<WebSocketSession> webSocketHomeSessions = Collections.synchronizedCollection(new ArrayList<>());

	public Collection<WebSocketSession> getWebSocketHomeSessions() {
		return webSocketHomeSessions;
	}
	
	@Override
	public synchronized void afterConnectionEstablished(WebSocketSession session) throws Exception {
		webSocketHomeSessions.add(session);
	}

	@Override
	protected synchronized void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		webSocketHomeSessions.forEach(w -> {
			try {
				if(w.isOpen()) {
					w.sendMessage(new TextMessage(String.valueOf(webSocketHomeSessions.size()).toString()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public synchronized void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		webSocketHomeSessions.forEach(w -> {
				if(w.getId().equals(session.getId())) {
					webSocketHomeSessions.remove(w);
				}
			});
		webSocketHomeSessions.forEach(w -> {
			if(w.isOpen()) {
				try {
					w.sendMessage(new TextMessage(String.valueOf(webSocketHomeSessions.size())));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		}
	
}
