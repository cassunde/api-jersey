package br.com.cassunde.rest.websocket;

import java.net.URI;
import java.net.URISyntaxException;

public class ClientNotificationBasic {

	private String url;
	private String message;
	
	public ClientNotificationBasic(String url, String message) {
		this.url = url;
		this.message = message;
	}

	public void call() {
		try {
            // open websocket
            final ChatClient clientEndPoint = new ChatClient(new URI("ws://"+url));

            // send message to websocket
            clientEndPoint.sendMessage( message );

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
	}
}