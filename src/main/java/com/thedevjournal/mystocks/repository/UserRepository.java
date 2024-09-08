package com.thedevjournal.mystocks.repository;

import com.thedevjournal.mystocks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :emailOrUsername " +
            "OR u.username = :emailOrUsername")
    Optional<User> findByUsernameOrEmail(String emailOrUsername);

    @Query(value = "SELECT EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM users\n" +
            "    WHERE username = ?1 OR email = ?2\n" +
            ")", nativeQuery = true)
    boolean existsByUsernameOrEmail(String username, String email);
}
