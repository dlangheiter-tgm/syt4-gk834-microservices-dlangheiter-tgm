package io.pivotal.microservices.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class UsersService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private String serviceUrl;

    protected Logger logger = Logger.getLogger(DataController.class
            .getName());

    public UsersService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public boolean authUser(String header) {
        logger.info("UserServer try to auth user " + header);
        return true;
    }

}
