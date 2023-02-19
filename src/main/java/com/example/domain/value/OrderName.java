package com.example.domain.value;

import lombok.Value;

@Value
public class OrderName {
  String value;

  public OrderName(String value) {
    this.value = validateOrderName(value);
  }

  private static String validateOrderName(String value) {
    String outputValue;
    return value;
  }
}
