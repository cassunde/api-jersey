package br.com.cassunde.rest.websocket;

import javax.websocket.server.ServerEndpointConfig.Configurator;

public class ChatServerEndPointConfigurator extends Configurator {

	private static Chat chatServer = new Chat();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
    	return (T)chatServer;
    }
}
