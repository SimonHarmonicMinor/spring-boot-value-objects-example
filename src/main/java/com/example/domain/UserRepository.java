package com.example.domain;

import com.example.domain.value.Passport;
import com.example.domain.value.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, User.ID> {

  Optional<User> findByPhoneNumber(@NotNull PhoneNumber phoneNumber);

  @Query("""
      SELECT u.passport FROM User u
      WHERE u.id = :id
      """)
  Optional<Passport> findPassportById(User.ID id);

  @Query(
      value = "SELECT * FROM order WHERE name LIKE :name",
      nativeQuery = true
  )
  List<User> findByNameLike(String name);
}
