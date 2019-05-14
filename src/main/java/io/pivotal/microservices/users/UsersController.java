package io.pivotal.microservices.users;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.pivotal.microservices.exceptions.AccountNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

/**
 * A RESTFul controller for accessing account information.
 *
 * @author Paul Chapman
 */
@RestController
public class UsersController {

    protected Logger logger = Logger.getLogger(UsersController.class
            .getName());
    protected UserRepository userRepository;

    /**
     * Create an instance plugging in the respository of users.
     *
     * @param userRepository An account repository implementation.
     */
    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;

        logger.info("UserRepository says system has "
                + userRepository.countUsers() + " users");
    }

    @RequestMapping(value = "/users/authenticate", produces = "application/json")
    public AuthResponse authenticateUser(HttpServletRequest httpServletRequest) {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader == null || authHeader.isEmpty()) {
            logger.info("Request without Authorization header");
            return new AuthResponse(true);
        }
        String auth = new String(DatatypeConverter.parseBase64Binary(authHeader.split(" ")[1]));

        String[] split = auth.split(":");

        try {
            Long id = Long.parseLong(split[0]);
            String password = split[1];

            User user = userRepository.findById(id);

            // Should never ever to that in production
            logger.info("Try to auth user: " + id + " with password " + password);

            if(user != null && user.password.equals(password)) {
                return new AuthResponse(true);
            }
        } catch (NumberFormatException e) {
            logger.info("Id supplied was not a number");
        }

        return new AuthResponse(false);
    }

    @RequestMapping(value= "/users", produces = "application/json")
    public List<User> allUsers() {
        List<User> users = userRepository.findAll();
        logger.info("users-service byFirstName() found: " + users);

        if (users == null || users.size() == 0)
            throw new AccountNotFoundException("");
        else {
            return users;
        }
    }

    /**
     * Fetch an account with the specified account number.
     *
     * @param userNumber A numeric, 9 digit account number.
     * @return The account if found.
     * @throws AccountNotFoundException If the number is not recognised.
     */
    @RequestMapping(value = "/users/{userNumber}", produces = "application/json")
    public User byNumber(@PathVariable("userNumber") Long userNumber) {

        logger.info("users-service byNumber() invoked: " + userNumber);
        User user = userRepository.findById(userNumber);
        logger.info("users-service byNumber() found: " + user);

        if (user == null)
            throw new AccountNotFoundException(userNumber);
        else {
            return user;
        }
    }

    /**
     * Fetch users with the specified name. A partial case-insensitive match
     * is supported. So <code>http://.../users/owner/a</code> will find any
     * users with upper or lower case 'a' in their name.
     *
     * @param partialName
     * @return A non-null, non-empty set of users.
     * @throws AccountNotFoundException If there are no matches at all.
     */
    @RequestMapping(value = "/users/first_name/{name}", produces = "application/json")
    public List<User> byFirstName(@PathVariable("name") String partialName) {
        logger.info("users-service byFirstName() invoked: "
                + userRepository.getClass().getName() + " for "
                + partialName);

        List<User> users = userRepository
                .findByFirstNameContainingIgnoreCase(partialName);
        logger.info("users-service byFirstName() found: " + users);

        if (users == null || users.size() == 0)
            throw new AccountNotFoundException(partialName);
        else {
            return users;
        }
    }
}
