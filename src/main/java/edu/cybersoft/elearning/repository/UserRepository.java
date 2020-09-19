package edu.cybersoft.elearning.repository;

import edu.cybersoft.elearning.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.fullName = :keyword OR user.email = :keyword")
    List<User> search(String keyword);

    User findByEmail(String email);
}