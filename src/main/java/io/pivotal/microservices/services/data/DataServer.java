package io.pivotal.microservices.services.data;

import io.pivotal.microservices.data.DataConfiguration;
import io.pivotal.microservices.users.UserRepository;
import io.pivotal.microservices.users.UsersConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(DataConfiguration.class)
public class DataServer {

    protected Logger logger = Logger.getLogger(DataServer.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for users-server.properties or
        // users-server.yml
        System.setProperty("spring.config.name", "data-server");

        SpringApplication.run(DataServer.class, args);
    }
}