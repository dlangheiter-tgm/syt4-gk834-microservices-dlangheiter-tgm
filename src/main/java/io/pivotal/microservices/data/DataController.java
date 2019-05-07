package io.pivotal.microservices.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class DataController {

    @Autowired
    private UsersService usersService;

    protected Logger logger = Logger.getLogger(DataController.class
            .getName());

    DataController() {
        System.out.println("GOT INTO DATA CONTROLLER ---------------------------------------------------------------------------------");
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "TEST";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(HttpServletRequest httpServletRequest) {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if(!usersService.authUser(authHeader)) {
            return "Error";
        }
        return "Data";
    }

}
