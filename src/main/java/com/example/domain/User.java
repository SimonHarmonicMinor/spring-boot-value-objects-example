package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
  @Id
  private UUID id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String snils;

  @Column(name = "phone_number")
  private long phoneNumber;
}
