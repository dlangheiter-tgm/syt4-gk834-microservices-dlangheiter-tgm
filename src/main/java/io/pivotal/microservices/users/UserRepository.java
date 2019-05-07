package io.pivotal.microservices.users;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository for User data implemented using Spring Data JPA.
 * 
 * @author Paul Chapman
 */
public interface UserRepository extends Repository<User, Long> {

	public List<User> findAll();

	public User findById(Long id);

	/**
	 * Find users whose owner name contains the specified string
	 * 
	 * @param firstName
	 *            Any alphabetic string.
	 * @return The list of matching users - always non-null, but may be
	 *         empty.
	 */
	public List<User> findByFirstNameContainingIgnoreCase(String firstName);

	/**
	 * Fetch the number of users known to the system.
	 * 
	 * @return The number of users.
	 */
	@Query("SELECT count(*) from User")
	public int countUsers();
}
