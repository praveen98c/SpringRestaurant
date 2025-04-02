package com.home.restaurant.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.home.restaurant.model.enums.Cuisine;
import com.home.restaurant.model.enums.CuisineConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "food_item")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Getter
    @Column(nullable = false)
    private String name;
    
    @Getter
    @Convert(converter = CuisineConverter.class)
    @Column(nullable = false)
    private Cuisine cuisine;
    
    @Getter
    @Column(nullable = false)
    private String description;
    
    @Getter
    @Column(precision = 3, scale = 1)
    private BigDecimal rating; 
    
    @Getter
    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
