package com.alltrips_transportion.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alltrips_transportion.security.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
