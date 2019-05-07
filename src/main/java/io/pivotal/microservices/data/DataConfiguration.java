package io.pivotal.microservices.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
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

	protected Logger logger;

	public DataConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

}
