package br.com.cassunde.scopeds;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

@ConversationScoped
public class ConversationService implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Inject
		private Conversation conversation;
		
		private int total = 0;
	
		public int sum(int value, boolean withConversation) {
		
				if( withConversation ) {
						startConversation();
				}else {
						stopConversation();
				}
			
			
				total += value;
	
				return total;
		}
		
		public void startConversation() {
				if( conversation.isTransient() ) {
						conversation.begin();
				}
		}
		
		public void stopConversation() {
				if( !conversation.isTransient() ) {
						conversation.end();
				}
		}
}
