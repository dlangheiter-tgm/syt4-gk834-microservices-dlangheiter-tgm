package io.pivotal.microservices.users;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent account entity with JPA markup. Accounts are stored in an H2
 * relational database.
 * 
 * @author Paul Chapman
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	@Id
	protected Long id;

	protected String firstName;
	protected String lastName;
	protected String role;
	protected String password;

	/**
	 * Default constructor for JPA only.
	 */
	protected User() {
	}


	public User(String firstName, String lastName, String role, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.password = password;
	}


	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", role='" + role + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
