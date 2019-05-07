package io.pivotal.microservices.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * The users Spring configuration.
 * 
 * @author Paul Chapman
 */
@Configuration
@ComponentScan
@EntityScan("io.pivotal.microservices.data")
public class DataConfiguration {

	public static final String USERS_SERVICE_URL = "http://USERS-SERVICE";

	protected Logger logger;

	public DataConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

}
