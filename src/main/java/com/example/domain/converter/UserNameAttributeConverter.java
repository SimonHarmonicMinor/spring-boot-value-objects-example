package com.example.domain.converter;

import com.example.domain.User;
import com.example.domain.User.Name;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserNameAttributeConverter implements AttributeConverter<User.Name, String> {

  @Override
  public String convertToDatabaseColumn(Name attribute) {
    return attribute.getValue();
  }

  @Override
  public Name convertToEntityAttribute(String dbData) {
    return new Name(dbData);
  }
}
