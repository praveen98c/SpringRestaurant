package com.home.restaurant.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users") // prevent Postgre conflict with user table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(name = "username", unique = true, nullable = false, length = 100)
	private String username;

	@Getter
	@Setter
	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Getter
	@Setter
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Setter
	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Transient
	private String token;
	
	@Getter
	@Setter
	private String role;
}
