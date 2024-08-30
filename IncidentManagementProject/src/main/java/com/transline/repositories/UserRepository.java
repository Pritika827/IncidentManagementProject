package com.transline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transline.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public Optional<User> findByUserName(String userName);

//	@Query("select u from User u where u.email=:email")
//	public User getUserByEmail(@Param("email") String email);

}
