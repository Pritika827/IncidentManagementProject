package com.transline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transline.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public Optional<User> findByUserName(String userName);

	@Query("SELECT MAX(CAST(SUBSTRING(u.userId, LENGTH(:prefix) + 1) AS INTEGER)) FROM User u WHERE u.userId LIKE CONCAT(:prefix, '%')")
	Optional<Integer> findMaxNumberForPrefix(@Param("prefix") String prefix);

}
