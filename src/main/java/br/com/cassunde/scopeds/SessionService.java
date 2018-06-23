package br.com.cassunde.scopeds;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class SessionService implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		private int total = 0;
	
		public int sum(int value) {
		
				total += value;
	
				return total;
		}
	
}
