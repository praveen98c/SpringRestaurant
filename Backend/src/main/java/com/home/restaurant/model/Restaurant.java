package com.home.restaurant.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Getter
    @Column(nullable = false)
    private String name;
    
	@Getter
    @Column(nullable = false)
    private String location;
    
	@Getter
    @Column(nullable = false)
    private String phone;
    
	@Getter
    @Column(nullable = false)
    private String email;

	@Getter
    @Column(name = "image_url")
    private String imageUrl;

	@Getter
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}