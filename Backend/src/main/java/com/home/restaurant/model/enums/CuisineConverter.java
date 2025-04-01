package com.home.restaurant.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CuisineConverter implements AttributeConverter<Cuisine, String> {

	@Override
	public String convertToDatabaseColumn(Cuisine cuisine) {
		return (cuisine == null) ? null : cuisine.name();
	}

	@Override
	public Cuisine convertToEntityAttribute(String dbData) {
		return (dbData == null) ? null : Cuisine.valueOf(dbData);
	}
}