package br.com.cassunde.rest.configuration.jersey;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.com.cassunde.FilterCROS;

@ApplicationPath("/service/")
public class RestConfiguration extends ResourceConfig {
	
    public RestConfiguration() {    
        packages(false, "br.com.cassunde.rest.resources");
        register(FilterCROS.class);
    }
}
