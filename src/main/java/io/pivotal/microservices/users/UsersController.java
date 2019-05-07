package io.pivotal.microservices.users;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.microservices.exceptions.AccountNotFoundException;

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
	 * @param userRepository
	 *            An account repository implementation.
	 */
	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;

		logger.info("UserRepository says system has "
				+ userRepository.countUsers() + " users");
	}

	@RequestMapping("/users")
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
	 * @param userNumber
	 *            A numeric, 9 digit account number.
	 * @return The account if found.
	 * @throws AccountNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/users/{userNumber}")
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
	 * @throws AccountNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/users/first_name/{name}")
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
