package inrowbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import inrowbackend.handlerWebSocket.HandlerWebSocketGame;
import inrowbackend.handlerWebSocket.HandlerWebSocketSearch;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	
	private final static String GAME_END_POINT = "/ws_game";
	private final static String SEARCH_END_POINT = "/ws_search";
	
	@Autowired
	private HandlerWebSocketGame handlerWebSocketGame;
	@Autowired
	private HandlerWebSocketSearch handlerWebSocketSearch;
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handlerWebSocketGame, GAME_END_POINT)
                .setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(handlerWebSocketSearch, SEARCH_END_POINT)
        .setAllowedOrigins("*");
    }

	public void setHandlerWebSocketGame(HandlerWebSocketGame handlerWebSocketGame) {
		this.handlerWebSocketGame = handlerWebSocketGame;
	}

	public HandlerWebSocketSearch getHandlerWebSocketSearch() {
		return handlerWebSocketSearch;
	}

}
