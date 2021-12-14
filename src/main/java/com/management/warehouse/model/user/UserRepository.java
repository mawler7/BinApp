package com.management.warehouse.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAllIgnoreCase(String email);

    List<User> findAllByRole(UserRole role);

    Optional<User> findByPassword(String password);

    User findByEmail(String email);
}
