package inrowbackend.handlerWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HandlerWebSocketHome extends TextWebSocketHandler {

	private Collection<WebSocketSession> usersInHome = Collections.synchronizedCollection(new ArrayList<>());
	private Object flag = new Object();

	public Collection<WebSocketSession> getUsersInHome() {
		return usersInHome;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		synchronized (flag) {
			usersInHome.add(session);
		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		synchronized (flag) {
		usersInHome.forEach(s -> {
			try {
				s.sendMessage(new TextMessage(String.valueOf(usersInHome.size())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		synchronized (flag) {
			usersInHome.remove(session);
		}
	}
}
