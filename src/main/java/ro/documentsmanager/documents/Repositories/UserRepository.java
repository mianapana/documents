package ro.documentsmanager.documents.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.documentsmanager.documents.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
